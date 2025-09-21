package com.suraj.ecom.mapper;

import com.suraj.ecom.entity.Type;
import com.suraj.ecom.model.TypeResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeMapper {
    TypeResponse typeToTypeResponse(Type type);
    List<TypeResponse> typesToTypeResponses(List<Type> types);
}
