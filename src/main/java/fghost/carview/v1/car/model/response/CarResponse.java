package fghost.carview.v1.car.model.response;

import fghost.carview.v1.car.domain.CarTypeEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private String code;
    private String carModel;
    private String autoMaker;
    private String year;
    private String color;
    private CarTypeEnum type;
}
