package fghost.carview.v1.accesGroup;

import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.v1.accesGroup.domain.Permission;
import fghost.carview.v1.accesGroup.model.request.AccessGroupRequestInput;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponse;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponseSummary;
import fghost.carview.v1.accesGroup.model.response.AccessGroupResponseWithProfiles;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/access-group")
public class AccessGroupController {
    private final AccessGroupFacade facade;

    @GetMapping
    public ResponseEntity<DefaultWrapper> findAll(
            @RequestParam(name = "nome", defaultValue = "") String name,
            PaginationRequest paginationRequest) {
        var body = facade.findAll(paginationRequest, name);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessGroupResponse> findByCode(@PathVariable(name = "id") String code) {
        var body = facade.findByCode(code);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Collection<AccessGroupResponseSummary>> insert(
            @Valid @RequestBody List<AccessGroupRequestInput> input) {
        var body = facade.insert(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccessGroupResponseWithProfiles> update(
            @PathVariable(name = "id") String code,
            @Valid @RequestBody AccessGroupRequestInput input) {
        var body = facade.update(code, input);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}/permissoes")
    public ResponseEntity<Set<Permission>> updatePermissions(
            @PathVariable(name = "id") String code,
            @RequestBody @Valid Set<Permission> permissions) {
        var body = facade.updatePermissions(code, permissions);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String code) {
        facade.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }
}
