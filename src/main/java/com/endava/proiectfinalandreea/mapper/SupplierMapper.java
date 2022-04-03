package com.endava.proiectfinalandreea.mapper;

import com.endava.proiectfinalandreea.entity.ClientEntity;
import com.endava.proiectfinalandreea.entity.SupplierEntity;
import com.endava.proiectfinalandreea.model.ClientDto;
import com.endava.proiectfinalandreea.model.SupplierDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierDto mapEntityToDto(SupplierEntity source);

    SupplierEntity mapDtoToEntity(SupplierDto source);

    List<SupplierDto> mapListEntityToListDto(List<SupplierEntity> source);


}
