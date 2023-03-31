package fghost.carview.v1.users;

import fghost.carview.domain.Constants;
import fghost.carview.exception.domain.StorageException;
import fghost.carview.exception.util.MessageResource;
import fghost.carview.utils.dto.OnlyCodeDto;
import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.utils.pagination.PaginationUtils;
import fghost.carview.v1.storage.StorageService;
import fghost.carview.v1.users.domain.Preference;
import fghost.carview.v1.users.dto.UserDtoUpdate;
import fghost.carview.v1.users.mapper.UserRequestMapper;
import fghost.carview.v1.users.mapper.UserResponseMapper;
import fghost.carview.v1.users.model.request.UserRequest;
import fghost.carview.v1.users.model.response.UserResponse;
import fghost.carview.v1.users.model.response.UserResponsePreference;
import fghost.carview.v1.users.model.response.UserResponseSummary;
import fghost.carview.v1.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserFacade {

    private final UserService usersService;
    private final PaginationUtils paginationUtils;

    public DefaultWrapper findAll(PaginationRequest paginationRequest, String name, String email) {
        Pageable pageable = paginationUtils.getPageableWithUserPreferencesSupport(paginationRequest);
        var entities = usersService.findAll(pageable, name, email);
        var dtos = entities.getContent().stream().map(UserResponseMapper.INSTANCE::mapEntityToResponse).collect(Collectors.toList());
        var page = PaginationUtils.pageOf(List.copyOf(dtos), pageable, entities.getTotalElements());
        var wrapper = new DefaultWrapper(page);
        return wrapper;
    }

    public UserResponse findByCode(String code) {
        var entity = usersService.findByCode(code);
        var dto = UserResponseMapper.INSTANCE.mapEntityToUserResponse(entity);
        return dto;
    }

    public UserResponsePreference getPreferences(String code) {
        var entity = usersService.findByCode(code);
        var dto = UserResponseMapper.INSTANCE.mapEntityToPreference(entity);
        return dto;
    }

    public DefaultWrapper getPermissions(String code, PaginationRequest paginationRequest) {
        Pageable pageable = paginationUtils.getPageableWithUserPreferencesSupport(paginationRequest);
        var dtos = usersService.getPermissions(code, pageable);
        var page = PaginationUtils.pageOf(dtos.getContent(), pageable, dtos.getTotalElements());
        var wrapper = new DefaultWrapper(page);
        return wrapper;
    }

    public StorageService.File getProfilePhotoInfo(String code){
        var entity = usersService.getProfilePhotoInfo(code);
        var dto = UserResponseMapper.INSTANCE.mapPhotoEntityToModel(entity);
        return dto;
    }

    public StorageService.File getProfilePhoto(String code){
        var photoFile = usersService.getProfilePhoto(code);
        return photoFile;
    }

    public UserResponseSummary insert(UserRequest input) {
        var entity = UserRequestMapper.INSTANCE.mapModelToEntity(input);
        var savedEntity = usersService.insert(entity);
        var dto = UserResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public UserResponseSummary update(String code, UserDtoUpdate input) {
        var entity = UserRequestMapper.INSTANCE.updateMapModelToEntity(input);
        var savedEntity = usersService.update(code, entity);
        var dto = UserResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public UserResponsePreference updatePreferences(String code, Set<Preference> inputList) {
        var entity = usersService.updatePreferences(code, inputList);
        var dto = UserResponseMapper.INSTANCE.mapEntityToPreference(entity);
        return dto;
    }

    public UserResponse updateProfiles(String code, Set<OnlyCodeDto> inputList) {
        var entity = usersService.updateProfiles(code, inputList);
        var dto = UserResponseMapper.INSTANCE.mapEntityToUserResponse(entity);
        return dto;
    }

    public UserResponse updateCar(String code, Set<OnlyCodeDto> inputList) {
        var entity = usersService.updateCar(code, inputList);
        var dto = UserResponseMapper.INSTANCE.mapEntityToUserResponse(entity);
        return dto;
    }

    public StorageService.File saveImage(String code, MultipartFile dto) {
        try {
            var fullPath = String.format("%s_%s%s",
                    code,
                    UserService.DEFAULT_SUFFIX,
                    StorageService.getFileExtension(dto.getOriginalFilename()));

            StorageService.File file = StorageService.File.builder()
                    .fileName(fullPath)
                    .inputStream(dto.getInputStream())
                    .size(dto.getSize())
                    .contentType(dto.getContentType())
                    .build();

            var savedEntity = usersService.updatePhoto(file, code);
            return UserResponseMapper.INSTANCE.mapPhotoEntityToModel(savedEntity);

        } catch (IOException e) {
            var msg = MessageResource.getInstance().getMessage(Constants.STORAGE_NOT_PERSISTED);
            throw new StorageException(msg, e);
        }

    }

    public void deleteByCode(String code) {
        usersService.deleteByCode(code);
    }
}
