package com.tutorial.expensetracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepo expenseRepo;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    public void shouldReturnAllExpenses(){
        Expense e1 = new Expense();
        e1.setAmount(BigDecimal.valueOf(100));
        e1.setDescription("Grocery");

        Expense e2 = new Expense();
        e2.setAmount(BigDecimal.valueOf(50));
        e2.setDescription("Taxi");

        when(expenseRepo.findAllByOrderByDateTimeDesc()).thenReturn(Arrays.asList(e1, e2));
        List<Expense> result = expenseService.getExpenses();

        assertEquals(2, result.size());
        assertEquals("Grocery", result.getFirst().getDescription());
    }

    @Test
    public void shouldFilterExpensiveExpenses(){
        Expense expense = new Expense();
        expense.setAmount(BigDecimal.valueOf(200));

        when(expenseRepo.findByAmountGreaterThan(BigDecimal.valueOf(100))).thenReturn(Arrays.asList(expense));

        List<Expense> result = expenseService.getExpensiveExpenses(BigDecimal.valueOf(100));

        assertEquals(1, result.size());
        assertEquals(BigDecimal.valueOf(200), result.getFirst().getAmount());
    }
}
