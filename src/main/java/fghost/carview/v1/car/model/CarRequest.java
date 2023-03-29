package fghost.carview.v1.car.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @NotBlank(message = "{car.carModel.not.null}")
    @Size(max = 100, message = "{car.carModel.max.size}")
    private String carModel;

    @ApiModelProperty(value = "Auto Maker", required = true, dataType = "java.lang.String")
    @NotBlank(message = "{car.autoMaker.not.null}")
    @Size(max = 100, message = "{car.autoMaker.max.size}")
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
