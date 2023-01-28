package br.com.conry.domain.repository;

import br.com.conry.domain.model.monthlybudget.MonthlyBudget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget, Long> {

    Optional<MonthlyBudget> findByDescriptionEqualsIgnoreCase(String description);

    boolean existsByDescriptionEqualsIgnoreCase(String description);
}
