package fghost.carview.v1.category.model.request;

import fghost.carview.v1.category.domain.CategoryEnum;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @ApiModelProperty(value = "Category", required = true, dataType = "fghost.carview.v1.category.TypeEnum")
    @NotBlank(message = "{category.not.null}")
    private CategoryEnum category;
}
