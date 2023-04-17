package fghost.carview.v1.category.service;

import fghost.carview.domain.Constants;
import fghost.carview.exception.domain.ObjectNotFoundException;
import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.v1.category.domain.CategoryEntity;
import fghost.carview.v1.category.domain.CategoryEnum;
import fghost.carview.v1.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository repository;

    public Page<CategoryEntity> findAll(Pageable pageable, CategoryEnum category) {
        boolean hasName = category != null;
        if (hasName)
            return repository.findByCategoryContainingIgnoreCase(pageable, category);

        return repository.findAll(pageable);
    }

    public CategoryEntity findByCode(String code) {
        var entity = repository.findByCode(code)
                .orElseThrow(() -> new ObjectNotFoundException(Constants.CATEGORY_NOT_FOUND));
        return entity;
    }

    @Transactional
    public CategoryEntity insert(CategoryEntity categoryEntity) {
        try{
            return repository.save(categoryEntity);
        }catch (DataIntegrityViolationException ex) {
            throw ExceptionUtils.buildSameIdentifierException(Constants.CATEGORY_DUPLICATED);
        }
    }

    @Transactional
    public CategoryEntity update(String code, CategoryEntity entity) {
        var existentEntity = findByCode(code);
        existentEntity.setCode(code);
        existentEntity.setCategory(entity.getCategory());

        var dto = existentEntity;
        try {
            return repository.save(dto);
        }catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.CATEGORY_NOT_PERSISTED);
        }
    }

    @Transactional
    public void deleteByCode(String code) {
        try{
            var entity = findByCode(code);
            repository.delete(entity);
        }catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.CATEGORY_DELETION_ERROR);
        }
    }
}
