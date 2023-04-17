package fghost.carview.v1.type.domain;

import fghost.carview.exception.util.ExceptionUtils;
import fghost.carview.exception.util.MessageResource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TypeEnum {

    MECHANICS((short) 0),
    ELECTRIC((short) 1),
    AIRCONDITIONING((short) 2),
    OTHERSPECIALTIES((short) 3);

    private final short value;

    public static TypeEnum fromValue(Short typeCode) {
        var message = MessageResource.getInstance().getMessage("type.invalid");
        return Arrays.stream(TypeEnum.values())
                .filter(it -> Short.valueOf(it.getValue()).equals(typeCode))
                .findFirst()
                .orElseThrow(() -> ExceptionUtils.buildBadRequestException(message));
    }
}
