package fghost.carview.v1.car.repository;

import fghost.carview.v1.car.domain.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Optional<CarEntity> findByCarModel(String carModel);

    Optional<CarEntity> findByCode(String code);

    Page<CarEntity> findAll(Pageable pageable);

    Page<CarEntity> findByYearContainingIgnoreCase(Pageable pageable, String year);

    Page<CarEntity> findByCarModelContainingIgnoreCase(Pageable pageable, String carModel);

    Page<CarEntity> findByCarModelContainingIgnoreCaseOrYearContainingIgnoreCase(Pageable pageable, String carModel, String year);
}
