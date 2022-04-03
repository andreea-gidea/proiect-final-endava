package com.endava.proiectfinalandreea.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ProductDto {

    private Integer id;

    private String productName;

    private String productDescription;

    private Integer price;
}
