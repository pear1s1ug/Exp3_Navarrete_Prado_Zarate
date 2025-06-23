package com.carla.springboot.crud.springboot_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carla.springboot.crud.springboot_crud.entities.Usuario;

import com.carla.springboot.crud.springboot_crud.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly= true)//es read only porque es solo para verlo c:
    public List<Usuario> findByAll() {
        return (List<Usuario>) repository.findAll();
    }



    @Override
    @Transactional(readOnly= true)
    public Optional<Usuario > findById(Long id) {  
        return repository.findById(id);
    }


    
    @Override
    @Transactional
    public Usuario save(Usuario unUsuario ) {
        return repository.save(unUsuario );
    }
    

    @Override
    @Transactional
    public Optional<Usuario > delete(Usuario unUsuario ) {
        Optional <Usuario > usuarioOptional= repository.findById(unUsuario .getRut());
        usuarioOptional.ifPresent(UsuarioDb->{
            repository.delete(unUsuario );
        });
        return usuarioOptional;
    }

}
