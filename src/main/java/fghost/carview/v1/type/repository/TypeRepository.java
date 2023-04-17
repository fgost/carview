package fghost.carview.v1.type.repository;

import fghost.carview.v1.type.domain.TypeEntity;
import fghost.carview.v1.type.domain.TypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<TypeEntity, Long> {
    Optional<TypeEntity> findByCode(String code);

    Page<TypeEntity> findAll(Pageable pageable);

    Page<TypeEntity> findByTypeNameContainingIgnoreCase(Pageable pageable, TypeEnum typeName);
}
