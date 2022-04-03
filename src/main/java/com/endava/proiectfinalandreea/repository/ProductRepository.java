package com.endava.proiectfinalandreea.repository;

import com.endava.proiectfinalandreea.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
