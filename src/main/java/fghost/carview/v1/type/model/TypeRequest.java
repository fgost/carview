package fghost.carview.v1.type.model;

import fghost.carview.v1.type.domain.TypeEnum;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeRequest {
    @ApiModelProperty(value = "Type Name", required = true, dataType = "fghost.carview.v1.type.TypeEnum")
    @NotNull(message = "{type.name.not.null}")
    private TypeEnum typeName;
}
