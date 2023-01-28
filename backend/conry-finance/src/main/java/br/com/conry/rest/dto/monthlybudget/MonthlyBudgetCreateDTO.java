package br.com.conry.rest.dto.monthlybudget;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class MonthlyBudgetCreateDTO {

    @NotBlank(message = "Enter the description field")
    private String description;
    @NotNull(message = "Enter the period field")
    @FutureOrPresent(message = "Enter this month's period or later")
    private LocalDate period;
}
