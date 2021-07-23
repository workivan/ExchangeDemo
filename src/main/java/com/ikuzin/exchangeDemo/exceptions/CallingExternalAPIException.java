package com.ikuzin.exchangeDemo.exceptions;

public class CallingExternalAPIException extends RuntimeException{
    public CallingExternalAPIException(String message){
        super(message);
    }
}
