package com.mathew.expense_tracker.controller;

import com.mathew.expense_tracker.model.Category;
import com.mathew.expense_tracker.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Category>> getCategories(@PathVariable Long userId) {
        return ResponseEntity.ok(categoryService.getCategoriesForUser(userId));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Category> createCategory(@PathVariable Long userId,
                                                    @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(categoryService.createCustomCategory(body.get("name"), userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}