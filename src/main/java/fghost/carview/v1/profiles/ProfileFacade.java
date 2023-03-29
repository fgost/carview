package fghost.carview.v1.profiles;

import fghost.carview.utils.dto.OnlyCodeDto;
import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.utils.pagination.PaginationUtils;
import fghost.carview.v1.profiles.mapper.ProfileRequestMapper;
import fghost.carview.v1.profiles.mapper.ProfileResponseMapper;
import fghost.carview.v1.profiles.model.request.ProfileRequestInput;
import fghost.carview.v1.profiles.model.response.ProfileResponseSummary;
import fghost.carview.v1.profiles.model.response.ProfileResponseWithAccessGroups;
import fghost.carview.v1.profiles.model.response.ProfileResponseWithAccessGroupsAndPermissions;
import fghost.carview.v1.profiles.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProfileFacade {

    private final ProfileService service;
    private final PaginationUtils paginationUtils;

    public ProfileResponseSummary insert(ProfileRequestInput input) {
        var entity = ProfileRequestMapper.INSTANCE.mapModelToEntity(input);
        var savedEntity = service.insert(entity);
        var dto = ProfileResponseMapper.INSTANCE.mapEntityToResponseSummary(savedEntity);
        return dto;
    }

    public ProfileResponseWithAccessGroups update(String code, ProfileRequestInput input) {
        var entity = ProfileRequestMapper.INSTANCE.mapModelToEntity(input);
        var updatedEntity = service.update(code, entity);
        var dto = ProfileResponseMapper.INSTANCE.mapEntityToResponseWithAccessGroups(updatedEntity);
        return dto;
    }

    public DefaultWrapper findAll(PaginationRequest paginationRequest, String name) {
        Pageable pageable = paginationUtils.getPageableWithUserPreferencesSupport(paginationRequest);
        var entities = service.findAll(pageable, name);
        var dtos = ProfileResponseMapper.INSTANCE.mapEntityToModelWithAccessGroupsCollection(entities.getContent());
        var page = PaginationUtils.pageOf(List.copyOf(dtos), pageable, entities.getTotalElements());
        var wrapper = new DefaultWrapper(page);
        return wrapper;
    }

    public ProfileResponseWithAccessGroupsAndPermissions profileResponseWithAccessGroupsAndPermissions(String code){
        var profileById = service.findByCode(code);
        var response = ProfileResponseMapper.INSTANCE.mapEntityToModelSummary(profileById);
        return response;
    }

    public ProfileResponseWithAccessGroupsAndPermissions updateProfileAccessGroups(String code, List<OnlyCodeDto> accessGroups) {
        var savedEntity = service.updateProfileAccessGroups(code, accessGroups);
        var dto = ProfileResponseMapper.INSTANCE.mapEntityToModelSummary(savedEntity);
        return dto;
    }

    public void deleteByCode(String code) {
        service.deleteByCode(code);
    }
}
