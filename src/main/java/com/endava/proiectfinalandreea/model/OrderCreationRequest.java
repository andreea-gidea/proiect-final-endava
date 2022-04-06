package com.endava.proiectfinalandreea.model;

import lombok.Data;

import java.util.List;

@Data

public class OrderCreationRequest {

//    private Integer clientId;

    private List<ProductInfo> linesOfOrder;


}
