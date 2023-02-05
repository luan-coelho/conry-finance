package com.conry.domain.repository.monthlybudget;

import com.conry.domain.model.monthlybudget.Card;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CardRepository implements PanacheRepository<Card> {
}
