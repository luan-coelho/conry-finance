package com.conry.domain.model.monthlybudget;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(of = "id")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARDITEM_SEQ")
    @SequenceGenerator(name = "CARDITEM_SEQ", sequenceName = "CARDITEM_SEQ", allocationSize = 1)
    private Long id;
    private String description;
    private BigDecimal amount = new BigDecimal(0);
    private LocalDateTime eventDateTime;
}
