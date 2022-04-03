package com.endava.proiectfinalandreea.mapper;


import com.endava.proiectfinalandreea.entity.OrderEntity;
import com.endava.proiectfinalandreea.model.OrderDto;
import org.mapstruct.Mapper;


@Mapper(uses = {LinesOfOrderMapper.class, ClientMapper.class, SupplierMapper.class}, componentModel = "spring")
public interface OrderMapper {

//    OrderDto mapEntityToDto(OrderEntity source);
//
//    OrderEntity mapDtoToEntity(OrderDto source);


}
