package com.mathew.expense_tracker.repository;

import com.mathew.expense_tracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);

    @Query("SELECT FUNCTION('TO_CHAR', e.date, 'YYYY-MM') as month, SUM(e.amount) as total FROM Expense e WHERE e.user.id = :userId GROUP BY FUNCTION('TO_CHAR', e.date, 'YYYY-MM') ORDER BY month")
    List<Object[]> findMonthlySpendingByUserId(@Param("userId") Long userId);

    @Query("SELECT e.category, SUM(e.amount) FROM Expense e WHERE e.user.id = :userId AND FUNCTION('TO_CHAR', e.date, 'YYYY-MM') = :month GROUP BY e.category")
    List<Object[]> findCategorySpendingByUserIdAndMonth(@Param("userId") Long userId, @Param("month") String month);

    @Query("SELECT FUNCTION('TO_CHAR', e.date, 'YYYY-MM') as month, e.category, SUM(e.amount) as total FROM Expense e WHERE e.user.id = :userId AND FUNCTION('TO_CHAR', e.date, 'YYYY') = :year GROUP BY FUNCTION('TO_CHAR', e.date, 'YYYY-MM'), e.category ORDER BY month")
    List<Object[]> findMonthlyCategorySpendingByYear(@Param("userId") Long userId, @Param("year") String year);
}