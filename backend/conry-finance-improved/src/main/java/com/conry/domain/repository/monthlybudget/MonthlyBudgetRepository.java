package com.conry.domain.repository.monthlybudget;

import com.conry.domain.model.monthlybudget.MonthlyBudget;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@ApplicationScoped
public class MonthlyBudgetRepository implements PanacheRepository<MonthlyBudget> {

    Optional<MonthlyBudget> findByDescriptionEqualsIgnoreCase(String description) {
        return find("description ILIKE ?1", description).singleResultOptional();
    }

    public boolean existsByDescriptionEqualsIgnoreCase(String description) {
        return count("description LIKE ?1", description) > 1;
    }
}
