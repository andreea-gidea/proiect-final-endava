package com.endava.proiectfinalandreea.repository;

import com.endava.proiectfinalandreea.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
}
