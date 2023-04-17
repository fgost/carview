package fghost.carview.v1.type;

import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.v1.type.domain.TypeEnum;
import fghost.carview.v1.type.model.TypeResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/carview/v1/type")
@Validated
public class TypeController {
    private TypeFacade typeFacade;

    @GetMapping
    public ResponseEntity<DefaultWrapper> findAll(
            @RequestParam(name = "typeName", defaultValue = "") TypeEnum typeName,
            PaginationRequest paginationRequest) {
        var response = typeFacade.findAll(paginationRequest, typeName);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponse> findByCode(@PathVariable(name = "id") String code) {
        var response = typeFacade.findByCode(code);
        return ResponseEntity.ok(response);
    }

//    @PostMapping
//    public ResponseEntity<TypeResponse> insert(
//            @Valid @RequestBody TypeRequest typeRequest,
//            HttpServletResponse response) {
//        var dto = typeFacade.insert(typeRequest);
//        URI uri = getURIFor(dto.getCode());
//        response.addHeader(HttpHeaders.LOCATION, uri.toString());
//        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<TypeResponse> update(@PathVariable(name = "id") String code,
//                                               @Valid @RequestBody TypeRequest typeRequest) {
//        var response = typeFacade.update(code, typeRequest);
//        return ResponseEntity.ok(response);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteByCode(@PathVariable(name = "id") String code) {
//        typeFacade.deletedByCode(code);
//        return ResponseEntity.noContent().build();
//    }
//
//    private URI getURIFor(String code) {
//        return ServletUriComponentsBuilder
//                .fromCurrentRequestUri()
//                .path("/{code}")
//                .buildAndExpand(code)
//                .toUri();
//    }
}
