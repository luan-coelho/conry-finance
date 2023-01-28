package br.com.conry.rest.controller.monthlybudget;

import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import br.com.conry.mapper.monthlybudget.MonthlyBudgetMapper;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetResponseDTO;
import br.com.conry.service.monthlybudget.MonthlyBudgetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/monthlybudget")
public class MonthlyBudgetController {

    private final MonthlyBudgetService monthlyBudgetService;

    @PostMapping
    public ResponseEntity<MonthlyBudgetResponseDTO> create(@RequestBody @Valid MonthlyBudgetCreateDTO monthlyBudgetCreateDTO) {
        MonthlyBudget monthlyBudgetCreated = monthlyBudgetService.create(monthlyBudgetCreateDTO);
        MonthlyBudgetResponseDTO dto = MonthlyBudgetMapper.INSTANCE.toResponseDto(monthlyBudgetCreated);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
}
