package com.suraj.ecom.service;

import com.suraj.ecom.model.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse getProductById(Long productId); // Changed to Long

    /**
     * Gets a paginated and filtered list of products.
     * The implementation of this method will use the JpaSpecificationExecutor.
     * @param pageable the pagination information.
     * @param brandId the brand ID to filter by (optional).
     * @param typeId the type ID to filter by (optional).
     * @param keyword the search keyword for the product name (optional).
     * @return a page of ProductResponse DTOs.
     */
    Page<ProductResponse> getProducts(Pageable pageable, Long brandId, Long typeId, String keyword); // Changed to Long
}