package br.com.conry.domain.model.monthlybudget;

import br.com.conry.domain.model.DefaultEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = "id", callSuper = false)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class MonthlyBudget extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monthlybudget_seq")
    @SequenceGenerator(name = "monthlybudget_seq", sequenceName = "monthlybudget_seq", allocationSize = 1)
    private Long id;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Card> cards = new ArrayList<>();
    private LocalDate period = LocalDate.now();
}
