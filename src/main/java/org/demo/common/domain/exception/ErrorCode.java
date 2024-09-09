package org.demo.common.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_INPUT_VALUE(400, "invalid input value"),
    NOT_FOUND(404, "not found"),
    INTERNAL_SERVER_ERROR(500, "internal server error");

    private final Integer code;
    private final String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
