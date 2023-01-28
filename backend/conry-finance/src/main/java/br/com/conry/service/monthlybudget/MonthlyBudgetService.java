package br.com.conry.service.monthlybudget;

import br.com.conry.domain.model.enums.CardType;
import br.com.conry.domain.model.monthlybudget.Card;
import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import br.com.conry.domain.repository.MonthlyBudgetRepository;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MonthlyBudgetService {

    private final MonthlyBudgetRepository monthlyBudgetRepository;

    /**
     * Responsible for creating and persisting a monthly budget in the database
     * @param monthlyBudget Monthly budget with description and period informed by the customer
     * @return Monthly budget persisted in the database
     */
    public MonthlyBudget create(MonthlyBudgetCreateDTO monthlyBudget) {
        if (monthlyBudgetRepository.existsByDescriptionEqualsIgnoreCase(monthlyBudget.getDescription())) { // Checks if there is already a monthly budget with the name entered
            throw new IllegalArgumentException("There is already an monthly budget registered with this name");
        }
        MonthlyBudget monthlyBudgetPersisted = createDefaultMonthlyBudget(monthlyBudget.getDescription(), monthlyBudget.getPeriod());

        return monthlyBudgetRepository.save(monthlyBudgetPersisted);
    }

    /**
     * Responsible for changing the description of a monthly budget
     * @param id Monthly budget identifier
     * @param description New monthly budget description
     * @return Monthly budget with updated description
     */
    public MonthlyBudget changeDescription(Long id, String description) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Monthly budget not found by id"));
        monthlyBudget.setDescription(description);

        return monthlyBudgetRepository.save(monthlyBudget);
    }

    /**
     *
     * @param description
     * @param period
     * @return
     */
    private MonthlyBudget createDefaultMonthlyBudget(String description, LocalDate period) {
        BigDecimal amount = new BigDecimal("0.00");

        Card defaultCard = Card.builder()
                .description("Description")
                .amount(amount)
                .cardType(CardType.DEFAULT)
                .build();

        Card totalAmountCard = Card.builder()
                .description("Total amount")
                .amount(amount)
                .cardType(CardType.TOTAL_AMOUNT_SPENT)
                .build();

        Card totalAvailable = Card.builder()
                .description("Total available")
                .amount(amount)
                .cardType(CardType.TOTAL_AVAILABLE)
                .build();

        return MonthlyBudget.builder()
                .description(description)
                .period(period)
                .cards(List.of(defaultCard, totalAmountCard, totalAvailable))
                .build();
    }
}
