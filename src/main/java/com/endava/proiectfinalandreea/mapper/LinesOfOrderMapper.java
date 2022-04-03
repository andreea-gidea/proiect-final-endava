package com.endava.proiectfinalandreea.mapper;

import com.endava.proiectfinalandreea.entity.OrderEntity;
import com.endava.proiectfinalandreea.model.OrderDto;
import org.mapstruct.Mapper;


@Mapper(uses = {OrderMapper.class, ProductMapper.class}, componentModel = "spring")
public interface LinesOfOrderMapper {

    OrderDto mapEntityToDto(OrderEntity source);

    OrderEntity mapDtoToEntity(OrderDto source);

}
