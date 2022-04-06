package com.endava.proiectfinalandreea.mapper;


import com.endava.proiectfinalandreea.entity.OrderEntity;
import com.endava.proiectfinalandreea.model.OrderDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(uses = {LinesOfOrderMapper.class, UserMapper.class}, componentModel = "spring")
public interface OrderMapper {

    OrderDto mapEntityToDto(OrderEntity source);

    OrderEntity mapDtoToEntity(OrderDto source);

    List<OrderDto> mapListEntityToListDto(List<OrderEntity> source);

    List<OrderEntity> mapListDtoToListEntity(List<OrderDto> source);



}
