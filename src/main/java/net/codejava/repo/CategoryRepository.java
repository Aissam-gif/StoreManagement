package net.codejava.repo;

import net.codejava.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryById(Long id);
}
