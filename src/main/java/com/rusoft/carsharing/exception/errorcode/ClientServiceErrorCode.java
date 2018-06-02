package com.rusoft.carsharing.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum ClientServiceErrorCode implements ErrorCode {

    ADDING_CLIENT_EXCEPTION(1, "Client isn't added"),
    GETTING_CLIENT_BY_NAME_AND_BIRTH_YEAR_EXCEPTION(2, "Client isn't found by name and year of birth"),
    GETTING_CLIENT_BY_NAME_EXCEPTION(3, "Client isn't found by name"),
    DELETING_CLIENT_EXCEPTION(4,"Client couldn't be deleted");

    private final int number;
    private final String message;
    private final HttpStatus httpStatus;

    ClientServiceErrorCode(int number, String message) {
        this(number, message, defaultHttpStatus);
    }

    ClientServiceErrorCode (int number, String message, HttpStatus httpStatus) {
        this.number = number;
        this.message = message;
        this.httpStatus = httpStatus;
    }
    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
