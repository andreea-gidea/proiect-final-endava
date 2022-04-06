package com.endava.proiectfinalandreea.model;

import com.endava.proiectfinalandreea.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@AllArgsConstructor
public class AuthorityDto {

    private Integer id;

    private String name;

}
