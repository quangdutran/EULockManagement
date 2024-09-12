package com.dutq.lock.management.lock_management.exceptions;

public class EUAPIRestException extends RuntimeException {

    private String message;

    public EUAPIRestException(String message) {
        super(message);
    }



}
