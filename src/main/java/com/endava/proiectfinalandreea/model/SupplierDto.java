package com.endava.proiectfinalandreea.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class SupplierDto {

    private int id;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private LocalDate dateCreatedAcc;

    private String companyDescription;

    private List<OrderDto> orders;

}
