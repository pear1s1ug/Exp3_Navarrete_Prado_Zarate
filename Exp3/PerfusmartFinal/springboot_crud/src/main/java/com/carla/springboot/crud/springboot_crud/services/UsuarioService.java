package com.carla.springboot.crud.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Usuario;


public interface UsuarioService {
    List<Usuario> findByAll();

    Optional<Usuario> findById(String rut);

    Usuario save(Usuario unUsuario);

    Optional<Usuario> delete(Usuario unUsuario);

}