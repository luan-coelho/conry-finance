package com.conry.commons;

import com.conry.domain.model.enums.CardType;
import com.conry.domain.model.monthlybudget.Card;
import com.conry.domain.model.monthlybudget.CardItem;
import com.conry.domain.model.monthlybudget.MonthlyBudget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class responsible for creating components of a monthly budget
 */
public class MontlyBudgetComponents {


    /**
     * Create a default monthly budget instance
     *
     * @param description Montly budget description
     * @param period      Monthly budget period
     * @return Standard monthly budget instance
     */
    public static MonthlyBudget createDefaultInstance(String description, LocalDate period) {
        final BigDecimal AMOUNT = new BigDecimal("0.00");

        Card defaultCard = Card.builder().description("Description").amount(AMOUNT).cardType(CardType.DEFAULT).cardItems(new ArrayList<>(List.of(MontlyBudgetComponents.buildCardItem("Description")))).build();
        Card totalAmountCard = Card.builder().description("Total amount").amount(AMOUNT).cardType(CardType.TOTAL_AMOUNT_SPENT).cardItems(new ArrayList<>(List.of(MontlyBudgetComponents.buildCardItem("Description")))).build();
        Card totalAvailable = Card.builder().description("Total available").amount(AMOUNT).cardType(CardType.TOTAL_AVAILABLE).cardItems(new ArrayList<>(List.of(MontlyBudgetComponents.buildCardItem("Description")))).build();

        ArrayList<Card> cards = new ArrayList<>(Arrays.asList(defaultCard, totalAmountCard, totalAvailable));

        return MonthlyBudget.builder().description(description).period(period).cards(cards).build();
    }

    /**
     * Constructs a card item with a standard card item inserted
     *
     * @param description Card description
     * @return Default card
     */
    public static Card buildCard(String description) {
        Card newCard = new Card();
        newCard.setDescription(description);
        newCard.getCardItems().add(buildCardItem("Description"));

        return newCard;
    }

    /**
     * Constructs a card item with a standard card item inserted
     *
     * @return Default card item
     */
    public static CardItem buildCardItem(String description) {
        return new CardItem(null, description, new BigDecimal("0"), LocalDateTime.now());
    }
}
