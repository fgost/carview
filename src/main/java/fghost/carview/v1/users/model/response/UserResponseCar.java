package fghost.carview.v1.users.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fghost.carview.v1.users.domain.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseCar {
    @JsonProperty(value = "user")
    private String fullName;

    @JsonProperty(value = "cars")
    private Set<Car> cars;
}
