package com.suraj.ecom.service.impl;


import com.suraj.ecom.entity.Type;
import com.suraj.ecom.mapper.TypeMapper;
import com.suraj.ecom.model.TypeResponse;
import com.suraj.ecom.repository.TypeRepository;
import com.suraj.ecom.service.TypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Log4j2
@Transactional(readOnly = true)
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;
    private final TypeMapper typeMapper;

    public TypeServiceImpl(TypeRepository typeRepository, TypeMapper typeMapper) {
        this.typeRepository = typeRepository;
        this.typeMapper = typeMapper;
    }

    @Override
    public List<TypeResponse> getAllTypes() {
        log.info("Fetching All Types");
        List<Type> typeList = typeRepository.findAll();
        return typeMapper.typesToTypeResponses(typeList);
    }
}