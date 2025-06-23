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


import com.carla.springboot.crud.springboot_crud.entities.Usuario;
import com.carla.springboot.crud.springboot_crud.services.UsuarioService;


@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> List(){
        return service.findByAll();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<?> verDetalle(@PathVariable Long rut){
        Optional <Usuario> usuarioOptional=service.findById(rut);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<Usuario> crear (@RequestBody Usuario unUsuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }


    @PutMapping("/{rut}")
    public ResponseEntity<?> modificar(@PathVariable Long rut, @RequestBody Usuario unUsuario){
        Optional<Usuario> usuarioOptional=service.findById(rut);
        if (usuarioOptional.isPresent()){
            Usuario usuarioexistente=usuarioOptional.get();
            usuarioexistente.setNombre(unUsuario.getNombre());
            usuarioexistente.setApellido(unUsuario.getApellido());
            usuarioexistente.setcontrasenna(unUsuario.getcontrasenna());
            usuarioexistente.setRol(unUsuario.getRol());
            usuarioexistente.setCorreo(unUsuario.getCorreo());
            usuarioexistente.setNumero(unUsuario.getNumero());
            Usuario usuariomodificado= service.save(usuarioexistente);
            return ResponseEntity.ok(usuariomodificado);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{rut}")
    public ResponseEntity<?> eliminar(@PathVariable Long rut){
        Usuario unUsuario=new Usuario();
        unUsuario.setRut(rut);
        Optional<Usuario> usuarioOptional=service.delete(unUsuario);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
