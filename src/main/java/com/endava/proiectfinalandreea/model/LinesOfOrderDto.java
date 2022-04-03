package com.endava.proiectfinalandreea.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Builder
@Data
@AllArgsConstructor
public class LinesOfOrderDto {

    private Integer id;

    private OrderDto order;

    private ProductDto product;

    @Min(1)
    private Integer numberOfProducts;


}
