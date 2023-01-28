package br.com.conry.common.monthlybudget;

import br.com.conry.domain.model.enums.CardType;
import br.com.conry.domain.model.monthlybudget.Card;
import br.com.conry.domain.model.monthlybudget.MonthlyBudget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class MouthlyBudgetConstants {

    public static final MonthlyBudget MONTHLY_BUDGET = createDefaultMonthlyBudget("description", LocalDate.now());
    public static final MonthlyBudget MONTHLY_BUDGET_CREATED = persisted(null);
    public static final MonthlyBudget MONTHLY_BUDGET_BEFORE_UPDATED = decriptionBeforeChanged();
    public static final MonthlyBudget MONTHLY_BUDGET_AFTER_UPDATED = decriptionAfterChanged();

    public static MonthlyBudget createDefaultMonthlyBudget(String description, LocalDate period) {
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

    public static MonthlyBudget persisted(String description) {
        MonthlyBudget monthlyBudget;

        monthlyBudget = createDefaultMonthlyBudget(Objects.requireNonNullElse(description, "description"), LocalDate.now());

        monthlyBudget.setId(1L);
        monthlyBudget.setVersion(0);
        monthlyBudget.setCreationDate(LocalDateTime.now());

        return monthlyBudget;
    }

    public static MonthlyBudget decriptionBeforeChanged() {
        return createDefaultMonthlyBudget("company expenses", LocalDate.now());
    }

    public static MonthlyBudget decriptionAfterChanged() {
        MonthlyBudget monthlyBudget = createDefaultMonthlyBudget("company expenses", LocalDate.now());

        monthlyBudget.setId(1L);
        monthlyBudget.setVersion(1);
        monthlyBudget.setCreationDate(LocalDateTime.now().minusDays(10));
        monthlyBudget.setChangeDate(LocalDateTime.now());

        return monthlyBudget;
    }
}
