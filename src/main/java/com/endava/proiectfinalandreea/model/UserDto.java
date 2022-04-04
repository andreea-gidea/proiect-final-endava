package com.endava.proiectfinalandreea.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Builder
@Data
@AllArgsConstructor
public class UserDto {

    private int id;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private LocalDate dateCreatedAcc;

    @NotBlank
    private String addressOfDelivery;


}
