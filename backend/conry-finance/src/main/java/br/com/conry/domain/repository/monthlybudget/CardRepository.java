package br.com.conry.domain.repository.monthlybudget;

import br.com.conry.domain.model.monthlybudget.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
