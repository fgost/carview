package fghost.carview.v1.profiles;

import fghost.carview.utils.dto.OnlyCodeDto;
import fghost.carview.utils.pagination.DefaultWrapper;
import fghost.carview.utils.pagination.PaginationRequest;
import fghost.carview.v1.profiles.model.request.ProfileRequestInput;
import fghost.carview.v1.profiles.model.response.ProfileResponseSummary;
import fghost.carview.v1.profiles.model.response.ProfileResponseWithAccessGroups;
import fghost.carview.v1.profiles.model.response.ProfileResponseWithAccessGroupsAndPermissions;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/carview/v1/profile")
public class ProfileController {
    private final ProfileFacade facade;

    @GetMapping
    public ResponseEntity<DefaultWrapper> findAll(
            @RequestParam(name = "nome", defaultValue = "") String name,
            PaginationRequest paginationRequest) {
        var body = facade.findAll(paginationRequest, name);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseWithAccessGroupsAndPermissions> findByCode(
            @PathVariable(name = "id") String code) {
        var body = facade.profileResponseWithAccessGroupsAndPermissions(code);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<ProfileResponseSummary> create(@Valid @RequestBody ProfileRequestInput input) {
        var body = facade.insert(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseWithAccessGroups> update(
            @PathVariable(name = "id") String code,
            @RequestBody @Valid ProfileRequestInput input) {
        var body = facade.update(code, input);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}/access-group")
    @ResponseStatus(value = HttpStatus.OK)
    public ProfileResponseWithAccessGroupsAndPermissions updateProfileAccessGroups(
            @PathVariable(name = "id") String code,
            @Valid @RequestBody List<OnlyCodeDto> inputList) {
        return facade.updateProfileAccessGroups(code, inputList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") String code) {
        facade.deleteByCode(code);
        return ResponseEntity.noContent().build();
    }
}
