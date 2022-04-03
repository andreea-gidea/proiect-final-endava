package com.endava.proiectfinalandreea.mapper;


import com.endava.proiectfinalandreea.entity.ClientEntity;
import com.endava.proiectfinalandreea.model.ClientDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto mapEntityToDto(ClientEntity source);

    ClientEntity mapDtoToEntity(ClientDto source);
}
