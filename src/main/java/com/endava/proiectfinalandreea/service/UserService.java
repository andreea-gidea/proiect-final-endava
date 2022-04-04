package com.endava.proiectfinalandreea.service;


import com.endava.proiectfinalandreea.entity.UserEntity;
import com.endava.proiectfinalandreea.exeption.UserNotFoundException;
import com.endava.proiectfinalandreea.mapper.UserMapper;
import com.endava.proiectfinalandreea.model.UserDto;
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

    public List<UserDto> getUsers() {
        return userMapper.mapListEntityToListDto(userRepository.findAll());
    }

    public UserDto getUser(Integer userId) {
        return userMapper.mapEntityToDto(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("No such id for a user  "+userId)));
    }
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = userMapper.mapDtoToEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);

        return userMapper.mapEntityToDto(savedEntity);
    }
    @Transactional
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }



}
