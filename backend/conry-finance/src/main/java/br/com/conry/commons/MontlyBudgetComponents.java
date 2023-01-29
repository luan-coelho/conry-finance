package br.com.conry.commons;

import br.com.conry.domain.model.monthlybudget.Card;
import br.com.conry.domain.model.monthlybudget.CardItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Class responsible for creating components of a monthly budget
 */
public class MontlyBudgetComponents {

    /**
     * Constructs a card item with a standard card item inserted
     *
     * @param description Card description
     * @return Default card
     */
    public static Card buildCard(String description) {
        Card newCard = new Card();
        newCard.setDescription(description);
        newCard.getCardItems().add(buildDefaultCardItem());

        return newCard;
    }

    /**
     * Constructs a card item with a standard card item inserted
     *
     * @return Default card item
     */
    public static CardItem buildDefaultCardItem() {
        return new CardItem(null, "Description", new BigDecimal("0"), LocalDateTime.now());
    }
}
