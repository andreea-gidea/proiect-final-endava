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
import com.endava.proiectfinalandreea.model.ProductInfo;
import com.endava.proiectfinalandreea.repository.LinesOfOrderRepository;
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
    private final LinesOfOrderRepository linesOfOrderRepository;

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

    public OrderDto addToOrder(OrderCreationRequest orderAddMoreItemsRequest , Integer orderID) {
        if (getAllOrdersOfRequestingClient().stream().anyMatch(o -> orderID == o.getOrderNumber())) {

            OrderEntity order = orderRepository.findById(orderID).orElseThrow(() -> new OrderNotFoundException("No order found for ID  " + orderID));
            List<ProductInfo> linesOfOrder = orderAddMoreItemsRequest.getLinesOfOrder();
            List<LinesOfOrderEntity> productsAlreadyInOrder = order.getOrderLinesEntities();
            List<LinesOfOrderEntity> productToBeAdded = new ArrayList<>();
            for (ProductInfo p : linesOfOrder) {
                List<Object> toReturn = findIfProductsAreInOrder(productsAlreadyInOrder, p);

                Boolean isProductAlreadyInOrder = (Boolean) toReturn.get(0);
                Integer whereIsTheProduct = (Integer) toReturn.get(1);

                if (isProductAlreadyInOrder) {
                    productsAlreadyInOrder.get(whereIsTheProduct).setNumberOfProducts(productsAlreadyInOrder.get(whereIsTheProduct).getNumberOfProducts() + p.getNumberOfProducts());
                } else {
                    productToBeAdded.add(new LinesOfOrderEntity(order, productRepository.findById(p.getProductID()).orElseThrow(() -> new ProductNotFoundException("No such id for a product  " + p.getProductID())), p.getNumberOfProducts()));
                }
                order.getOrderLinesEntities().addAll(productToBeAdded);

            }
            OrderDto orderDto = orderMapper.mapEntityToDto(orderRepository.save(order));

            return orderDto;
        }else throw new OrderNotFoundException("No order to be modified");
    }

    @Transactional
    public void removeFromOrder(OrderCreationRequest orderAddMoreItemsRequest, Integer orderID) {
        if (getAllOrdersOfRequestingClient().stream().anyMatch(o -> orderID == o.getOrderNumber())) {

            OrderEntity order = orderRepository.findById(orderID).orElseThrow(() -> new OrderNotFoundException("No order found for ID  " + orderID));
            List<ProductInfo> productInfos = orderAddMoreItemsRequest.getLinesOfOrder();
            List<LinesOfOrderEntity> productsAlreadyInOrder = order.getOrderLinesEntities();
            for (ProductInfo p : productInfos) {
                List<Object> toReturn = findIfProductsAreInOrder(productsAlreadyInOrder, p);

                Boolean isProductAllreadyInOrder = (Boolean) toReturn.get(0);
                Integer whereIsTheProduct = (Integer) toReturn.get(1);

                if (isProductAllreadyInOrder) {
                    if (productsAlreadyInOrder.get(whereIsTheProduct).getNumberOfProducts() > p.getNumberOfProducts()) {
                        productsAlreadyInOrder.get(whereIsTheProduct).setNumberOfProducts(productsAlreadyInOrder.get(whereIsTheProduct).getNumberOfProducts() - p.getNumberOfProducts());
                    } else if (productsAlreadyInOrder.get(whereIsTheProduct).getNumberOfProducts().equals(p.getNumberOfProducts())) {
                        linesOfOrderRepository.deleteById(productsAlreadyInOrder.get(whereIsTheProduct).getId());
                        productsAlreadyInOrder.remove(whereIsTheProduct);
                    } else
                        throw new ProductNotFoundException("You are trying to remove more products than existing number " + p.getNumberOfProducts());
                } else {
                    throw new ProductNotFoundException("The product that you are trying to delete from the order, is not a part of the order: plant ID " + p.getProductID());
                }
                if (productsAlreadyInOrder.isEmpty()) {
                    orderRepository.deleteById(orderID);
                }
            }
        }else throw new OrderNotFoundException("No order to be modified");
    }

    private List<Object> findIfProductsAreInOrder(List<LinesOfOrderEntity> productsAlreadyInOrder, ProductInfo p) {
        boolean isProdAlreadyInOrder = false;
        int whereIsTheProd = 0;
        for (LinesOfOrderEntity o : productsAlreadyInOrder) {
            if (p.getProductID().equals(o.getProduct().getId())) {
                isProdAlreadyInOrder = true;
                whereIsTheProd = productsAlreadyInOrder.indexOf(o);
            }
        }
        List<Object> toReturn = new ArrayList<>();
        toReturn.add(isProdAlreadyInOrder);
        toReturn.add(whereIsTheProd);
        return toReturn;
    }

    @Transactional
    public void deleteOrder(Integer orderId) {
        if (getAllOrdersOfRequestingClient().stream().anyMatch(o -> orderId == o.getOrderNumber())) {
            orderRepository.deleteById(orderId);
        } else throw new OrderNotFoundException("No order you have access to");
    }

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