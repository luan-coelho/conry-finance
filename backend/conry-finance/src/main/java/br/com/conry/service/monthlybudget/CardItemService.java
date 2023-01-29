package br.com.conry.service.monthlybudget;

import br.com.conry.domain.model.monthlybudget.Card;
import br.com.conry.domain.model.monthlybudget.CardItem;
import br.com.conry.domain.repository.monthlybudget.CardItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CardItemService {

    final CardItemRepository cardRepository;

    public CardItem findById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CardItem not found by id"));
    }
}
