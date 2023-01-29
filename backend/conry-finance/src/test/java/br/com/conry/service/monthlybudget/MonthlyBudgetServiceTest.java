package br.com.conry.service.monthlybudget;

import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import br.com.conry.domain.repository.MonthlyBudgetRepository;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

/**
 * This class is responsible for testing the Monthly budget service
 */
@ActiveProfiles("test")
@SpringBootTest
class MonthlyBudgetServiceTest {

    @Autowired
    MonthlyBudgetService monthlyBudgetService;

    @Autowired
    MonthlyBudgetRepository monthlyBudgetRepository;

    MonthlyBudgetCreateDTO monthlyBudgetCreateDTO;

    @BeforeEach
    void setUp() {
        monthlyBudgetCreateDTO = new MonthlyBudgetCreateDTO();
        monthlyBudgetCreateDTO.setDescription("Test");
        monthlyBudgetCreateDTO.setPeriod(LocalDate.now());
    }

    /**
     * SIGNATURE STANDARD IN TESTING METHODS
     * Method name - Purpose of the test - Expected return
     * Body of each test must be composed for: Arrange, Act e Assert
     */

    @Transactional
    @Test
    @DisplayName("Checks if monthly budget was successfully created and persisted")
    void testCreate_whenValidInput_thenReturnCreatedMontlyBudget() {
        MonthlyBudget monthlyBudgetCreated = monthlyBudgetService.create(monthlyBudgetCreateDTO);
        boolean exists = monthlyBudgetRepository.existsById(monthlyBudgetCreated.getId());

        Assertions.assertThat(exists).isTrue();
    }

    @Transactional
    @Test
    @DisplayName("Checks if an exception is being thrown when passing a description that already belongs to another monthly budget")
    void testCreate_whenEnterDescriptionOfAnotherMonthlyBudget_thenReturnIllegalArgumentException() {
        monthlyBudgetService.create(monthlyBudgetCreateDTO);

        assertThatThrownBy(() -> {
            monthlyBudgetService.create(monthlyBudgetCreateDTO);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is already an monthly budget registered with this name");
    }

    @Transactional
    @Test
    @DisplayName("Checks if the monthly budget description was successfully changed")
    void testChangeDescription_ChecksIfTheDescriptionWasChanged_() {
        MonthlyBudget monthlyBudgetCreated = monthlyBudgetService.changeDescription(1L, "Test");
        boolean exists = monthlyBudgetRepository.existsById(monthlyBudgetCreated.getId());

        Assertions.assertThat(exists).isTrue();
    }

    @Transactional
    @Test
    @DisplayName("Checks if the monthly budget was successfully deleted from the database")
    void testDelete_CheckIfMonthlyBudgetIsDeleted_Void() {
        MonthlyBudget monthlyBudget = monthlyBudgetService.create(monthlyBudgetCreateDTO);
        Long monthlyBudgetId = monthlyBudget.getId();
        monthlyBudgetService.delete(monthlyBudgetId);
        boolean exists = monthlyBudgetRepository.existsById(monthlyBudgetId);

        Assertions.assertThat(exists).isFalse();
    }
}