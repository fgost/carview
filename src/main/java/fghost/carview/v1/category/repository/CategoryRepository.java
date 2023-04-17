package fghost.carview.v1.category.repository;

import fghost.carview.v1.category.domain.CategoryEntity;
import fghost.carview.v1.category.domain.CategoryEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByCode(String code);
    Page<CategoryEntity> findAll(Pageable pageable);
    Page<CategoryEntity> findByCategoryContainingIgnoreCase(Pageable pageable, CategoryEnum category);
    @Query("SELECT id FROM CategoryEntity")
    List<Integer> findAllIds();
}
