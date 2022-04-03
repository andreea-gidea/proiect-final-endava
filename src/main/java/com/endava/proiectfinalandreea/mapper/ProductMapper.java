package com.endava.proiectfinalandreea.mapper;

import com.endava.proiectfinalandreea.entity.ClientEntity;
import com.endava.proiectfinalandreea.entity.ProductEntity;
import com.endava.proiectfinalandreea.model.ClientDto;
import com.endava.proiectfinalandreea.model.ProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto mapEntityToDto(ProductEntity source);

    ProductEntity mapDtoToEntity(ProductDto source);
}
