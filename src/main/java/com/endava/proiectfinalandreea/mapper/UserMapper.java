package com.endava.proiectfinalandreea.mapper;


import com.endava.proiectfinalandreea.entity.UserEntity;
import com.endava.proiectfinalandreea.model.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapEntityToDto(UserEntity source);

    UserEntity mapDtoToEntity(UserDto source);

    List<UserDto> mapListEntityToListDto(List<UserEntity> source);
}
