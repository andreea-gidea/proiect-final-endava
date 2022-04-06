package com.endava.proiectfinalandreea.service;


import com.endava.proiectfinalandreea.entity.LinesOfOrderEntity;
import com.endava.proiectfinalandreea.entity.OrderEntity;
import com.endava.proiectfinalandreea.entity.OrderStatus;
import com.endava.proiectfinalandreea.exeption.OrderNotFoundException;
import com.endava.proiectfinalandreea.exeption.ProductNotFoundException;
import com.endava.proiectfinalandreea.exeption.UserNotFoundException;
import com.endava.proiectfinalandreea.mapper.OrderMapper;
import com.endava.proiectfinalandreea.model.OrderCreationRequest;
import com.endava.proiectfinalandreea.model.OrderDto;
import com.endava.proiectfinalandreea.repository.LinesOfOrderRepository;
import com.endava.proiectfinalandreea.repository.OrderRepository;
import com.endava.proiectfinalandreea.repository.ProductRepository;
import com.endava.proiectfinalandreea.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final LinesOfOrderRepository linesOfOrderRepository;

    public List<OrderDto> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDto> ordersDTO = orderMapper.mapListEntityToListDto(orders);

        return ordersDTO;
    }

    public OrderDto getOrder(Integer orderId) {

        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("No order for given Id  " + orderId));

        OrderDto orderDto = orderMapper.mapEntityToDto(order);

        return orderDto;
    }

    public OrderDto createOrder(OrderCreationRequest orderCreationRequest) {

        List<LinesOfOrderEntity> products = new ArrayList<>();
        OrderEntity order = new OrderEntity();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        order.setClient(userRepository.findUserByUserName(authentication.getName()).orElseThrow(() -> new UserNotFoundException("No such id for a client  ")));

        orderCreationRequest.getLinesOfOrder().forEach(lineOfOrder -> products.add(new LinesOfOrderEntity(order, productRepository.findById(lineOfOrder.getProductID()).orElseThrow(() -> new ProductNotFoundException("No such id for a product  " + lineOfOrder.getProductID())), lineOfOrder.getNumberOfProducts())));
        order.getOrderLinesEntities().addAll(products);
        order.setOrderStatus(OrderStatus.NEW);
        OrderDto orderDto = orderMapper.mapEntityToDto(orderRepository.save(order));

        return orderDto;
    }

}