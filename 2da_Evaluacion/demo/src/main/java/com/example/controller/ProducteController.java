package com.example.controller;

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

import com.example.model.Producte;
import com.example.service.ProducteService;

@RestController
@RequestMapping("/productes")
public class ProducteController {
    private final ProducteService service;

    public ProducteController(ProducteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Producte> create(@RequestBody Producte producte) {
        return ResponseEntity.ok(service.create(producte));
    }

    @GetMapping
    public ResponseEntity<List<Producte>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producte> getById(@PathVariable Integer id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producte> update(@PathVariable Integer id, @RequestBody Producte producte) {
        Producte updated = service.update(id, producte);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
