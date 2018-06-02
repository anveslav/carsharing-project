package com.rusoft.carsharing.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus defaultHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    int getNumber();

    String getMessage();

    default HttpStatus getHttpStatus() {
        return defaultHttpStatus;
    }
}
