package net.codejava.service;

import net.codejava.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    void saveCategory(String categoryName);
    void deleteCategory(Long id);
}
