package fghost.carview.v1.users.mapper;

import fghost.carview.v1.storage.StorageService;
import fghost.carview.v1.users.domain.UserEntity;
import fghost.carview.v1.users.domain.UserPhotoEntity;
import fghost.carview.v1.users.model.response.*;
import fghost.carview.v1.users.model.response.permissions.UserResponsePermissionWrapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserResponseMapper {

    UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    @Mapping(target = "nomeCompleto", expression = "java(entity.getNomeCompleto())")
    UserResponseSummary mapEntityToResponse(UserEntity entity);

    @Mapping(target = "fullName", expression = "java(entity.getNomeCompleto())")
    UserResponseProfile mapEntityToResponseProfile(UserEntity entity);

    @Mapping(target = "fullName", expression = "java(entity.getNomeCompleto())")
    UserResponsePreference mapEntityToPreference(UserEntity entity);


    @Mapping(target = "fullName", expression = "java(entity.getNomeCompleto())")
    UserResponseCar mapEntityToCar(UserEntity entity);

    UserResponsePermissionWrapper mapEntityToPermission(UserEntity entity);

    UserResponse mapEntityToUserResponse(UserEntity entity);

    StorageService.File mapPhotoEntityToModel(UserPhotoEntity entity);

    //UsersResponseMapper mapResponseMapperToModelO(UserEntity entity);
}
