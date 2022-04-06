package com.endava.proiectfinalandreea.controller;

import com.endava.proiectfinalandreea.model.*;
import com.endava.proiectfinalandreea.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    public OrderDto createOrder(@RequestBody OrderCreationRequest orderCreationRequest) {
        return orderService.createOrder(orderCreationRequest);
    }


}