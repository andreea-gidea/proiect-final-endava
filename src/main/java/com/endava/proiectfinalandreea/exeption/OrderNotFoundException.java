package com.endava.proiectfinalandreea.exeption;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String msg){
        super(msg);
    }
}
