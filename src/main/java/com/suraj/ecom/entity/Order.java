package com.suraj.ecom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "basket_id")
    private String basketId;

    @Embedded
    private ShippingAddress shippingAddress;

    @Column(name = "order_date")
    private LocalDateTime orderDate = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "delivery_fee")
    private Long deliveryFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus = OrderStatus.Pending;
}