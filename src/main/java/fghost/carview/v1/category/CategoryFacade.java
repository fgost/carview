package fghost.carview.v1.category;

import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.utils.pagination.PaginationUtils;
import fghost.carview.v1.category.domain.CategoryEnum;
import fghost.carview.v1.category.mapper.CategoryRequestMapper;
import fghost.carview.v1.category.mapper.CategoryResponseMapper;
import fghost.carview.v1.category.model.request.CategoryRequest;
import fghost.carview.v1.category.model.response.CategoryResponse;
import fghost.carview.v1.category.model.response.CategoryResponseType;
import fghost.carview.v1.category.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CategoryFacade {
    private final CategoryService categoryService;
    private final PaginationUtils paginationUtils;

    public DefaultWrapper findAll(PaginationRequest paginationRequest, CategoryEnum category) {
        Pageable pageable = paginationUtils.getPageableWithUserPreferencesSupport(paginationRequest);
        var entities = categoryService.findAll(pageable, category);
        var dtos = entities.getContent().stream().map(CategoryResponseMapper.INSTANCE::mapEntityToResponse).collect(Collectors.toList());
        var page = PaginationUtils.pageOf(List.copyOf(dtos), pageable, entities.getTotalElements());
        var wrapper = new DefaultWrapper(page);
        return wrapper;
    }

    public CategoryResponse findByCode(String code) {
        var entity = categoryService.findByCode(code);
        var dto = CategoryResponseMapper.INSTANCE.mapEntityToCategoryResponse(entity);
        return dto;
    }

    public CategoryResponseType getTypes(String code) {
        var entity = categoryService.findByCode(code);
        var dto = CategoryResponseMapper.INSTANCE.mapEntityToType(entity);
        return dto;
    }

    public CategoryResponse insert(CategoryRequest input) {
        var entity = CategoryRequestMapper.INSTANCE.mapModelToEntity(input);
        var savedEntity = categoryService.insert(entity);
        var dto = CategoryResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public CategoryResponse update(String code, CategoryRequest request) {
        var entity = CategoryRequestMapper.INSTANCE.mapModelToEntity(request);
        var savedEntity = categoryService.update(code, entity);
        var dto = CategoryResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public void deleteByCode(String code) {
        categoryService.deleteByCode(code);
    }
}
