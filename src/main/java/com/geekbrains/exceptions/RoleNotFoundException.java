package com.geekbrains.exceptions;

import org.springframework.web.client.HttpClientErrorException;

public class RoleNotFoundException  extends NotFoundException {
    public RoleNotFoundException(String message){
        super(message);
    }
}
