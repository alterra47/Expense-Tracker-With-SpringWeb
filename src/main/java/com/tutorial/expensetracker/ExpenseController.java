package com.tutorial.expensetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ExpenseController {
    ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/expenses")
    public Expense addExpense(@RequestBody Expense expense){
        try {
            return this.expenseService.createExpense(expense);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        try {
            return this.expenseService.getExpenses();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/expenses/expensive/{amt}")
    public List<Expense> getExpensiveExpenses(@PathVariable BigDecimal amt){
        try{
            return this.expenseService.getExpensiveExpenses(amt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
