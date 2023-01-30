package br.com.conry.service.monthlybudget;

import br.com.conry.commons.MontlyBudgetComponents;
import br.com.conry.domain.model.monthlybudget.Card;
import br.com.conry.domain.model.monthlybudget.CardItem;
import br.com.conry.domain.repository.monthlybudget.CardItemRepository;
import br.com.conry.domain.repository.monthlybudget.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;
    private final CardItemRepository cardItemRepository;

    /**
     * Responsible for adding a new item to the card
     *
     * @param cardId Card identifier
     * @return Card updated
     */
    public Card addItem(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("Card not found by id"));

        CardItem cardItem = MontlyBudgetComponents.buildCardItem("Description");
        card.getCardItems().add(cardItem);

        return cardRepository.save(card);
    }

    /**
     * @param cardId     Card item identifier
     * @param cardItemId Item identifier
     * @return Card updated
     */
    public Card removeItem(Long cardId, Long cardItemId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("Card not found by id"));
        boolean existsById = cardItemRepository.existsById(cardId);

        if (existsById) {
            card.getCardItems().removeIf((item) -> item.getId().equals(cardItemId));
            return cardRepository.save(card);
        }

        throw new IllegalArgumentException("Item not found by id");
    }
}
