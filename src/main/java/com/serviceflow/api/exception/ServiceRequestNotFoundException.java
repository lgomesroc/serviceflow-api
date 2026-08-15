package com.serviceflow.api.exception;

public class ServiceRequestNotFoundException extends RuntimeException {

    public ServiceRequestNotFoundException(Long id) {
        super("Service request not found: " + id);
    }
}

