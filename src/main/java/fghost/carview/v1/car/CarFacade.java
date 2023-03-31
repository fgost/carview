package fghost.carview.v1.car;

import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.utils.pagination.PaginationUtils;
import fghost.carview.v1.car.mapper.CarRequestMapper;
import fghost.carview.v1.car.mapper.CarResponseMapper;
import fghost.carview.v1.car.model.CarRequest;
import fghost.carview.v1.car.model.CarResponse;
import fghost.carview.v1.car.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CarFacade {
    private final CarService carService;
    private final PaginationUtils paginationUtils;

    public DefaultWrapper findAll(PaginationRequest paginationRequest, String carModel, String year) {
        Pageable pageable = paginationUtils.getPageableWithUserPreferencesSupport(paginationRequest);
        var entities = carService.findAll(pageable, carModel, year);
        var dtos = entities.getContent().stream().map(CarResponseMapper.INSTANCE::mapEntityToResponse).collect(Collectors.toList());
        var page = PaginationUtils.pageOf(List.copyOf(dtos), pageable, entities.getTotalElements());
        var wrapper = new DefaultWrapper(page);
        return wrapper;
    }

    public CarResponse findByCode(String code) {
        var entity = carService.findByCode(code);
        var dto = CarResponseMapper.INSTANCE.mapEntityToCarResponse(entity);
        return dto;
    }

    public CarResponse insert(CarRequest input) {
        var entity = CarRequestMapper.INSTANCE.mapModelToEntity(input);
        var savedEntity = carService.insert(entity);
        var dto = CarResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public CarResponse update(String code, CarRequest request) {
        var entity = CarRequestMapper.INSTANCE.mapModelToEntity(request);
        var savedEntity = carService.update(code, entity);
        var dto = CarResponseMapper.INSTANCE.mapEntityToResponse(savedEntity);
        return dto;
    }

    public void deleteByCode(String code) {
        carService.deleteByCode(code);
    }

}
