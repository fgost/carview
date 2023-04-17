package fghost.carview.v1.category.domain;

import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.exception.util.MessageResource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CategoryEnum {

    PREVENTIVE((short) 0),
    CORRECTIVE((short) 1),
    PREDICTIVE((short) 2),
    SCHEDULED((short) 3),
    EMERGENCY((short) 4);

    private final short value;

    public static CategoryEnum fromValue(Short categoryCode) {
        var message = MessageResource.getInstance().getMessage("category.invalid");
        return Arrays.stream(CategoryEnum.values())
                .filter(it -> Short.valueOf(it.getValue()).equals(categoryCode))
                .findFirst()
                .orElseThrow(() -> ExceptionUtils.buildBadRequestException(message));
    }
}

