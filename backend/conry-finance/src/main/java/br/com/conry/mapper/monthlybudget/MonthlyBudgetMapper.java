package br.com.conry.mapper.monthlybudget;

import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import br.com.conry.rest.dto.monthlybudget.MonthlyBudgetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonthlyBudgetMapper {

    MonthlyBudgetMapper INSTANCE = Mappers.getMapper(MonthlyBudgetMapper.class);

    MonthlyBudgetResponseDTO toResponseDto(MonthlyBudget monthlyBudget);
    MonthlyBudget toEntity(MonthlyBudgetCreateDTO dto);
}
