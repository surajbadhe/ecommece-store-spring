package com.suraj.ecom.model;


import java.time.LocalDateTime;

import com.suraj.ecom.entity.OrderStatus;
import com.suraj.ecom.entity.ShippingAddress;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private String basketId;
    private ShippingAddress shippingAddress;
    private Long subTotal;
    private Long deliveryFee;
    private Double total;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
}
