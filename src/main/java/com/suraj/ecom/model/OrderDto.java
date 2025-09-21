package com.suraj.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.suraj.ecom.entity.ShippingAddress;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long basketId;
    private ShippingAddress shippingAddress;
    private Long subTotal;
    private Long deliveryFee;
    private LocalDateTime orderDate;
}
