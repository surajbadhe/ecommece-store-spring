package com.suraj.ecom.mapper;

import com.suraj.ecom.entity.Product;
import com.suraj.ecom.model.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "brand.name", target = "productBrand")
    @Mapping(source = "type.name", target = "productType")
    ProductResponse productToProductResponse(Product product);
}
