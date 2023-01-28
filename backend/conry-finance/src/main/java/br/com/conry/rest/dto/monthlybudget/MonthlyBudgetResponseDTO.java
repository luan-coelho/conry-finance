package br.com.conry.rest.dto.monthlybudget;

import br.com.conry.domain.model.monthlybudget.Card;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class MonthlyBudgetResponseDTO {

    private Long id;
    private String description;
    private List<Card> cards;
    private LocalDate period;
}
