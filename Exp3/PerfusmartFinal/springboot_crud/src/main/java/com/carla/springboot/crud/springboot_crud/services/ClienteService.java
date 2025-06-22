package com.carla.springboot.crud.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Cliente;

public interface ClienteService {

    List<Cliente> findByAll();

    Optional<Cliente> findById(Long id);

    Cliente save(Cliente unCliente);

    Optional<Cliente> delete(Cliente unCliente);

}
