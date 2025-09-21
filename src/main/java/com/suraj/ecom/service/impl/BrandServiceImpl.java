package com.suraj.ecom.service.impl;

import com.suraj.ecom.entity.Brand;
import com.suraj.ecom.mapper.BrandMapper;
import com.suraj.ecom.model.BrandResponse;
import com.suraj.ecom.repository.BrandRepository;
import com.suraj.ecom.service.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public List<BrandResponse> getAllBrands() {
        log.info("Fetching All Brands");
        List<Brand> brandList = brandRepository.findAll();
        return brandMapper.brandsToBrandResponses(brandList);
    }
}