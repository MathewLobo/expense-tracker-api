package com.mathew.expense_tracker.service;

import com.mathew.expense_tracker.model.Category;
import com.mathew.expense_tracker.model.User;
import com.mathew.expense_tracker.repository.CategoryRepository;
import com.mathew.expense_tracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public List<Category> getCategoriesForUser(Long userId) {
        List<Category> all = new ArrayList<>();
        all.addAll(categoryRepository.findByIsDefaultTrue());
        all.addAll(categoryRepository.findByUserId(userId));
        return all;
    }

    public Category createCustomCategory(String name, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Category category = new Category();
        category.setName(name);
        category.setDefault(false);
        category.setUser(user);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}