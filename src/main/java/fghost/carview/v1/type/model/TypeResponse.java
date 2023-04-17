package fghost.carview.v1.type.model;

import fghost.carview.v1.type.domain.TypeEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse {
    private String code;
    private TypeEnum typeName;
}
