package com.endava.proiectfinalandreea.model;

import lombok.Data;

import java.util.List;

@Data

public class OrderCreationRequest {

    private List<ProductInfo> linesOfOrder;


}
