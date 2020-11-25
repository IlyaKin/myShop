package com.geekbrains.exceptions;

import lombok.Getter;

@Getter
public class UnknownUserTypeException extends NotFoundException{
    private String message;

    public UnknownUserTypeException(String message){
        super(message);
         }
}
