package com.endava.proiectfinalandreea.mapper;

import com.endava.proiectfinalandreea.entity.Authority;

import com.endava.proiectfinalandreea.model.AuthorityDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(uses = {UserMapper.class}, componentModel = "spring")
public interface AuthorityMapper {

    AuthorityDto mapEntityToDto(Authority source);

    Authority mapDtoToEntity(AuthorityDto source);

    List<Authority> mapListDtoToListEntity(List<AuthorityDto> source);

}
