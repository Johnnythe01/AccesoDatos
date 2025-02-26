package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        return repository.findById(id).map(existingProduct -> {
            existingProduct.setNom(producte.getNom());
            existingProduct.setDescripcio(producte.getDescripcio());
            existingProduct.setPreu(producte.getPreu());
            existingProduct.setQuantitat(producte.getQuantitat());
            return repository.save(existingProduct);
        }).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
