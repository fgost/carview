package fghost.carview.v1.category.mapper;

import fghost.carview.v1.category.domain.CategoryEntity;
import fghost.carview.v1.category.model.request.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryRequestMapper {
    CategoryRequestMapper INSTANCE = Mappers.getMapper(CategoryRequestMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    CategoryEntity mapModelToEntity(CategoryRequest request);
}
