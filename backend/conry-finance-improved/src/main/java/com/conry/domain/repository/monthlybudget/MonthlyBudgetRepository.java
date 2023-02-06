package com.conry.domain.repository.monthlybudget;

import com.conry.commons.Pagination;
import com.conry.domain.model.monthlybudget.MonthlyBudget;
import com.conry.rest.dto.pagination.Pageable;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class MonthlyBudgetRepository implements PanacheRepository<MonthlyBudget> {

    Optional<MonthlyBudget> findByDescriptionEqualsIgnoreCase(String description) {
        return find("description ILIKE ?1", description).singleResultOptional();
    }

    public boolean existsByDescriptionEqualsIgnoreCase(String description) {
        return count("description LIKE ?1", description) > 0;
    }

    public Pagination<MonthlyBudget> listAllPaginated(Pageable pageable) {
        PanacheQuery<MonthlyBudget> list = findAll();
        List<MonthlyBudget> monthlyBudgets = list.page(Page.of(pageable.getPage(), pageable.getSize())).list();

        return Pagination.of(monthlyBudgets, pageable.getPage(), pageable.getSize(), count());
    }
}
