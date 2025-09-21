package com.suraj.ecom.service;

import com.suraj.ecom.model.BasketResponse;

public interface BasketService {

    BasketResponse getBasketById(Long basketId);

    BasketResponse getBasketByUserIdentifier(String userIdentifier);

    void deleteBasketById(Long basketId);

    /**
     * Creates a new basket for a given user identifier.
     * @param userIdentifier the user's unique identifier (e.g., username or a temp ID).
     * @return the newly created basket.
     */
    BasketResponse createBasket(String userIdentifier); // Changed signature

	BasketResponse addItemToBasket(String userIdentifier, Long productId, int quantity);

	BasketResponse removeItemFromBasket(String userIdentifier, Long productId);
}