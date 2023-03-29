package fghost.carview.v1.accesGroup;

import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.utils.pagination.PaginationUtils;
import fghost.carview.v1.accesGroup.domain.Permission;
import fghost.carview.v1.accesGroup.mapper.AccessGroupRequestMapper;
import fghost.carview.v1.accesGroup.mapper.AccessGroupResponseMapper;
import fghost.carview.v1.accesGroup.model.request.AccessGroupRequestInput;
import fghost.carview.v1.accesGroup.model.request.AccessGroupRequestInputPermissions;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponse;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponseSummary;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponseWithProfiles;
import fghost.carview.v1.accesGroup.service.AccessGroupService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class AccessGroupFacade {
    private final AccessGroupService service;
    private final PaginationUtils paginationUtils;

    public DefaultWrapper findAll(PaginationRequest paginationRequest, String name) {
        Pageable pageable = paginationUtils.getPageableWithUserPreferencesSupport(paginationRequest);
        var entities = service.findAll(pageable, name);
        var dtos = AccessGroupResponseMapper.INSTANCE.mapEntityToModelCollection(entities.getContent());
        var page = new PageImpl<>(List.copyOf(dtos), entities.getPageable(), entities.getTotalElements());
        var wrapper = new DefaultWrapper(page);
        return wrapper;
    }

    public AccessGroupResponse findByCode(String code) {
        var entity = service.findByCode(code);
        var dto = AccessGroupResponseMapper.INSTANCE.mapEntityToResponse(entity);
        return dto;
    }

    public Collection<AccessGroupResponseSummary> insert(List<AccessGroupRequestInput> inputList) {
        var entities = AccessGroupRequestMapper.INSTANCE.mapModelToEntityCollection(inputList);
        var savedEntities = service.insert(List.copyOf(entities));
        var dtos = AccessGroupResponseMapper.INSTANCE.mapEntityToResponseSummaryCollection(savedEntities);
        return dtos;
    }

    public AccessGroupResponseWithProfiles update(String code, AccessGroupRequestInput input) {
        var entity = AccessGroupRequestMapper.INSTANCE.mapModelToEntity(input);
        var updatedEntity = service.update(code, entity);
        return AccessGroupResponseMapper.INSTANCE.mapEntityToModel(updatedEntity);
    }

    public Set<Permission> updatePermissions(String code, Set<Permission> permissions) {
        var permissionsInput = new AccessGroupRequestInputPermissions();
        permissionsInput.getPermissions().addAll(permissions);

        var entity = AccessGroupRequestMapper.INSTANCE.mapModelToEntity(permissionsInput);
        var savedEntity = service.updatePermissions(code, entity);
        var dto = AccessGroupResponseMapper.INSTANCE.mapEntityToResponseWithPermissions(savedEntity);
        return dto.getPermissions();
    }

    public void deleteByCode(String code) {
        service.deleteByCode(code);
    }
}
