package fghost.carview.v1.users.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fghost.carview.v1.car.model.CarResponse;
import fghost.carview.v1.profiles.model.response.ProfileResponseWithAccessGroupsAndPermissions;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @JsonProperty("idExterno")
    private String code;
    private String nomeCompleto;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("sobrenome")
    private String lastName;
    private String email;
    @JsonProperty("perfis")
    private Set<ProfileResponseWithAccessGroupsAndPermissions> profiles = new HashSet<>();
    @JsonProperty("cars")
    private Set<CarResponse> cars = new HashSet<>();
}
