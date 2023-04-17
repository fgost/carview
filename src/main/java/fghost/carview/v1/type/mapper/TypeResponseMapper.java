package fghost.carview.v1.type.mapper;

import fghost.carview.v1.type.domain.TypeEntity;
import fghost.carview.v1.type.model.TypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeResponseMapper {
    TypeResponseMapper INSTANCE = Mappers.getMapper(TypeResponseMapper.class);

    TypeResponse mapEntityToResponse(TypeEntity entity);

    TypeResponse mapEntityToTypeResponse(TypeEntity entity);
}
