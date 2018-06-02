package com.rusoft.carsharing.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum CarServiceErrorCode implements ErrorCode {

    GETTING_FREE_CAR_BY_MODEL_AND_YEAR_EXCEPTION(1, "Car isn't free"),
    GETTING_CAR_BY_MODEL_AND_YEAR_EXCEPTION(2, "Car isn't free"),
    GETTING_CAR_BY_MODEL_EXCEPTION(3, "Car isn't found by model"),
    SAVING_CAR_EXCEPTION(4, "Car couldn't be saved"),
    MAKING_CAR_FREE_EXCEPTION(5, "Car couldn't be free");

    private final int number;
    private final String message;
    private final HttpStatus httpStatus;

    CarServiceErrorCode(int number, String message) {
        this(number, message, defaultHttpStatus);
    }

    CarServiceErrorCode(int number, String message, HttpStatus httpStatus) {
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
