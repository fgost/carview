package fghost.carview.v1.authentication.Controllers;

import fghost.carview.v1.authentication.model.request.AuthenticationRequestDto;
import fghost.carview.v1.authentication.model.response.AuthenticationResponseDto;
import fghost.carview.v1.authentication.services.AuthenticationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carview/v1/authentication")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "Authentication Controller")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody AuthenticationRequestDto requestDto) {
        AuthenticationResponseDto authentication = authenticationService.authenticate(requestDto);
        return ResponseEntity.ok(authentication);
    }
}
