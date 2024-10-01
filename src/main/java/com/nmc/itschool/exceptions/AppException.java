package com.nmc.itschool.exceptions;

import com.nmc.itschool.constant.MessageEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends RuntimeException {

    private final String errorCode;

    public AppException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AppException(MessageEnum messageEnum) {
        super(messageEnum.getMessage());
        this.errorCode = messageEnum.getMessageCode();
    }

    public String getCode() {
        return this.errorCode;
    }

}
