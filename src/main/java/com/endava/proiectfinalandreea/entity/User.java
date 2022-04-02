package com.endava.proiectfinalandreea.entity;

import lombok.Data;

import java.time.LocalDate;
import javax.persistence.*;

@MappedSuperclass
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "date_account")
    private LocalDate dateCreatedAcc=LocalDate.now();

}