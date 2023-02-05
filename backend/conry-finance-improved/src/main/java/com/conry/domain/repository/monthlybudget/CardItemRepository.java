package com.conry.domain.repository.monthlybudget;

import com.conry.domain.model.monthlybudget.CardItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CardItemRepository implements PanacheRepository<CardItem> {
}
