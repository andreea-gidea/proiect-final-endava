package com.endava.proiectfinalandreea.repository;

import com.endava.proiectfinalandreea.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserByUserName(String username);
}
