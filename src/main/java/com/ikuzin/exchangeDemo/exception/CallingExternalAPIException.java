package com.ikuzin.exchangeDemo.exception;

public class CallingExternalAPIException extends RuntimeException{
    public CallingExternalAPIException(String message){
        super(message);
    }
}
