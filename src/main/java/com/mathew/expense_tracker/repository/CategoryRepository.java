package com.mathew.expense_tracker.repository;

import com.mathew.expense_tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByIsDefaultTrue();
    List<Category> findByUserId(Long userId);
}