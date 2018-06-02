package com.rusoft.carsharing.exception;

import com.rusoft.carsharing.exception.errorcode.ErrorCode;
import lombok.Data;

@Data
public class CarsharingException extends RuntimeException {

    private ErrorCode errorCode;

    private CarsharingException(Throwable throwable, ErrorCode errorCode) {
        super(throwable);
        this.errorCode = errorCode;
    }

    public static CarsharingException wrap(Throwable tr) {
        return wrap(tr, null);
    }

    public static CarsharingException wrap(Throwable tr, ErrorCode errorCode) {
        if (tr instanceof CarsharingException) {
            CarsharingException serviceException = (CarsharingException) tr;
            if (errorCode != null && errorCode != serviceException.getErrorCode()
                    && !serviceException.getErrorCode()
                    .getHttpStatus()
                    .is4xxClientError()) {
                return new CarsharingException(tr, errorCode);
            }
            return serviceException;
        } else {
            return new CarsharingException(tr, errorCode);
        }
    }
}
