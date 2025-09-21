package com.suraj.ecom.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.suraj.ecom.entity.Order;
import com.suraj.ecom.model.OrderDto;
import com.suraj.ecom.model.OrderResponse;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "total", expression = "java(order.getSubTotal() + order.getDeliveryFee())")
    OrderResponse orderToOrderResponse(Order order);

    List<OrderResponse> ordersToOrderResponses(List<Order> orders);

    @Mapping(target = "id", ignore = true) // Ignore ID during creation
    @Mapping(target = "orderItems", ignore = true)
    @Mapping(target = "orderStatus", constant = "Pending")
    Order orderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", ignore = true)
    void updateOrderFromDto(OrderDto orderDto, @MappingTarget Order order);
}