package com.conry.rest.controller.monthlybudget;


import com.conry.domain.model.monthlybudget.MonthlyBudget;
import com.conry.mapper.monthlybudget.MonthlyBudgetMapper;
import com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import com.conry.rest.dto.monthlybudget.MonthlyBudgetResponseDTO;
import com.conry.service.monthlybudget.MonthlyBudgetService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/montlybudget")
public class MonthlyBudgetResource {

    @Inject
    MonthlyBudgetService monthlyBudgetService;

    @POST
    public Response create(@Valid MonthlyBudgetCreateDTO monthlyBudgetCreateDTO) {
        MonthlyBudget monthlyBudgetCreated = monthlyBudgetService.create(monthlyBudgetCreateDTO);
        MonthlyBudgetResponseDTO dto = MonthlyBudgetMapper.INSTANCE.toResponseDto(monthlyBudgetCreated);

        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
}
