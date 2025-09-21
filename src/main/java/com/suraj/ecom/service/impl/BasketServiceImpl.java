package com.suraj.ecom.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suraj.ecom.entity.Basket;
import com.suraj.ecom.entity.BasketItem;
import com.suraj.ecom.entity.Product;
import com.suraj.ecom.exception.BasketNotFoundException;
import com.suraj.ecom.exception.ProductNotFoundException;
import com.suraj.ecom.mapper.BasketMapper;
import com.suraj.ecom.model.BasketResponse;
import com.suraj.ecom.repository.BasketRepository;
import com.suraj.ecom.repository.ProductRepository;
import com.suraj.ecom.service.BasketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BasketServiceImpl implements BasketService {

	private final BasketRepository basketRepository;
	private final ProductRepository productRepository;
	private final BasketMapper basketMapper;
    
    @Override
    @Transactional(readOnly = true)
    public BasketResponse getBasketById(Long basketId) {
        log.info("Fetching Basket by Id: {}", basketId);
        return basketRepository.findById(basketId)
                .map(basketMapper::basketToBasketResponse)
                .orElseThrow(() -> new BasketNotFoundException("Basket with ID " + basketId + " not found"));
    }
    
    @Override
    @Transactional(readOnly = true)
    public BasketResponse getBasketByUserIdentifier(String userIdentifier) {
        log.info("Fetching Basket by User Identifier: {}", userIdentifier);
        return basketRepository.findByUserIdentifier(userIdentifier)
                .map(basketMapper::basketToBasketResponse)
                .orElseThrow(() -> new BasketNotFoundException("Basket for user " + userIdentifier + " not found"));
    }

    @Override
    public void deleteBasketById(Long basketId) {
        log.info("Deleting Basket by Id: {}", basketId);
        basketRepository.deleteById(basketId);
        log.info("Deleted Basket by Id: {}", basketId);
    }

    @Override
    public BasketResponse createBasket(String userIdentifier) { // Corrected signature
        log.info("Creating Basket for user: {}", userIdentifier);
        Basket newBasket = new Basket(userIdentifier);
        Basket savedBasket = basketRepository.save(newBasket);
        log.info("Basket created with Id: {}", savedBasket.getId());
        return basketMapper.basketToBasketResponse(savedBasket);
    }

    @Override
    public BasketResponse addItemToBasket(String userIdentifier, Long productId, int quantity) {
        log.info("Adding product {} with quantity {} for user {}", productId, quantity, userIdentifier);

        // 1. Get the product from the database
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

        // 2. Get or create the basket for the user
        Basket basket = basketRepository.findByUserIdentifier(userIdentifier)
                .orElseGet(() -> new Basket(userIdentifier));

        // 3. Check if the item is already in the basket
        Optional<BasketItem> existingItemOpt = basket.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            // If item exists, update its quantity
            BasketItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            // If item does not exist, create a new one and add it
            BasketItem newItem = new BasketItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            newItem.setBasket(basket); // Link the item to the basket
            basket.getItems().add(newItem);
        }

        // 4. Save the basket (and its items due to cascade) and return the DTO
        Basket savedBasket = basketRepository.save(basket);
        return basketMapper.basketToBasketResponse(savedBasket);
    }

    @Override
    public BasketResponse removeItemFromBasket(String userIdentifier, Long productId) {
        log.info("Removing product {} from basket for user {}", productId, userIdentifier);

        // 1. Get the user's basket
        Basket basket = basketRepository.findByUserIdentifier(userIdentifier)
                .orElseThrow(() -> new BasketNotFoundException("Basket for user " + userIdentifier + " not found"));

        // 2. Find and remove the item from the basket's item list
        // The `removeIf` operation is safe and efficient for this
        boolean removed = basket.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        if (!removed) {
            log.warn("Product {} was not found in the basket for user {}", productId, userIdentifier);
        }

        // 3. Save the basket and return the updated DTO
        // Because of `orphanRemoval=true` in the Basket entity, the removed BasketItem will be deleted from the database.
        Basket savedBasket = basketRepository.save(basket);
        return basketMapper.basketToBasketResponse(savedBasket);
    }
}