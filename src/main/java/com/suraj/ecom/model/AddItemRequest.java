package com.suraj.ecom.model;

import lombok.Data;

@Data
public class AddItemRequest {
    private Long productId;
    private int quantity;
}
