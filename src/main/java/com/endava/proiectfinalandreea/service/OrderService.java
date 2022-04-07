package com.endava.proiectfinalandreea.service;


import com.endava.proiectfinalandreea.entity.LinesOfOrderEntity;
import com.endava.proiectfinalandreea.entity.OrderEntity;
import com.endava.proiectfinalandreea.entity.OrderStatus;
import com.endava.proiectfinalandreea.entity.UserEntity;
import com.endava.proiectfinalandreea.exeption.OrderNotFoundException;
import com.endava.proiectfinalandreea.exeption.ProductNotFoundException;
import com.endava.proiectfinalandreea.exeption.UserNotFoundException;
import com.endava.proiectfinalandreea.mapper.OrderMapper;
import com.endava.proiectfinalandreea.model.OrderCreationRequest;
import com.endava.proiectfinalandreea.model.OrderDto;
import com.endava.proiectfinalandreea.repository.OrderRepository;
import com.endava.proiectfinalandreea.repository.ProductRepository;
import com.endava.proiectfinalandreea.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<OrderDto> getAllOrders() {

        List<OrderEntity> orders = orderRepository.findAll();

        return orderMapper.mapListEntityToListDto(orders);
    }

    public List<OrderDto> getAllOrdersOfSupplier() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity supplier = userRepository.findUserByUserName(authentication.getName()).orElseThrow(() -> new UserNotFoundException("No such name for a client  "));
        List<OrderEntity> orders = orderRepository.findAll().stream()
                .filter(o -> o.getSupplier() == supplier).collect(Collectors.toList());

        return orderMapper.mapListEntityToListDto(orders);
    }

    public List<OrderDto> getAllOrdersWithStatusNew() {
        List<OrderEntity> ordersWithStatusNew = getOrderEntitiesWithStatus(OrderStatus.NEW);

        return orderMapper.mapListEntityToListDto(ordersWithStatusNew);
    }

    private List<OrderEntity> getOrderEntitiesWithStatus(OrderStatus orderStatus) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderEntity> ordersWithStatusNew = orders.stream()
                .filter(o -> (orderStatus).equals(o.getOrderStatus()))
                .collect(Collectors.toList());
        return ordersWithStatusNew;
    }

    public List<OrderDto> getAllOrdersOfRequestingClient() {
        List<OrderEntity> orders = orderRepository.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity client = userRepository.findUserByUserName(authentication.getName()).orElseThrow(() -> new UserNotFoundException("No such name for a client  "));
        List<OrderEntity> ordersWithStatusNew = orders.stream()
                .filter(o -> o.getClient() == client)
                .collect(Collectors.toList());
        List<OrderDto> ordersDTO = orderMapper.mapListEntityToListDto(ordersWithStatusNew);

        return ordersDTO;
    }

    @Transactional
    public OrderDto changeOrderStatusToAnalysis(Integer orderId) {

        List<OrderEntity> orders = getOrderEntitiesWithStatus(OrderStatus.NEW);
        OrderEntity order = orders.stream().filter(o -> orderId == o.getOrderNumber())
                .findAny()
                .orElseThrow(() -> new OrderNotFoundException("No order with status NEW for given Id  " + orderId));
        order.setOrderStatus(OrderStatus.ANALYSIS);
        OrderEntity savedEntity = orderRepository.save(order);

        return orderMapper.mapEntityToDto(savedEntity);
    }

    @Transactional
    public OrderDto changeOrderStatusToProgress(Integer orderId) {

        List<OrderEntity> orders = getOrderEntitiesWithStatus(OrderStatus.ANALYSIS);
        OrderEntity order = orders.stream().filter(o -> orderId == o.getOrderNumber())
                .findAny()
                .orElseThrow(() -> new OrderNotFoundException("No order with status NEW for given Id  " + orderId));
        order.setOrderStatus(OrderStatus.IN_PROGRESS);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        order.setSupplier(userRepository.findUserByUserName(authentication.getName()).orElseThrow(() -> new UserNotFoundException("No such id for a client  ")));
        OrderEntity savedEntity = orderRepository.save(order);

        return orderMapper.mapEntityToDto(savedEntity);
    }

    public OrderDto createOrder(OrderCreationRequest orderCreationRequest) {

        List<LinesOfOrderEntity> products = new ArrayList<>();
        OrderEntity order = new OrderEntity();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        order.setClient(userRepository.findUserByUserName(authentication.getName()).orElseThrow(() -> new UserNotFoundException("No such id for a client  ")));

        orderCreationRequest.getLinesOfOrder().forEach(lineOfOrder -> products.add(new LinesOfOrderEntity(order, productRepository.findById(lineOfOrder.getProductID()).orElseThrow(() -> new ProductNotFoundException("No such id for a product  " + lineOfOrder.getProductID())), lineOfOrder.getNumberOfProducts())));
        order.getOrderLinesEntities().addAll(products);
        order.setOrderStatus(OrderStatus.NEW);

        return orderMapper.mapEntityToDto(orderRepository.save(order));
    }

}