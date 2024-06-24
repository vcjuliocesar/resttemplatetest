package com.vcjuliocesar.resttemplate.resttemplatetest.domain.exceptions;

public class CustomException extends RuntimeException {
    private final int statusCode;

    public CustomException(String message,int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return this.statusCode;
    }
}
