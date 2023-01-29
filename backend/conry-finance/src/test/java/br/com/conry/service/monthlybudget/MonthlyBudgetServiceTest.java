package br.com.conry.service.monthlybudget;

import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import br.com.conry.domain.repository.MonthlyBudgetRepository;
import br.com.conry.rest.dto.card.CardCreateDTO;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
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
        // Arrange
        MonthlyBudget monthlyBudgetCreated = monthlyBudgetService.create(monthlyBudgetCreateDTO);

        // Act
        boolean exists = monthlyBudgetRepository.existsById(monthlyBudgetCreated.getId());

        Assertions.assertThat(exists).isTrue();
        Assertions.assertThat(monthlyBudgetCreated.getId()).isNotNull();
        Assertions.assertThat(monthlyBudgetCreated.getDescription()).isEqualTo("Test");
        Assertions.assertThat(monthlyBudgetCreated.getPeriod()).isEqualTo(LocalDate.now());
        Assertions.assertThat(monthlyBudgetCreated.getCards()).isNotEmpty();
        Assertions.assertThat(monthlyBudgetCreated.getVersion()).isEqualTo(0);
    }

    @Transactional
    @Test
    @DisplayName("Checks if an exception is being thrown when passing a description that already belongs to another monthly budget")
    void testCreate_whenEnterDescriptionOfAnotherMonthlyBudget_thenReturnIllegalArgumentException() {
        monthlyBudgetService.create(monthlyBudgetCreateDTO);

        assertThatThrownBy(() -> monthlyBudgetService.create(monthlyBudgetCreateDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is already an monthly budget registered with this name");
    }

    /**
     * Transactional(propagation = Propagation.SUPPORTS)
     * A new transaction is always created, regardless of whether there is an active transaction.
     * The current transaction becomes the active transaction and the previous transaction is suspended.
     */

    @Transactional(propagation = Propagation.SUPPORTS) //
    @Test
    @DisplayName("Checks if the monthly budget description was successfully changed")
    void testChangeDescription_ChecksIfTheDescriptionWasChanged_() {
        // Arrange
        Long id = monthlyBudgetService.create(monthlyBudgetCreateDTO).getId();

        // Act
        MonthlyBudget monthlyBudgetUpdated = monthlyBudgetService.changeDescription(id, "New description");
        boolean exists = monthlyBudgetRepository.existsById(monthlyBudgetUpdated.getId());

        // Assert
        Assertions.assertThat(monthlyBudgetUpdated.getDescription()).isEqualTo("New description");
        Assertions.assertThat(exists).isTrue();
        Assertions.assertThat(monthlyBudgetUpdated.getChangeDate()).isNotNull();
        Assertions.assertThat(monthlyBudgetUpdated.getChangeDate().isAfter(monthlyBudgetUpdated.getCreationDate())).isTrue();
    }

    @Transactional
    @Test
    @DisplayName("Checks if the monthly budget was successfully deleted from the database")
    void testDelete_CheckIfMonthlyBudgetIsDeleted_Void() {
        // Arrange
        MonthlyBudget monthlyBudget = monthlyBudgetService.create(monthlyBudgetCreateDTO);
        Long monthlyBudgetId = monthlyBudget.getId();

        // Act
        monthlyBudgetService.delete(monthlyBudgetId);
        boolean exists = monthlyBudgetRepository.existsById(monthlyBudgetId);

        // Assert
        Assertions.assertThat(exists).isFalse();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Test
    @DisplayName("Checks if new card is being added to the monthly budget")
    void testAddCard_WhenNewCardIsAdded_thenReturnTheMonthlyBudget() {
        // Arrange
        CardCreateDTO cardCreateDTO = new CardCreateDTO("New Card");
        MonthlyBudget monthlyBudget = monthlyBudgetService.create(monthlyBudgetCreateDTO);

        // Act
        MonthlyBudget monthlyBudgetWithNewCard = monthlyBudgetService.addCard(monthlyBudget.getId(), cardCreateDTO);

        // Assert
        Assertions.assertThat(monthlyBudgetWithNewCard.getCards().size()).isEqualTo(4);
        Assertions.assertThat(monthlyBudgetWithNewCard.getCards().get(3).getDescription()).isEqualTo("New Card");
    }
}