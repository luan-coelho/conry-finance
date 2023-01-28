package br.com.conry.domain.model.monthlybudget;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carditem_seq")
    @SequenceGenerator(name = "carditem_seq", sequenceName = "carditem_seq", allocationSize = 1)
    private Long id;
    private String description;
    private BigDecimal amount = new BigDecimal(0);
    private LocalDateTime eventDateTime;
}
