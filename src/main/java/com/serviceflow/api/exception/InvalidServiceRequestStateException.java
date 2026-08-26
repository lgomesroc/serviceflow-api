package com.serviceflow.api.exception;

public class InvalidServiceRequestStateException extends RuntimeException {

    public InvalidServiceRequestStateException(String message) {
        super(message);
    }
}