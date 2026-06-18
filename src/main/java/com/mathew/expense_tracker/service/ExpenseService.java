package com.mathew.expense_tracker.service;

import com.mathew.expense_tracker.model.Expense;
import com.mathew.expense_tracker.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId);
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public Expense updateExpense(Long id, Expense updated) {
        return expenseRepository.findById(id).map(expense -> {
            expense.setTitle(updated.getTitle());
            expense.setAmount(updated.getAmount());
            expense.setCategory(updated.getCategory());
            expense.setDate(updated.getDate());
            return expenseRepository.save(expense);
        }).orElseThrow(() -> new RuntimeException("Expense not found"));
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public Map<String, BigDecimal> getSummaryByCategory(Long userId) {
        return expenseRepository.findByUserId(userId).stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, Expense::getAmount, BigDecimal::add)
                ));
    }
}