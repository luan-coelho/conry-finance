package com.conry.rest.dto.monthlybudget;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
