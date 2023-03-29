package fghost.carview.v1.car.mapper;

import fghost.carview.v1.car.domain.CarEntity;
import fghost.carview.v1.car.model.CarResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarResponseMapper {
    CarResponseMapper INSTANCE = Mappers.getMapper(CarResponseMapper.class);

    CarResponse mapEntityToResponse(CarEntity entity);

    CarResponse mapEntityToCarResponse(CarEntity entity);
}
