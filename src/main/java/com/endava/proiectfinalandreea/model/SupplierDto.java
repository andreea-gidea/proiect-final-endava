package com.endava.proiectfinalandreea.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SupplierDto {

    private int id;

    @NotBlank
    private String userName;

    @NotBlank
    @JsonIgnore
    private String password;

    private LocalDate dateCreatedAcc;

    private String companyDescription;

    private List<OrderDto> orders;

}
