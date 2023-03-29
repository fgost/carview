package fghost.carview.v1.car;

import fghost.carview.config.properties.ApplicationProperties;
import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.v1.car.model.CarRequest;
import fghost.carview.v1.car.model.CarResponse;
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

    @PostMapping
    public ResponseEntity<CarResponse> insert(
            @Valid @RequestBody CarRequest carRequest,
            HttpServletResponse response) {
        var dto = carFacade.insert(carRequest);
        URI uri = getURIFor(dto.getCode());
        response.addHeader(HttpHeaders.LOCATION, uri.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    private URI getURIFor(String code) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{code}")
                .buildAndExpand(code)
                .toUri();
    }
}
