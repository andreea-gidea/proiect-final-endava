package com.endava.proiectfinalandreea.model;


import com.endava.proiectfinalandreea.entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderDto {

    private Integer orderNumber;

    private LocalDate orderingDate;

    private OrderStatus orderStatus;

    private UserDto client;

    private SupplierDto supplier;

    private List<LinesOfOrderDto> orderLines;

    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

}