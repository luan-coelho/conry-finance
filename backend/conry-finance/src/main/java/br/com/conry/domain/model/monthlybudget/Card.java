package br.com.conry.domain.model.monthlybudget;

import br.com.conry.domain.model.enums.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CardItem> cardItems = new LinkedList<>();
    private BigDecimal amount = new BigDecimal(0);
    private CardType cardType = CardType.DEFAULT;

    public void addItem(CardItem cardItem) {
        if (this.cardItems == null) {
            return;
        }
        this.cardItems.add(cardItem);
    }
}
