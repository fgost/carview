package fghost.carview.v1.category.repository;

import fghost.carview.v1.category.domain.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByCode(String code);
    Page<CategoryEntity> findAll(Pageable pageable);
    Page<CategoryEntity> findByCategoryNameContainingIgnoreCase(Pageable pageable, String categoryName);
}
