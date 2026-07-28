package com.mathew.expense_tracker.config;

import com.mathew.expense_tracker.model.Category;
import com.mathew.expense_tracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.findByIsDefaultTrue().isEmpty()) {
            List<String> defaults = List.of(
                "Food", "Transport", "Housing",
                "Entertainment", "Health", "Shopping",
                "Education", "Other"
            );
            for (String name : defaults) {
                Category category = new Category();
                category.setName(name);
                category.setDefault(true);
                category.setUser(null);
                categoryRepository.save(category);
            }
            System.out.println("Default categories seeded!");
        }
    }
}