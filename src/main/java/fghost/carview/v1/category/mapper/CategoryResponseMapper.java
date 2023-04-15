package fghost.carview.v1.category.mapper;

import fghost.carview.v1.category.domain.CategoryEntity;
import fghost.carview.v1.category.model.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryResponseMapper {
    CategoryResponseMapper INSTANCE = Mappers.getMapper(CategoryResponseMapper.class);

    CategoryResponse mapEntityToResponse(CategoryEntity entity);

    CategoryResponse mapEntityToCategoryResponse(CategoryEntity entity);
}
