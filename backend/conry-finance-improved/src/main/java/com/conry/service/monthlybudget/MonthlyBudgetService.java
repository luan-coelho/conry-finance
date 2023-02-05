package com.conry.service.monthlybudget;

import com.conry.commons.MontlyBudgetComponents;
import com.conry.domain.model.monthlybudget.Card;
import com.conry.domain.model.monthlybudget.MonthlyBudget;
import com.conry.domain.repository.monthlybudget.CardRepository;
import com.conry.domain.repository.monthlybudget.MonthlyBudgetRepository;
import com.conry.rest.dto.card.CardCreateDTO;
import com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Service responsible for the monthly budget business rule
 */
@ApplicationScoped
public class MonthlyBudgetService {

    @Inject
    MonthlyBudgetRepository monthlyBudgetRepository;

    @Inject
    CardRepository cardRepository;

    /**
     * Responsible for creating and persisting a monthly budget in the database
     *
     * @param monthlyBudget Monthly budget with description and period informed by the customer
     * @return Monthly budget persisted in the database
     */
    public MonthlyBudget create(MonthlyBudgetCreateDTO monthlyBudget) {
        if (monthlyBudgetRepository.existsByDescriptionEqualsIgnoreCase(monthlyBudget.getDescription())) { // Checks if there is already a monthly budget with the name entered
            throw new IllegalArgumentException("There is already an monthly budget registered with this name");
        }
        MonthlyBudget monthlyBudgetPersisted = MontlyBudgetComponents.createDefaultInstance(monthlyBudget.getDescription(), monthlyBudget.getPeriod());

        monthlyBudgetRepository.persist(monthlyBudgetPersisted);

        return monthlyBudgetPersisted;
    }

    /**
     * Responsible for changing the description of a monthly budget
     *
     * @param id          Monthly budget identifier
     * @param description New monthly budget description
     * @return Monthly budget with updated description
     */
    public MonthlyBudget changeDescription(Long id, String description) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByIdOptional(id).orElseThrow(() -> new IllegalArgumentException("Monthly budget not found by id"));
        monthlyBudget.setDescription(description);

        monthlyBudgetRepository.persist(monthlyBudget);

        return monthlyBudget;
    }

    /**
     * Deletes a monthly budget from the database
     *
     * @param id Monthly budget identifier
     */
    public void delete(Long id) {
        if (monthlyBudgetRepository.count("id", id) == 0) {
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
    public MonthlyBudget addCard(Long monthlyBudgetId, CardCreateDTO cardCreateDTO) {
        MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByIdOptional(monthlyBudgetId).orElseThrow(() -> new IllegalArgumentException("Monthly budget not found by id"));

        Card newCard = MontlyBudgetComponents.buildCard(cardCreateDTO.getDescription());
        monthlyBudget.getCards().add(newCard);

        monthlyBudgetRepository.persist(monthlyBudget);

        return monthlyBudget;
    }

    /**
     * Remove an item from a card
     *
     * @param cardItemId Card item identifier
     */
    public void removeCard(Long cardItemId) {
        if (cardRepository.count("id", cardItemId) == 0) { // Exists by id. x > 0
            throw new IllegalArgumentException("Card item not found by id");
        }
        monthlyBudgetRepository.deleteById(cardItemId);
    }
}
