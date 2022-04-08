package net.codejava.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.codejava.model.Category;
import net.codejava.model.Product;
import net.codejava.repo.CategoryRepository;
import net.codejava.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(String categoryName) {
        categoryRepository.save(new Category(null, categoryName));
    }

    @Override
    public void deleteCategory(Long id) {
        log.error(id.toString());
        Category category= categoryRepository.getCategoryById(id);
        log.error(category.toString());
        productRepository.deleteProductsByCategory(category);
        categoryRepository.deleteById(id);
    }
}
