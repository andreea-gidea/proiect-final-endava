package com.endava.proiectfinalandreea.service;


import com.endava.proiectfinalandreea.entity.Authority;
import com.endava.proiectfinalandreea.entity.UserEntity;
import com.endava.proiectfinalandreea.exeption.UserNotFoundException;
import com.endava.proiectfinalandreea.mapper.UserMapper;
import com.endava.proiectfinalandreea.model.*;
import com.endava.proiectfinalandreea.repository.AuthorityRepository;
import com.endava.proiectfinalandreea.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityRepository authorityRepository;

    public List<UserDto> getUsers() {
        return userMapper.mapListEntityToListDto(userRepository.findAll());
    }

    public UserDto getUser(Integer userId) {
        return userMapper.mapEntityToDto(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No such id for a user  " + userId)));
    }

    public UserDto createAdminUser(UserAdminDtoCreateRequest user) {

        UserEntity userEntity = userMapper.mapAdminDtoToEntityUser(user);
        UserEntity savedEntity = saveUserAndRole(userEntity, "ROLE_ADMIN");


        return userMapper.mapEntityToDto(savedEntity);
    }

    public UserDto createIndividualClientUser(UserIndividualClientDtoCreateRequest user) {

        UserEntity userEntity = userMapper.mapIndiovidualClientDtoToEntityUser(user);
        UserEntity savedEntity = saveUserAndRole(userEntity, "ROLE_INDIVIDUAL_CLIENT");

        return userMapper.mapEntityToDto(savedEntity);
    }


    public UserDto createCompanyClientUser(UserCompanyClientDtoCreateRequest user) {

        UserEntity userEntity = userMapper.mapCompanyClientDtoToEntityUser(user);
        UserEntity savedEntity = saveUserAndRole(userEntity, "ROLE_COMPANY_CLIENT");

        return userMapper.mapEntityToDto(savedEntity);
    }

    public UserDto createSupplierClientUser(UserSupplierDtoCreateRequest user) {

        UserEntity userEntity = userMapper.mapSupplierDtoToEntityUser(user);
        UserEntity savedEntity = saveUserAndRole(userEntity, "ROLE_SUPPLIER");

        return userMapper.mapEntityToDto(savedEntity);
    }

    private UserEntity saveUserAndRole(UserEntity userEntity, String role) {
        UserEntity savedEntity = userRepository.save(userEntity);
        Authority authority = new Authority();
        authority.setClient(userRepository.findById(savedEntity.getId()).orElseThrow(() -> new UserNotFoundException("User not found for given Id")));
        authority.setName(role);
        authorityRepository.save(authority);
        return savedEntity;
    }

    @Transactional
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }


}
