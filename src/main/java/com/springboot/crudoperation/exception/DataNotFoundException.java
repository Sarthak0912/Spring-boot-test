package com.springboot.crudoperation.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message){
        super(message);
    }
}
