package fghost.carview.v1.car.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fghost.carview.v1.car.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseCategory {

    @JsonProperty(value = "carName")
    private String carModel;
    @JsonProperty(value = "categories")
    private Set<Category> categories;
}
