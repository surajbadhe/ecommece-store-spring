package com.suraj.ecom.service.impl;
import com.suraj.ecom.entity.Product;
import com.suraj.ecom.exception.ProductNotFoundException;
import com.suraj.ecom.mapper.ProductMapper;
import com.suraj.ecom.model.ProductResponse;
import com.suraj.ecom.repository.ProductRepository;
import com.suraj.ecom.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Fetching Product by Id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));
        log.info("Fetched Product by Product Id: {}", productId);
        return productMapper.productToProductResponse(product);
    }

    @Override
    public Page<ProductResponse> getProducts(Pageable pageable, Long brandId, Long typeId, String keyword) {
        log.info("Fetching products with filters - Brand ID: {}, Type ID: {}, Keyword: {}", brandId, typeId, keyword);
        Specification<Product> spec = Specification.where(null);

        if (brandId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("brand").get("id"), brandId));
        }
        if (typeId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("type").get("id"), typeId));
        }
        if (keyword != null && !keyword.trim().isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"));
        }

        return productRepository.findAll(spec, pageable).map(productMapper::productToProductResponse);
    }
}