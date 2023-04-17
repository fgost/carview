package fghost.carview.v1.category.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import fghost.carview.v1.category.domain.CategoryEnum;
import fghost.carview.v1.category.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseType {
    @JsonProperty(value = "categoryName")
    private CategoryEnum category;
    @JsonProperty(value = "types")
    private Set<Type> types;
}
