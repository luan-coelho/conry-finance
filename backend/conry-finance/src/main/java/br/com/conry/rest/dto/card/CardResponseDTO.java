package br.com.conry.rest.dto.card;

import br.com.conry.domain.model.enums.CardType;
import br.com.conry.rest.dto.carditem.CardItemResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class CardResponseDTO {

    private Long id;
    private String description;
    private List<CardItemResponseDTO> valueCards;
    private BigDecimal amount;
    private CardType cardType;
}
