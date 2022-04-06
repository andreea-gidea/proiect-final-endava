package com.endava.proiectfinalandreea.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCompanyClientDtoCreateRequest {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String deliveryAddress;

    @NotBlank
    private String companyBillingDetails;


    private List<AuthorityDto> authorities;

}
