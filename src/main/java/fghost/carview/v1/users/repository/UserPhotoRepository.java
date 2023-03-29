package fghost.carview.v1.users.repository;

import fghost.carview.v1.users.domain.UserPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPhotoRepository extends JpaRepository<UserPhotoEntity, Long> {

    Optional<UserPhotoEntity> findByUserId(Long id);
}
