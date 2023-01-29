package br.com.conry.service.monthlybudget;

import br.com.conry.commons.MontlyBudgetComponents;
import br.com.conry.domain.model.enums.CardType;
import br.com.conry.domain.model.monthlybudget.Card;
import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import br.com.conry.domain.repository.MonthlyBudgetRepository;
import br.com.conry.domain.repository.monthlybudget.CardRepository;
import br.com.conry.rest.dto.card.CardCreateDTO;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Service responsible for the monthly budget business rule
 */
@RequiredArgsConstructor
@Service
public class MonthlyBudgetService {

    private final MonthlyBudgetRepository monthlyBudgetRepository;
    private final CardRepository cardRepository;

    /**
     * Responsible for creating and persisting a monthly budget in the database
     *
     * @param monthlyBudget Monthly budget with description and period informed by the customer
     * @return Monthly budget persisted in the database
     */
    @Transactional
    public MonthlyBudget create(MonthlyBudgetCreateDTO monthlyBudget) {
        if (monthlyBudgetRepository.existsByDescriptionEqualsIgnoreCase(monthlyBudget.getDescription())) { // Checks if there is already a monthly budget with the name entered
            throw new IllegalArgumentException("There is already an monthly budget registered with this name");
        }
        MonthlyBudget monthlyBudgetPersisted = createDefaultInstance(monthlyBudget.getDescription(), monthlyBudget.getPeriod());

        return monthlyBudgetRepository.save(monthlyBudgetPersisted);
    }

    /**
     * Responsible for changing the description of a monthly budget
     *
     * @param id          Monthly budget identifier
     * @param description New monthly budget description
     * @return Monthly budget with updated description
     */
    @Transactional
    public MonthlyBudget changeDescription(Long id, String description) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Monthly budget not found by id"));
        monthlyBudget.setDescription(description);

        return monthlyBudgetRepository.save(monthlyBudget);
    }

    /**
     * Create a default monthly budget instance
     *
     * @param description Montly budget description
     * @param period      Monthly budget period
     * @return Standard monthly budget instance
     */
    private MonthlyBudget createDefaultInstance(String description, LocalDate period) {
        final BigDecimal AMOUNT = new BigDecimal("0.00");

        Card defaultCard = Card.builder().description("Description").amount(AMOUNT).cardType(CardType.DEFAULT).cardItems(new ArrayList<>(List.of(MontlyBudgetComponents.buildDefaultCardItem()))).build();
        Card totalAmountCard = Card.builder().description("Total amount").amount(AMOUNT).cardType(CardType.TOTAL_AMOUNT_SPENT).cardItems(new ArrayList<>(List.of(MontlyBudgetComponents.buildDefaultCardItem()))).build();
        Card totalAvailable = Card.builder().description("Total available").amount(AMOUNT).cardType(CardType.TOTAL_AVAILABLE).cardItems(new ArrayList<>(List.of(MontlyBudgetComponents.buildDefaultCardItem()))).build();

        return MonthlyBudget.builder().description(description).period(period).cards(new ArrayList<>(Arrays.asList(defaultCard, totalAmountCard, totalAvailable))).build();
    }

    /**
     * Deletes a monthly budget from the database
     *
     * @param id Monthly budget identifier
     */
    @Transactional
    public void delete(Long id) {
        if (!monthlyBudgetRepository.existsById(id)) {
            throw new IllegalArgumentException("Monthly budget not found by id");
        }
        monthlyBudgetRepository.deleteById(id);
    }

    /**
     * Adds a card to the monthly budget
     *
     * @param monthlyBudgetId Monthly budget identifier
     * @param cardCreateDTO   DTO with input data
     * @return Montly budget updated
     */
    @Transactional
    public MonthlyBudget addCard(Long monthlyBudgetId, CardCreateDTO cardCreateDTO) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findById(monthlyBudgetId).orElseThrow(() -> new IllegalArgumentException("Monthly budget not found by id"));

        Card newCard = MontlyBudgetComponents.buildCard(cardCreateDTO.getDescription());
        monthlyBudget.getCards().add(newCard);

        return monthlyBudgetRepository.save(monthlyBudget);
    }
}
