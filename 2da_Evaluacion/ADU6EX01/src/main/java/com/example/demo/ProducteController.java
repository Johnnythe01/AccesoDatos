package com.example.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Anotación que indica que es un controlador REST
@RequestMapping("/productes") // Ruta base para las peticiones
public class ProducteController { // Clase controlador

    private final ProducteService service;

    public ProducteController(ProducteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Producte> create(@RequestBody Producte producte) { // Método para crear un producto
        Producte createdProducte = service.create(producte); // Llama al método de creación del servicio
        return ResponseEntity.status(201).body(createdProducte); // Devuelve el producto creado
    }

    @GetMapping
    public ResponseEntity<List<Producte>> getAll() { // Método para obtener todos los productos
        return ResponseEntity.ok(service.getAll()); // Devuelve la lista de productos
    }

    @GetMapping("/{id}") // Método para obtener un producto por su id
    public ResponseEntity<Producte> getById(@PathVariable Integer id) { // Recibe el id como parámetro
        return service.getById(id) // Llama al método de obtención del servicio
                .map(ResponseEntity::ok) // Si existe, devuelve el producto
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve un error 404
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producte> update(@PathVariable Integer id, @RequestBody Producte producte) {
        Producte updatedProducte = service.update(id, producte);
        return updatedProducte != null ? ResponseEntity.ok(updatedProducte) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
