package com.conry.rest.resource.monthlybudget;

import com.conry.commons.Pagination;
import com.conry.domain.model.monthlybudget.MonthlyBudget;
import com.conry.mapper.monthlybudget.MonthlyBudgetMapper;
import com.conry.rest.dto.monthlybudget.MonthlyBudgetCreateDTO;
import com.conry.rest.dto.monthlybudget.MonthlyBudgetResponseDTO;
import com.conry.rest.dto.pagination.Pageable;
import com.conry.service.monthlybudget.MonthlyBudgetService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/montlybudget")
public class MonthlyBudgetResource {

    @Inject
    MonthlyBudgetService monthlyBudgetService;

    @GET
    public Response findAll(Pageable pageable) {
        Pagination<MonthlyBudget> monthlyBudgets = monthlyBudgetService.findAll(pageable);

        return Response.ok(monthlyBudgets).build();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") Long id) {
        MonthlyBudget monthlyBudget = monthlyBudgetService.findById(id);
        MonthlyBudgetResponseDTO dto = MonthlyBudgetMapper.INSTANCE.toResponseDto(monthlyBudget);

        return Response.status(Response.Status.CREATED).entity(dto).build();
    }

    @POST
    public Response create(@Valid MonthlyBudgetCreateDTO monthlyBudgetCreateDTO) {
        MonthlyBudget monthlyBudgetCreated = monthlyBudgetService.create(monthlyBudgetCreateDTO);
        MonthlyBudgetResponseDTO dto = MonthlyBudgetMapper.INSTANCE.toResponseDto(monthlyBudgetCreated);

        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
}
