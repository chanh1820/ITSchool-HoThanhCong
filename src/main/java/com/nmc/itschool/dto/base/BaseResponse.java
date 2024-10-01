package com.nmc.itschool.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nmc.itschool.constant.MessageEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private Integer status;
    private String messageCode;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(HttpStatus.OK.value(), null, null, null);
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(HttpStatus.OK.value(), null, null, data);
    }

    public static <T> BaseResponse<T> success(String messageCode, String message) {
        return new BaseResponse<>(HttpStatus.OK.value(), messageCode, message, null);
    }

    public static <T> BaseResponse<T> success(MessageEnum messageEnum) {
        return new BaseResponse<>(HttpStatus.OK.value(), messageEnum.getMessageCode(), messageEnum.getMessage(), null);
    }

    public static <T> BaseResponse<T> badRequest(T data) {
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), null, null, data);
    }

    public static <T> BaseResponse<T> badRequest(String messageCode, String message) {
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), messageCode, message, null);
    }

    public static <T> BaseResponse<T> badRequest(String messageCode, String message, T data) {
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), messageCode, message, data);
    }

    public static <T> BaseResponse<T> badRequest(String message, T data) {
        return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), null, message, data);
    }

    public static <T> BaseResponse<T> badRequest(MessageEnum messageEnum) {
        return new BaseResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                messageEnum.getMessageCode(), messageEnum.getMessage(), null);
    }

    public static <T> BaseResponse<T> fail(String message) {
        return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, message, null);
    }

    public static <T> BaseResponse<T> fail(MessageEnum messageEnum) {
        return new BaseResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                messageEnum.getMessageCode(), messageEnum.getMessage(), null);
    }

    public static <T> BaseResponse<T> unAuthorized(String message) {
        return new BaseResponse<>(HttpStatus.UNAUTHORIZED.value(), null, message, null);
    }

    public static <T> BaseResponse<T> unAuthorized(MessageEnum messageEnum) {
        return new BaseResponse<>(
                HttpStatus.UNAUTHORIZED.value(),
                messageEnum.getMessageCode(), messageEnum.getMessage(), null);
    }

    public static <T> BaseResponse<T> accessDenied(String message) {
        return new BaseResponse<>(HttpStatus.FORBIDDEN.value(), null, message, null);
    }

    public static <T> BaseResponse<T> accessDenied(MessageEnum messageEnum) {
        return new BaseResponse<>(
                HttpStatus.FORBIDDEN.value(),
                messageEnum.getMessageCode(), messageEnum.getMessage(), null);
    }

}
