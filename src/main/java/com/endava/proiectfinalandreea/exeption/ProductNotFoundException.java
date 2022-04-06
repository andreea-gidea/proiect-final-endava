package com.endava.proiectfinalandreea.exeption;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String msg){
        super(msg);
    }
}
