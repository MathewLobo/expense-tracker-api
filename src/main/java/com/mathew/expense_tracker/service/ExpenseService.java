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

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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

    public List<Map<String, Object>> getMonthlySpendin(Long userId) {
        List<Object[]> results = expenseRepository.findMonthlySpendingByUserId(userId);
        List<Map<String, Object>> monthly = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("month", row[0]);
            entry.put("total", row[1]);
            monthly.add(entry);
        }
        return monthly;
    }

    public Map<String, Object> getMonthlyCategorySpending(Long userId, String month) {
        List<Object[]> results = expenseRepository.findCategorySpendingByUserIdAndMonth(userId, month);
        Map<String, Object> categoryMap = new HashMap<>();
        for (Object[] row : results) {
            categoryMap.put((String) row[0], row[1]);
        }
        return categoryMap;
    }

    public List<Map<String, Object>> getMonthlySpendingByCategory(Long userId, String year) {
        List<Object[]> results = expenseRepository.findMonthlyCategorySpendingByYear(userId, year);
        
        String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        Map<String, Map<String, Object>> monthMap = new LinkedHashMap<>();
        for (String m : months) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("month", year + "-" + m);
            monthMap.put(year + "-" + m, entry);
        }
        
        for (Object[] row : results) {
            String month = (String) row[0];
            String category = (String) row[1];
            Object amount = row[2];
            if (monthMap.containsKey(month)) {
                monthMap.get(month).put(category, amount);
            }
        }
        
        return new ArrayList<>(monthMap.values());
    }
}