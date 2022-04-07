package com.endava.proiectfinalandreea.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserDto {

    @NotBlank
    private String userName;

    @NotBlank
    @JsonIgnore
    private String password;

    @CreatedDate
    private LocalDate dateCreatedAcc;

    @NotBlank
    private String deliveryAddress;

    @NotBlank
    private String companyBillingDetails;

    @NotBlank
    private List<AuthorityDto> authorities;

//    private List<OrderDto> ordersOfClient;
//
//    private List<OrderDto> ordersOfSupplier;

}
