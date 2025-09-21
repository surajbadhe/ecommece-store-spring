package com.suraj.ecom.service;

import com.suraj.ecom.model.OrderDto;
import com.suraj.ecom.model.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderService {

    OrderResponse getOrderById(Long orderId); 

    List<OrderResponse> getAllOrders();

    Page<OrderResponse> getAllOrders(Pageable pageable);

    Long createOrder(OrderDto order);

    void deleteOrder(Long orderId);
}