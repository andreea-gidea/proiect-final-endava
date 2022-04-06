package com.endava.proiectfinalandreea.mapper;

import com.endava.proiectfinalandreea.entity.LinesOfOrderEntity;
import com.endava.proiectfinalandreea.model.LinesOfOrderDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(uses = {OrderMapper.class, ProductMapper.class}, componentModel = "spring")
public interface LinesOfOrderMapper {

    LinesOfOrderDto mapEntityToDto(LinesOfOrderEntity source);

    LinesOfOrderEntity mapDtoToEntity(LinesOfOrderDto source);

    List<LinesOfOrderEntity> mapListDtoToListEntity(List<LinesOfOrderDto> source);

}
