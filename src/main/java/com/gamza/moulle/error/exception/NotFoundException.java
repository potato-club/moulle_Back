package com.gamza.moulle.error.exception;


import com.gamza.moulle.error.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
