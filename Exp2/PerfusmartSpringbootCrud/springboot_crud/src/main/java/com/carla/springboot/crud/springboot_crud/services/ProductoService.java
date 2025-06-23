package com.carla.springboot.crud.springboot_crud.services;
import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Producto;

public interface ProductoService {
    List<Producto> findByAll();

    Optional<Producto> findById(Long id);//optional validador en caso de que no se encuentre un dato

    Producto save(Producto unProducto);

    Optional<Producto> delete(Producto unProducto);

}
