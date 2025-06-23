package com.carla.springboot.crud.springboot_crud.repository;

import org.springframework.data.repository.CrudRepository;

import com.carla.springboot.crud.springboot_crud.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
