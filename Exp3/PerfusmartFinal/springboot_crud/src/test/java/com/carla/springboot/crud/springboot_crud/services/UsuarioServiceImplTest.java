package com.carla.springboot.crud.springboot_crud.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.carla.springboot.crud.springboot_crud.entities.Usuario;
import com.carla.springboot.crud.springboot_crud.repository.usuario.UsuarioRepository;

public class UsuarioServiceImplTest {
     @InjectMocks
    private UsuarioServiceImpl service;
    @Mock
    private UsuarioRepository repository;
    

    List<Usuario> list=new ArrayList<Usuario>();
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeUsuario();
    }

    @Test
    public void UsuarioFindByAllTest(){
        when(repository.findAll()).thenReturn(list);
        List<Usuario>response=service.findByAll();

        assertEquals(3, response.size());

        verify(repository,times(1)).findAll();
    }

     @Test
    public void UsuarioFindByIdTest(){

        Usuario Usuario=new Usuario("21861087-0", "Gonzalo", "Navarrete", "passw0rd", "Empleado", "gonzalonavarrete@gmail.com", "+569 83022268");
        when(repository.findById("21861087-0")).thenReturn(Optional.of(Usuario));

        Optional<Usuario> response = service.findById("21861087-0");


        assertTrue(response.isPresent());
        assertEquals("Gonzalo", response.get().getNombre());
        verify(repository, times(1)).findById("21861087-0");

    }

    @Test
public void crearUsuarioTest() {
    // Usuario con ID manual asignado
    Usuario usuario = new Usuario("21861087-0", "Nombre Nuevo", "Apellido Nuevo", "Pass nueva", "Rol Nuevo", "Correo Nuevo", "Numero nuevo");

    // Simula que al guardar, retorna el mismo usuario
    when(repository.save(usuario)).thenReturn(usuario);

    Usuario response = service.save(usuario);

    // Verificaciones
    assertNotNull(response);
    assertEquals("21861087-0", response.getRut());
    assertEquals("Nombre Nuevo", response.getNombre());

    verify(repository, times(1)).save(usuario);
}


    @Test

    public void modificarUsuarioTest() {
        Usuario UsuarioOriginal = new Usuario("21861087-0", "Nombre antiguo", "Apellido antiguo", "Pass antigua", "Rol antiguo", "Correo antiguo", "Numero antiguo");
        Usuario UsuarioModificado = new Usuario("21861087-0", "Nombre Modificado", "Apellido Modificado", "Pass Modificado", "Rol Modificado", "Correo Modificado", "Numero Modificado");

        when(repository.findById("21861087-0")).thenReturn(Optional.of(UsuarioOriginal));
        when(repository.save(any(Usuario.class))).thenReturn(UsuarioModificado); 

        Optional<Usuario> UsuarioBD = service.findById("21861087-0");
        Usuario actualizado = null;
        if (UsuarioBD.isPresent()) {
            Usuario u = UsuarioBD.get();
            u.setNombre("Nombre Modificado");
            u.setApellido("Apellido Modificado");
            u.setcontrasenna("Pass Modificado");
            u.setRol("Rol Modificado");
            u.setCorreo("Correo Modificado");

            actualizado = service.save(u);
        }

        assertNotNull(actualizado);
        assertEquals("Nombre Modificado", actualizado.getNombre());
        assertEquals("Apellido Modificado", actualizado.getApellido());
        assertEquals("Rol Modificado", actualizado.getRol());
        verify(repository).findById("21861087-0");
        verify(repository).save(any(Usuario.class));
    }

    @Test
    public void eliminarUsuarioTest() {
        // Arrange
        Usuario UsuarioAEliminar = new Usuario("21861087-0", "Nombre a Eliminar", "Apellido a Eliminar", "Pass a Eliminar", "Rol a Eliminar", "Correo a Eliminar", "Numero a Eliminar");

        when(repository.findById("21861087-0")).thenReturn(Optional.of(UsuarioAEliminar));

        // Act
        Optional<Usuario> response = service.delete(UsuarioAEliminar);

        // Assert
        assertTrue(response.isPresent());
        assertEquals("Nombre a Eliminar", response.get().getNombre());

        verify(repository, times(1)).findById("21861087-0");
        verify(repository, times(1)).delete(UsuarioAEliminar);
    }


    public void  chargeUsuario(){
        Usuario usuario1 =new Usuario("Rut 1", "Nombre 1", "Apellido 1", "Pass 1", "Rol 1", "Correo 1", "Numero 1");
        Usuario usuario2 =new Usuario("Rut 2", "Nombre 2", "Apellido 2", "Pass 2", "Rol 2", "Correo 2", "Numero 2");
        Usuario usuario3 =new Usuario("Rut 3", "Nombre 3", "Apellido 3", "Pass 3", "Rol 3", "Correo 3", "Numero 3");
        
        list.add(usuario1);
        list.add(usuario2);
        list.add(usuario3);
    }

}