package com.conry.rest.dto.carditem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class CardItemResponseDTO {

    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime eventDateTime;
}
