package com.conry.mapper.monthlybudget;

import com.conry.domain.model.monthlybudget.MonthlyBudget;
import com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import com.conry.rest.dto.monthlybudget.MonthlyBudgetResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.inject.Singleton;

@Mapper
public interface MonthlyBudgetMapper {

    @Singleton
    MonthlyBudgetMapper INSTANCE = Mappers.getMapper(MonthlyBudgetMapper.class);

    MonthlyBudgetResponseDTO toResponseDto(MonthlyBudget monthlyBudget);

    MonthlyBudget toEntity(MonthlyBudgetCreateDTO dto);
}
