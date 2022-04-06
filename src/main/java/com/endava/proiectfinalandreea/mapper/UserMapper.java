package com.endava.proiectfinalandreea.mapper;


import com.endava.proiectfinalandreea.entity.UserEntity;
import com.endava.proiectfinalandreea.model.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {AuthorityMapper.class },componentModel = "spring")
public interface UserMapper {

    UserDto mapEntityToDto(UserEntity source);

    UserEntity mapDtoToEntity(UserDto source);

    List<UserDto> mapListEntityToListDto(List<UserEntity> source);

    UserEntity mapAdminDtoToEntityUser(UserAdminDtoCreateRequest source);

    UserEntity mapIndiovidualClientDtoToEntityUser(UserIndividualClientDtoCreateRequest source);

    UserEntity mapCompanyClientDtoToEntityUser(UserCompanyClientDtoCreateRequest source);

    UserEntity mapSupplierDtoToEntityUser(UserSupplierDtoCreateRequest source);
}
