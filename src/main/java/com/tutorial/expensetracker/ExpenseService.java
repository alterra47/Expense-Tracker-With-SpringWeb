package com.tutorial.expensetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepo expenseRepo;
    @Autowired
    public ExpenseService(ExpenseRepo expenseRepo){
        this.expenseRepo = expenseRepo;
    }
    public Expense createExpense(Expense expense){
        try{
            return this.expenseRepo.save(expense);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Expense> getExpenses(){
        try{
            return this.expenseRepo.findAllByOrderByDateTimeDesc();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Expense> getExpensiveExpenses(BigDecimal amt){
        try{
            return this.expenseRepo.findByAmountGreaterThan(amt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
