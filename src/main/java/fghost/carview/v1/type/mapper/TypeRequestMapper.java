package fghost.carview.v1.type.mapper;

import fghost.carview.v1.type.domain.TypeEntity;
import fghost.carview.v1.type.model.TypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeRequestMapper {
    TypeRequestMapper INSTANCE = Mappers.getMapper(TypeRequestMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    TypeEntity mapModelToEntity(TypeRequest request);
}
