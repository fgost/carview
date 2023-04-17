package fghost.carview.v1.category.domain;

import fghost.carview.v1.type.domain.TypeEnum;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class Type {

    @NotBlank
    private TypeEnum typeName;
}
