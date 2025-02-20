package com.example.service;

import com.example.model.Producte;
import com.example.repository.ProducteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProducteService {
    private final ProducteRepository repository;

    public ProducteService(ProducteRepository repository) {
        this.repository = repository;
    }

    public List<Producte> getAll() {
        return repository.findAll();
    }

    public Optional<Producte> getById(Integer id) {
        return repository.findById(id);
    }

    public Producte create(Producte producte) {
        return repository.save(producte);
    }

    public Producte update(Integer id, Producte producte) {
        return repository.findById(id).map(p -> {
            p.setNom(producte.getNom());
            p.setDescripcio(producte.getDescripcio());
            p.setPreu(producte.getPreu());
            p.setQuantitat(producte.getQuantitat());
            return repository.save(p);
        }).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
