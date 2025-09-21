package com.suraj.ecom.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.ecom.model.BrandResponse;
import com.suraj.ecom.model.ProductResponse;
import com.suraj.ecom.model.TypeResponse;
import com.suraj.ecom.service.BrandService;
import com.suraj.ecom.service.ProductService;
import com.suraj.ecom.service.TypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final BrandService brandService;
    private final TypeService typeService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId) {
        ProductResponse productResponse = productService.getProductById(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long brandId,
            @RequestParam(required = false) Long typeId,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<ProductResponse> productResponses = productService.getProducts(pageable, brandId, typeId, keyword);
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<BrandResponse> brandResponses = brandService.getAllBrands();
        return new ResponseEntity<>(brandResponses, HttpStatus.OK);
    }

    @GetMapping("/types")
    public ResponseEntity<List<TypeResponse>> getTypes() {
        List<TypeResponse> typeResponses = typeService.getAllTypes();
        return new ResponseEntity<>(typeResponses, HttpStatus.OK);
    }
}