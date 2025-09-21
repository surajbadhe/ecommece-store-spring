package com.suraj.ecom.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.ecom.model.AddItemRequest;
import com.suraj.ecom.model.BasketResponse;
import com.suraj.ecom.service.BasketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basket")
public class BasketController {

    private final BasketService basketService;

    /**
     * Gets the basket for the current user.
     * In a real app, userIdentifier would come from the authenticated security principal.
     */
    @GetMapping
    public ResponseEntity<BasketResponse> getUserBasket() {
        // TODO: Replace "anonymousUser" with the actual user identifier from Spring Security
        String userIdentifier = "anonymousUser"; 
        BasketResponse basket = basketService.getBasketByUserIdentifier(userIdentifier);
        return ResponseEntity.ok(basket);
    }

    /**
     * Adds an item to the current user's basket or updates its quantity.
     */
    @PostMapping("/items")
    public ResponseEntity<BasketResponse> addItemToBasket(@RequestBody AddItemRequest addItemRequest) {
        // TODO: Replace "anonymousUser" with the actual user identifier
        String userIdentifier = "anonymousUser";
        BasketResponse updatedBasket = basketService.addItemToBasket(
            userIdentifier,
            addItemRequest.getProductId(),
            addItemRequest.getQuantity()
        );
        return ResponseEntity.ok(updatedBasket);
    }

    /**
     * Removes an item from the current user's basket.
     */
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<BasketResponse> removeItemFromBasket(@PathVariable Long productId) {
        // TODO: Replace "anonymousUser" with the actual user identifier
        String userIdentifier = "anonymousUser";
        BasketResponse updatedBasket = basketService.removeItemFromBasket(userIdentifier, productId);
        return ResponseEntity.ok(updatedBasket);
    }
}