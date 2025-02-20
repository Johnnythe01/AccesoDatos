package com.example.repository;

import com.example.model.Producte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducteRepository extends JpaRepository<Producte, Integer> {
}
