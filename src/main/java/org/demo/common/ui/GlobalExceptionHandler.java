package org.demo.common.ui;

import lombok.extern.slf4j.Slf4j;
import org.demo.common.domain.exception.ErrorCode;
import org.demo.user.ui.common.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // IllegalArgumentException 발생 시 common의 ErrorCode 객체 반환
    @ExceptionHandler(IllegalArgumentException.class)
    public Response<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        return Response.error(ErrorCode.INVALID_INPUT_VALUE);
    }

    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception e) {
        log.error(e.getMessage());
        return Response.error(ErrorCode.INVALID_INPUT_VALUE);
    }
}
