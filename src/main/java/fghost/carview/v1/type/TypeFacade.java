package fghost.carview.v1.type;

import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.utils.pagination.PaginationUtils;
import fghost.carview.v1.type.domain.TypeEnum;
import fghost.carview.v1.type.mapper.TypeRequestMapper;
import fghost.carview.v1.type.mapper.TypeResponseMapper;
import fghost.carview.v1.type.model.TypeRequest;
import fghost.carview.v1.type.model.TypeResponse;
import fghost.carview.v1.type.service.TypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class TypeFacade {
    private final TypeService typeService;
    private final PaginationUtils paginationUtils;

    public DefaultWrapper findAll(PaginationRequest paginationRequest, TypeEnum typeName) {
        Pageable pageable = paginationUtils.getPageableWithUserPreferencesSupport(paginationRequest);
        var entities = typeService.findAll(pageable, typeName);
        var dtos = entities.getContent().stream().map(TypeResponseMapper.INSTANCE::mapEntityToResponse).collect(Collectors.toList());
        var page = PaginationUtils.pageOf(List.copyOf(dtos), pageable, entities.getTotalElements());
        var wrapper = new DefaultWrapper(page);
        return wrapper;
    }

    public TypeResponse findByCode(String code) {
        var entity = typeService.findByCode(code);
        var dto = TypeResponseMapper.INSTANCE.mapEntityToTypeResponse(entity);
        return dto;
    }

    public TypeResponse insert(TypeRequest input) {
        var entity = TypeRequestMapper.INSTANCE.mapModelToEntity(input);
        var savedEntity = typeService.insert(entity);
        var dto = TypeResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public TypeResponse update(String code, TypeRequest request) {
        var entity = TypeRequestMapper.INSTANCE.mapModelToEntity(request);
        var savedEntity = typeService.update(code, entity);
        var dto = TypeResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public void deletedByCode(String code) {
        typeService.deleteByCode(code);
    }
}
