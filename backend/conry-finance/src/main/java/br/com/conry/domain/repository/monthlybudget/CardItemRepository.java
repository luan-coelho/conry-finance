package br.com.conry.domain.repository.monthlybudget;

import br.com.conry.domain.model.monthlybudget.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardItemRepository extends JpaRepository<CardItem, Long> {
}
