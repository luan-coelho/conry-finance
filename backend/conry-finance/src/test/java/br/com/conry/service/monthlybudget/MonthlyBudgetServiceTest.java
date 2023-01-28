package br.com.conry.service.monthlybudget;

import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import br.com.conry.domain.repository.MonthlyBudgetRepository;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static br.com.conry.common.monthlybudget.MouthlyBudgetConstants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MonthlyBudgetServiceTest {

    @InjectMocks
    MonthlyBudgetService monthlyBudgetService;

    @Mock
    MonthlyBudgetRepository monthlyBudgetRepository;

    MonthlyBudgetCreateDTO monthlyBudgetCreate;

    @BeforeEach
    void setUp() {
        monthlyBudgetCreate = new MonthlyBudgetCreateDTO();
        monthlyBudgetCreate.setDescription("description");
        monthlyBudgetCreate.setPeriod(LocalDate.now());
    }

    @Test
    @DisplayName("Testa se MonthlyBudget está sendo criado corretamente")
    void create_testaSeMonthlyBudgetEstaSendoCriadoCorretamente_monthlybudget() {
        // Arrange
        when(monthlyBudgetRepository.save(MONTHLY_BUDGET)).thenReturn(MONTHLY_BUDGET_CREATED);

        // Act
        MonthlyBudget monthlyBudgetCreated = monthlyBudgetService.create(monthlyBudgetCreate);

        // Assert
        assertThat(monthlyBudgetCreated.getId()).isEqualTo(1L);
        assertThat(monthlyBudgetCreated.getDescription()).isEqualTo("description");
        assertThat(monthlyBudgetCreated.getVersion()).isEqualTo(0);
        assertThat(monthlyBudgetCreated.getCards()).isNotNull();
        assertThat(monthlyBudgetCreated.getPeriod()).isEqualTo(LocalDate.now());
    }

    @Test
    @DisplayName("Testa se excessão está sendo lançada caso seja passado um nome que já esteja cadastrado")
    void create_testaSeExcessaoEstaSendoLancada_IllegalArgumentException() {
        // Arrange
        when(monthlyBudgetRepository.existsByDescriptionEqualsIgnoreCase("description")).thenReturn(true);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> monthlyBudgetService.create(monthlyBudgetCreate), "There is already an monthly budget registered with this name");
    }

    @Test
    @DisplayName("Testa se descrição a descrição está sendo alterada com sucesso")
    void changeDescription_testaSeDescricaoEstaSendoAlterada_MonthlyBudget() {
        // Arrange
        when(monthlyBudgetRepository.findById(1L)).thenReturn(Optional.of(MONTHLY_BUDGET_CREATED));
        when(monthlyBudgetRepository.save(MONTHLY_BUDGET_BEFORE_UPDATED)).thenReturn(MONTHLY_BUDGET_AFTER_UPDATED);

        // Act
        MonthlyBudget monthlyBudget = monthlyBudgetService.changeDescription(1L, "company expenses");

        // Assert
        assertThat(monthlyBudget.getDescription()).isEqualTo("company expenses");
    }
}