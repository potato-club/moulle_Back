package com.gamza.moulle.error.exception;


import com.gamza.moulle.error.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
