package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducteRepository extends JpaRepository<Producte, Integer> { // Interfaz que extiende de JpaRepository
}
