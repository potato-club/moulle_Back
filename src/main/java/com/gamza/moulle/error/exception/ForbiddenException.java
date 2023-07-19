package com.gamza.moulle.error.exception;


import com.gamza.moulle.error.ErrorCode;

public class ForbiddenException extends BusinessException {

    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
