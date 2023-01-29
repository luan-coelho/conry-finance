package br.com.conry.domain.repository.monthlybudget;

import br.com.conry.domain.model.monthlybudget.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Long> {
}
