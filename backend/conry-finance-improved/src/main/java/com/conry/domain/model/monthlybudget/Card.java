package com.conry.domain.model.monthlybudget;

import com.conry.domain.model.enums.CardType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARD_SEQ")
    @SequenceGenerator(name = "CARD_SEQ", sequenceName = "CARD_SEQ", allocationSize = 1)
    private Long id;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CardItem> cardItems = new LinkedList<>();
    private BigDecimal amount = new BigDecimal(0);
    private CardType cardType = CardType.DEFAULT;

    public void addItem(CardItem cardItem) {
        if (this.cardItems == null) {
            this.cardItems = new ArrayList<>();
        }
        this.cardItems.add(cardItem);
    }
}
