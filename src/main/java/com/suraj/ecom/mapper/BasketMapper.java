package com.suraj.ecom.mapper;

import com.suraj.ecom.entity.Basket;
import com.suraj.ecom.entity.BasketItem;
import com.suraj.ecom.model.BasketItemResponse;
import com.suraj.ecom.model.BasketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring") 
public interface BasketMapper {
    BasketResponse basketToBasketResponse(Basket basket);

    @Mapping(source = "product.id", target = "id")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.description", target = "description")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "product.pictureUrl", target = "pictureUrl")
    @Mapping(source = "product.brand.name", target = "productBrand")
    @Mapping(source = "product.type.name", target = "productType")
    BasketItemResponse basketItemToBasketItemResponse(BasketItem basketItem);
}
