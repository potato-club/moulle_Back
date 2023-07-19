package com.gamza.moulle.error.exception;


import com.gamza.moulle.error.ErrorCode;

public class UnAuthorizedException extends BusinessException {

    public UnAuthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
