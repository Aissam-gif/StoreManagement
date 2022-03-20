package net.codejava.service;

import lombok.RequiredArgsConstructor;
import net.codejava.model.Category;
import net.codejava.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(String categoryName) {
        categoryRepository.save(new Category(null, categoryName, null));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
