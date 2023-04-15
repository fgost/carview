package fghost.carview.v1.car;

import fghost.carview.utils.dto.OnlyCodeDto;
import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.v1.car.model.request.CarRequest;
import fghost.carview.v1.car.model.response.CarResponse;
import fghost.carview.v1.car.model.response.CarResponseCategory;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/carview/v1/car")
@Validated
public class CarController {
    private CarFacade carFacade;

    @GetMapping
    public ResponseEntity<DefaultWrapper> findAll(
            @RequestParam(name = "carModel", defaultValue = "") String carModel,
            @RequestParam(name = "year", defaultValue = "") String year,
            PaginationRequest paginationRequest) {
         var response = carFacade.findAll(paginationRequest, carModel, year);
         return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> findByCode(@PathVariable(name = "id") String code) {
        var response = carFacade.findByCode(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/categories")
    public ResponseEntity<CarResponseCategory> getCategories(@PathVariable(name = "id") String code) {
        var response = carFacade.getCategories(code);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CarResponse> insert(
            @Valid @RequestBody CarRequest carRequest,
            HttpServletResponse response) {
        var dto = carFacade.insert(carRequest);
        URI uri = getURIFor(dto.getCode());
        response.addHeader(HttpHeaders.LOCATION, uri.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> update(@PathVariable(name = "id") String code,
                                              @Valid @RequestBody CarRequest carRequest) {
        var response = carFacade.update(code, carRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/categories")
    public ResponseEntity<CarResponse> updateCategory(@PathVariable(name = "id") String code,
                                                  @Valid @RequestBody Set<OnlyCodeDto> ids) {
        var response = carFacade.updateCategory(code, ids);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCode(@PathVariable(name = "id") String code) {
        carFacade.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }

    private URI getURIFor(String code) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{code}")
                .buildAndExpand(code)
                .toUri();
    }
}
