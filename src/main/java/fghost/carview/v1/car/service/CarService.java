package fghost.carview.v1.car.service;

import fghost.carview.domain.Constants;
import fghost.carview.exception.domain.ObjectNotFoundException;
import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.utils.dto.OnlyCodeDto;
import fghost.carview.v1.car.domain.CarEntity;
import fghost.carview.v1.car.repository.CarRepository;
import fghost.carview.v1.category.domain.CategoryEntity;
import fghost.carview.v1.category.service.CategoryService;
import fghost.carview.v1.users.domain.UserEntity;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CarService {
    private final CarRepository repository;

    private final CategoryService categoryService;

    public Page<CarEntity> findAll(Pageable pageable, String carModel, String year) {
        boolean hasCarModel = carModel != null && !carModel.isBlank();
        boolean hasYear = year != null && !year.isBlank();
        boolean hasBoth = hasCarModel && hasYear;
        boolean noOne = !hasYear && !hasCarModel;

        if (hasBoth)
            return repository.findByCarModelContainingIgnoreCaseOrYearContainingIgnoreCase(pageable, carModel, year);
        if (noOne)
            return repository.findAll(pageable);
        if (hasYear)
            return repository.findByYearContainingIgnoreCase(pageable, year);
        if (hasCarModel)
            return repository.findByCarModelContainingIgnoreCase(pageable, carModel);

        return repository.findAll(pageable);
    }

    public CarEntity findByCode(String code) {
        var entity = repository.findByCode(code)
                .orElseThrow(() -> new ObjectNotFoundException(Constants.CAR_NOT_FOUND));
        return entity;
    }

    @Transactional
    public CarEntity insert(CarEntity carEntity) {
        try{
            return repository.save(carEntity);
        }catch (DataIntegrityViolationException ex) {
            throw ExceptionUtils.buildSameIdentifierException(Constants.CAR_DUPLICATED);
        }
    }

    @Transactional
    public CarEntity update(String code, CarEntity entity) {
        var existentEntity = findByCode(code);
        existentEntity.setCode(code);
        existentEntity.setCarModel(entity.getCarModel());
        existentEntity.setType(entity.getType());
        existentEntity.setColor(entity.getColor());
        existentEntity.setAutoMaker(entity.getAutoMaker());
        existentEntity.setYear(entity.getYear());
        var dto = existentEntity;
        try {
            return repository.save(dto);
        }catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.CAR_NOT_PERSISTED);
        }
    }

    @Transactional
    public CarEntity updateCategory(String code, Set<OnlyCodeDto> inputList) {
        var entity = findByCode(code);
        entity.getCategories().clear();
        List<CategoryEntity> categoriesToAdd = new ArrayList<>();
        inputList.forEach(input -> {
            var category = categoryService.findByCode(input.getCode());
            if (!categoriesToAdd.contains(category)) {
                categoriesToAdd.add(category);
            }
        });
        entity.setCategories(categoriesToAdd);
        return repository.save(entity);
    }

    @Transactional
    public void deleteByCode(String code) {
        try{
            var entity = findByCode(code);
            repository.delete(entity);
        }catch (Exception e) {
            throw ExceptionUtils.buildNotPersistedException(Constants.CAR_DELETION_ERROR);
        }
    }
}
