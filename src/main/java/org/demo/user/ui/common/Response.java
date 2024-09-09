package org.demo.user.ui.common;

import org.demo.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String msg, T value) {

    public static <T> Response<T> ok(T value){
        return new Response<>(0, "ok", value);
    }

    public static <T> Response<T> error(ErrorCode error){
        return new Response<>(error.getCode(), error.getMsg(), null);
    }
}
