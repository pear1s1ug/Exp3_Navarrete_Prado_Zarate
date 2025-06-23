package com.carla.springboot.crud.springboot_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Producto;

import com.carla.springboot.crud.springboot_crud.services.ProductoService;

@RestController
@RequestMapping("api/productos")

public class ProductoController {
    
    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> List(){
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional <Producto> productoOptional=service.findById(id);
        if (productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<Producto> crear (@RequestBody Producto unProducto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unProducto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Producto unProducto){
        Optional<Producto> productoOptional=service.findById(id);
        if (productoOptional.isPresent()){
            Producto productoexistente=productoOptional.get();
            productoexistente.setNombre(unProducto.getNombre());
            productoexistente.setPrecio(unProducto.getPrecio());
            productoexistente.setMarca(unProducto.getMarca());
            productoexistente.setTipo(unProducto.getTipo());
            productoexistente.setCantidad(unProducto.getCantidad());
            Producto productomodificado= service.save(productoexistente);
            return ResponseEntity.ok(productomodificado);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Producto unProducto=new Producto();
        unProducto.setId(id);
        Optional<Producto> productoOptional=service.delete(unProducto);
        if(productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


}
