package com.endava.proiectfinalandreea.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
public class UserSupplierDtoCreateRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private List<AuthorityDto> authorities;

}
