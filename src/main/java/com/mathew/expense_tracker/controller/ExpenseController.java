package com.mathew.expense_tracker.controller;

import com.mathew.expense_tracker.model.Expense;
import com.mathew.expense_tracker.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.createExpense(expense));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpensesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getExpensesByUserId(userId));
    }

    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<Map<String, BigDecimal>> getSummary(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getSummaryByCategory(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return ResponseEntity.ok(expenseService.updateExpense(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}/monthly")
    public ResponseEntity<List<Map<String, Object>>> getMonthlySpending(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getMonthlySpendin(userId));
    }

    @GetMapping("/user/{userId}/monthly-category")
    public ResponseEntity<Map<String, Object>> getMonthlyCategorySpending(
            @PathVariable Long userId,
            @RequestParam String month) {
        return ResponseEntity.ok(expenseService.getMonthlyCategorySpending(userId, month));
    }

    @GetMapping("/user/{userId}/monthly-stacked")
    public ResponseEntity<List<Map<String, Object>>> getMonthlySpendingByCategory(
            @PathVariable Long userId,
            @RequestParam String year) {
        return ResponseEntity.ok(expenseService.getMonthlySpendingByCategory(userId, year));
    }
}