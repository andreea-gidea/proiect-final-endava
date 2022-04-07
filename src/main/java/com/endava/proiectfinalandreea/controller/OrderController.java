package com.endava.proiectfinalandreea.controller;

import com.endava.proiectfinalandreea.model.*;
import com.endava.proiectfinalandreea.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get-all-orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/get-all-orders-assigned-to-supplier")
    public List<OrderDto> getOrdersOfCertainSupplier() {
        return orderService.getAllOrdersOfSupplier();
    }

    @PostMapping("/create-order")
    public OrderDto createOrder(@RequestBody OrderCreationRequest orderCreationRequest) {
        return orderService.createOrder(orderCreationRequest);
    }

    @GetMapping("/get-new-orders")
    public List<OrderDto> getNewOrders() {
        return orderService.getAllOrdersWithStatusNew();
    }

    @GetMapping("/get-my-orders")
    public List<OrderDto> getOrdersOfRequestingClient() {
        return orderService.getAllOrdersOfRequestingClient();
    }

    @PutMapping("/change-status/to-analysis/{orderID}")
    public OrderDto changeOrderStatus(@PathVariable(name = "orderID") Integer orderID) {
        return orderService.changeOrderStatusToAnalysis(orderID);
    }

    @PutMapping("/change-status/to-progress/{orderID}")
    public OrderDto changeOrderStatusToInProgress(@PathVariable(name = "orderID") Integer orderID) {
        return orderService.changeOrderStatusToProgress(orderID);
    }
}