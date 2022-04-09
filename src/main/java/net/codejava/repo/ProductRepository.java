package net.codejava.repo;

import net.codejava.model.Category;
import net.codejava.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
    List<Product> getProductsByCategory(Category category);
    //void deleteProductsByCategory(Category category);
    int deleteProductsByCategory(Category category);
    void deleteProductById(Long id);
}
