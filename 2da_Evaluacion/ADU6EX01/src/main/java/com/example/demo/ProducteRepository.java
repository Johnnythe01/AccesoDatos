package com.example.demo;

// import com.example.demo.Producte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducteRepository extends JpaRepository<Producte, Integer> {
}
