package com.carla.springboot.crud.springboot_crud.services;

import static org.junit.jupiter.api.Assertions.*;
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

import com.carla.springboot.crud.springboot_crud.entities.Cliente;
import com.carla.springboot.crud.springboot_crud.repository.cliente.ClienteRepository;

public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl service;
    @Mock
    private ClienteRepository repository;
    
    List<Cliente> list = new ArrayList<Cliente>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeCliente();
    }

    public void chargeCliente(){
        Cliente cliente1 = new Cliente(1L, "Juanes", "Eshpaña", "gfdh5t345", "hola@hola.hola", "+56943543534", "enrique segoviano 453", "tarjeta debito");
        Cliente cliente2 = new Cliente(2L, "Fernando", "Zarate", "fdsdr43tr43", "fzarate@duocuc.cl", "+5695463452", "esquina blanca 501", "tarjeta debito");
        Cliente cliente3 = new Cliente(3L, "Gonzalo", "Navarrete", "fdhrth332", "gon.navarrete@duocuc.cl", "+56943523523", "Tres poniente 432", "tarjeta credito");

        list.add(cliente1);
        list.add(cliente2);
        list.add(cliente3);
    }

    @Test
    public void clienteFindByAllTest(){
        when(repository.findAll()).thenReturn(list);
        List<Cliente>response = service.findByAll();

        assertEquals(3, response.size());

        verify(repository,times(1)).findAll();
    }

    @Test
    public void clienteFindByIdTest(){

        Cliente cliente = new Cliente(2L, "Gustavo", "Petro", "gfh5et4wr342", "null", "+569453543", "avenida poto 43543", "efectivo");
        when(repository.findById(2L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> response = service.findById(2L);

        assertTrue(response.isPresent());
        assertEquals("Gustavo", response.get().getNombre());
        verify(repository, times(1)).findById(2L);

    }

    @Test
    public void crearClienteTest(){

        Cliente clienteSinId = new Cliente(null, "Lagarto", "Juancho", "hgfh54t43", "ola99@ola.com", "+76856435", "calle 5645", "efectivo");

        Cliente clienteConId = new Cliente(10L, "Lagarto", "Juancho", "hgfh54t43", "ola99@ola.com", "+76856435", "calle 5645", "efectivo");

        when(repository.save(clienteSinId)).thenReturn(clienteConId);

        Cliente response = service.save(clienteSinId);

        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("Lagarto", response.getNombre());

        verify(repository, times(1)).save(clienteSinId);

    }

    @Test
    public void modificarClienteTest() {
        Cliente clienteOriginal = new Cliente(3L, "Carrie", "Prado", "54654623hgf", "cprado@correo.com", "+54654567823", "calle 7334", "tarjeta credito");
        Cliente clienteModificado = new Cliente(3L, "Carla", "Muñoz", "gfdg343", "ca.pradom@duocuc.cl", "+65345435", "calle 5646", "tarjeta debito");

        when(repository.findById(3L)).thenReturn(Optional.of(clienteOriginal));
        when(repository.save(any(Cliente.class))).thenReturn(clienteModificado); 

        Optional<Cliente> clienteBD = service.findById(3L);
        Cliente actualizado = null;
        if (clienteBD.isPresent()) {
            Cliente c = clienteBD.get();
            c.setNombre("Carla");
            c.setApellido("Muñoz");
            c.setContrasenna("gfdg343");
            c.setCorreo("ca.pradom@duocuc.cl");
            c.setNumero("+65345435");
            c.setDireccion("calle 5646");
            c.setMetodoPago("tarjeta debito");

            actualizado = service.save(c);
        }

        assertNotNull(actualizado);
        assertEquals("Carla", actualizado.getNombre());
        assertEquals("Muñoz", actualizado.getApellido());
        assertEquals("gfdg343", actualizado.getContrasenna());

        verify(repository).findById(3L);
        verify(repository).save(any(Cliente.class));
    }

    @Test
    public void eliminarClienteTest() {
        // Arrange
        Cliente clienteAEliminar = new Cliente(4L, "Cliente Eliminar", "Pérez", "fdgdfr3wr", "cl@hola.cl", "+56634534345", "Albilla 4352", "tarjeta debito");

        when(repository.findById(4L)).thenReturn(Optional.of(clienteAEliminar));

        // Act
        Optional<Cliente> response = service.delete(clienteAEliminar);

        // Assert
        assertTrue(response.isPresent());
        assertEquals("Cliente Eliminar", response.get().getNombre());

        verify(repository, times(1)).findById(4L);
        verify(repository, times(1)).delete(clienteAEliminar);
    }

}
