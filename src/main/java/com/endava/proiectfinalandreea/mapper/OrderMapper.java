package com.endava.proiectfinalandreea.mapper;


import org.mapstruct.Mapper;


@Mapper(uses = {LinesOfOrderMapper.class, UserMapper.class}, componentModel = "spring")
public interface OrderMapper {

//    OrderDto mapEntityToDto(OrderEntity source);
//
//    OrderEntity mapDtoToEntity(OrderDto source);


}
