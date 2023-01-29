package br.com.conry.rest.dto.card;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CardCreateDTO {

    @NotBlank(message = "Enter the description field")
    private String description;
}
