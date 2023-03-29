package fghost.carview.exception.domain;

import fghost.carview.exception.configuration.ConstantsExceptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SameIdentifierException extends BusinessException{
    private final String code;

    private final String message;

    public SameIdentifierException() {
        this.code =  ConstantsExceptions.EXCEPTION_CODE_BUSINESS;
        this.message = "Denied by business rule";
    }

    public SameIdentifierException(String message) {
        this.code = ConstantsExceptions.EXCEPTION_CODE_BUSINESS;
        this.message = message;
    }
}
