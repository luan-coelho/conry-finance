package com.conry.service.monthlybudget;

import com.conry.domain.model.monthlybudget.CardItem;
import com.conry.domain.repository.monthlybudget.CardItemRepository;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@RequiredArgsConstructor
@ApplicationScoped
public class CardItemService {

    final CardItemRepository cardRepository;

    public CardItem findById(Long id) {
        return cardRepository.findByIdOptional(id)
                .orElseThrow(() -> new IllegalArgumentException("CardItem not found by id"));
    }
}
