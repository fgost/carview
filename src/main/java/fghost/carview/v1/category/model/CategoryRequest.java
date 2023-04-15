package fghost.carview.v1.category.model;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @ApiModelProperty(value = "Category Name", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{category.name.not.null}")
    @Size(max = 100, message = "{category.name.max.size}")
    private String categoryName;
}
