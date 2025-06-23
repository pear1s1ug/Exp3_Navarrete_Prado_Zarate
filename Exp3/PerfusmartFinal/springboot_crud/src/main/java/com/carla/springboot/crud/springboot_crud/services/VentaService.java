package com.carla.springboot.crud.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Venta;



public interface VentaService {

    List<Venta> findByAll();

    Optional<Venta> findById(Long idVenta);//optional validador en caso de que no se encuentre un dato

    Venta save(Venta unVenta);

    Optional<Venta> delete(Venta unVenta);
}


