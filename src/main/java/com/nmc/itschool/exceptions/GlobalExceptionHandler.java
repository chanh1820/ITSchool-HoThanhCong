package com.nmc.itschool.exceptions;

import com.demo.crudemployee.dto.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<BaseResponse<String>> handleAppException(HttpServletRequest request, AppException ex) {
        log.error("Request method: {}, url: {}, AppException: {}", request.getMethod(), request.getRequestURI(), ex);
        return ResponseEntity.badRequest().body(BaseResponse.badRequest(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<String>> handleGlobalException(HttpServletRequest request, Exception ex) {
        log.error("Request method: {}, url: {}, Exception: {}", request.getMethod(), request.getRequestURI(), ex);
        return ResponseEntity.of(Optional.of(BaseResponse.fail(ex.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse<String>> handleRuntimeException(HttpServletRequest request,
            RuntimeException ex) {
        log.error("Request method: {}, url: {}, RuntimeException: {}", request.getMethod(), request.getRequestURI(),
                ex);
        return ResponseEntity.of(Optional.of(BaseResponse.fail(ex.getMessage())));
    }

}
