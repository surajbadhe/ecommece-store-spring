package com.suraj.ecom.mapper;

import com.suraj.ecom.entity.Brand;
import com.suraj.ecom.model.BrandResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandResponse brandToBrandResponse(Brand brand);
    List<BrandResponse> brandsToBrandResponses(List<Brand> brands);
}
