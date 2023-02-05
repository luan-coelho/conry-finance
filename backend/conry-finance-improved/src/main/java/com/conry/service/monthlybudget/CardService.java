package com.conry.service.monthlybudget;

import com.conry.commons.MontlyBudgetComponents;
import com.conry.domain.model.monthlybudget.Card;
import com.conry.domain.model.monthlybudget.CardItem;
import com.conry.domain.repository.monthlybudget.CardItemRepository;
import com.conry.domain.repository.monthlybudget.CardRepository;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@RequiredArgsConstructor
@ApplicationScoped
public class CardService {

    @Inject
    CardRepository cardRepository;

    @Inject
    CardItemRepository cardItemRepository;

    /**
     * Responsible for adding a new item to the card
     *
     * @param cardId Card identifier
     * @return Card updated
     */
    public Card addItem(Long cardId) {
        Card card = cardRepository.findByIdOptional(cardId).orElseThrow(() -> new IllegalArgumentException("Card not found by id"));

        CardItem cardItem = MontlyBudgetComponents.buildCardItem("Description");

        card.getCardItems().add(cardItem);
        cardRepository.persist(card);

        return cardRepository.findById(card.getId());
    }

    /**
     * @param cardId     Card item identifier
     * @param cardItemId Item identifier
     * @return Card updated
     */
    public Card removeItem(Long cardId, Long cardItemId) {
        Card card = cardRepository.findByIdOptional(cardId).orElseThrow(() -> new IllegalArgumentException("Card not found by id"));
        boolean existsById = cardItemRepository.findById(cardId) != null;

        if (existsById) {
            card.getCardItems().removeIf((item) -> item.getId().equals(cardItemId));
            cardRepository.persist(card);
            return cardRepository.findById(card.getId());
        }

        throw new IllegalArgumentException("Item not found by id");
    }
}
