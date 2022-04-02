package com.endava.proiectfinalandreea.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class Customer extends User{

    @Column(name = "delivery_address")
    private String deliveryAddress;

}
