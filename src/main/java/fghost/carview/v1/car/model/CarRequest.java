package fghost.carview.v1.car.model;

import fghost.carview.v1.car.domain.CarTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {
    @ApiModelProperty(value = "Car Model", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{car.model.not.null}")
    @Size(max = 100, message = "{car.model.max.size}")
    private String carModel;

    @ApiModelProperty(value = "Automaker", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{car.automaker.not.null}")
    @Size(max = 100, message = "{car.automaker.max.size}")
    private String autoMaker;

    @ApiModelProperty(value = "Year", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{car.year.not.null}")
    @Size(max = 100, message = "{car.year.max.size}")
    private String year;

    @ApiModelProperty(value = "Color", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{car.color.not.null}")
    @Size(max = 100, message = "{car.color.max.size}")
    private String color;

    @ApiModelProperty(value = "Type", required = true, dataType = "fghost.carview.v1.car.CarTypeEnum")
    @NotNull(message = "{car.type.not.null}")
    private CarTypeEnum type;
}
