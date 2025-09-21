package com.suraj.ecom.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suraj.ecom.entity.Order;
import com.suraj.ecom.entity.OrderItem;
import com.suraj.ecom.entity.ProductItemOrdered;
import com.suraj.ecom.mapper.OrderMapper;
import com.suraj.ecom.model.BasketItemResponse;
import com.suraj.ecom.model.BasketResponse;
import com.suraj.ecom.model.OrderDto;
import com.suraj.ecom.model.OrderResponse;
import com.suraj.ecom.repository.OrderRepository;
import com.suraj.ecom.service.BasketService;
import com.suraj.ecom.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse getOrderById(Long orderId) { 
        return orderRepository.findById(orderId)
                .map(orderMapper::orderToOrderResponse)
                .orElse(null);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.ordersToOrderResponses(orders);
    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .map(orderMapper::orderToOrderResponse);
    }

    @Override
    public void deleteOrder(Long orderId) { 
        orderRepository.deleteById(orderId);
    }

    @Override
    public Long createOrder(OrderDto orderDto) {
        BasketResponse basketResponse = basketService.getBasketById(orderDto.getBasketId());
        if (basketResponse == null || basketResponse.getItems().isEmpty()) {
            log.error("Basket with ID {} is empty or not found", orderDto.getBasketId());
            return null;
        }

        Order order = orderMapper.orderDtoToOrder(orderDto);

        List<OrderItem> orderItems = basketResponse.getItems().stream()
                .map(basketItem -> {
                    OrderItem orderItem = mapBasketItemToOrderItem(basketItem);
                    orderItem.setOrder(order); 
                    return orderItem;
                })
                .collect(Collectors.toList());

        double subTotal = basketResponse.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        order.setOrderItems(orderItems);
        order.setSubTotal(subTotal);

        Order savedOrder = orderRepository.save(order);
        basketService.deleteBasketById(orderDto.getBasketId());

        return savedOrder.getId();
    }

    private OrderItem mapBasketItemToOrderItem(BasketItemResponse basketItem) {
        OrderItem orderItem = new OrderItem();

        ProductItemOrdered itemOrdered = new ProductItemOrdered();
        itemOrdered.setProductId(basketItem.getId());
        itemOrdered.setName(basketItem.getName());
        itemOrdered.setPictureUrl(basketItem.getPictureUrl());

        orderItem.setItemOrdered(itemOrdered);
        orderItem.setPrice(basketItem.getPrice());
        orderItem.setQuantity(basketItem.getQuantity());

        return orderItem;
    }
}
