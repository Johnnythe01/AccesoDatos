package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service 
public class ProducteService { // En esta clase está la lógica de negocio

    private final ProducteRepository repository; // Repositorio de productos

    public ProducteService(ProducteRepository repository) { 
        this.repository = repository; 
    }

    public List<Producte> getAll() {
        return repository.findAll(); // Devuelve la lista de productos
    }

    public Optional<Producte> getById(Integer id) {
        return repository.findById(id); // Devuelve el producto si existe
    }

    public Producte create(Producte producte) { 
        return repository.save(producte); // Guarda el producto en la base de datos
    }

    public Producte update(Integer id, Producte producte) { // Con este método modificamos un producto
        return repository.findById(id).map(existingProduct -> { 
            existingProduct.setNom(producte.getNom());
            existingProduct.setDescripcio(producte.getDescripcio());
            existingProduct.setPreu(producte.getPreu());
            existingProduct.setQuantitat(producte.getQuantitat());
            return repository.save(existingProduct);
        }).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id); // Borra el producto de la base de datos por su id
    }
}
