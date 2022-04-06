package com.endava.proiectfinalandreea.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
public class UserAdminDtoCreateRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    private List<AuthorityDto> authorities;

}
