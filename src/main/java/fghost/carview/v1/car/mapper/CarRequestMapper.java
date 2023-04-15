package fghost.carview.v1.car.mapper;

import fghost.carview.v1.car.domain.CarEntity;
import fghost.carview.v1.car.model.request.CarRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarRequestMapper {
    CarRequestMapper INSTANCE = Mappers.getMapper(CarRequestMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    CarEntity mapModelToEntity(CarRequest request);
}
