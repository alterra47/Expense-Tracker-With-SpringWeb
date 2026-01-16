package com.tutorial.expensetracker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    public List<Expense> findByAmountGreaterThan(BigDecimal amt);
    public List<Expense> findAllByOrderByDateTimeDesc();
}
