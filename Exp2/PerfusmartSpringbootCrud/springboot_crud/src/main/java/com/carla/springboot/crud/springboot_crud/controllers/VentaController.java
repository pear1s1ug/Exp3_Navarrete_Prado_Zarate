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

import com.carla.springboot.crud.springboot_crud.entities.Venta;
import com.carla.springboot.crud.springboot_crud.services.VentaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/ventas")
public class VentaController {
    @Autowired
    private VentaService service;

    @GetMapping
    public List<Venta> List(){
        return service.findByAll();
    }
    @GetMapping("/{idVenta}")
    public ResponseEntity<?> verDetalle(@PathVariable Long idVenta){
        Optional <Venta> ventaOptional=service.findById(idVenta);
        if (ventaOptional.isPresent()){
            return ResponseEntity.ok(ventaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<Venta> crear (@RequestBody Venta unVenta){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unVenta));
    }
    @PutMapping("/{idVenta}")
    public ResponseEntity<?> modificar(@PathVariable Long idVenta, @RequestBody Venta unVenta){
        Optional<Venta> ventaOptional=service.findById(idVenta);
        if (ventaOptional.isPresent()){
            Venta ventaexistente=ventaOptional.get();
            ventaexistente.setProducto(unVenta.getProducto());
            ventaexistente.setPrecio(unVenta.getPrecio());
            ventaexistente.setRut(unVenta.getRut());
            Venta ventamodificado= service.save(ventaexistente);
            return ResponseEntity.ok(ventamodificado);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{idVenta}")
    public ResponseEntity<?> eliminar(@PathVariable Long idVenta){
        Venta unVenta=new Venta();
        unVenta.setidVenta(idVenta);
        Optional<Venta> ventaOptional=service.delete(unVenta);
        if(ventaOptional.isPresent()){
            return ResponseEntity.ok(ventaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
