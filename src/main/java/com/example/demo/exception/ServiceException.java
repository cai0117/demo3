package com.example.demo.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{
    public ServiceException(String message){
        super(message);
    }
}
