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

import com.carla.springboot.crud.springboot_crud.entities.Venta;
import com.carla.springboot.crud.springboot_crud.repository.venta.VentaRepository;

public class VentaServiceImplTest {

    @InjectMocks
    private VentaServiceImpl service;
    @Mock
    private VentaRepository repository;
    

    List<Venta> list=new ArrayList<Venta>();
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeVenta();
    }

    @Test
    public void VentaFindByAllTest(){
        when(repository.findAll()).thenReturn(list);
        List<Venta>response=service.findByAll();

        assertEquals(3, response.size());

        verify(repository,times(1)).findAll();
    }

     @Test
    public void VentaFindByIdTest(){

        Venta Venta=new Venta(2L, "Nombre Producto", 20000, "RutCliente");
        when(repository.findById(2L)).thenReturn(Optional.of(Venta));

        Optional<Venta> response = service.findById(2L);


        assertTrue(response.isPresent());
        assertEquals("Nombre Producto", response.get().getProducto());
        verify(repository, times(1)).findById(2L);

    }

    @Test
    public void crearVentaTest(){

        Venta VentaSinId = new Venta(null, "Nombre Producto nuevo", 20000, "RutCliente nuevo");

        Venta VentaConId = new Venta(10L, "Nombre Producto nuevo", 20000, "RutCliente nuevo");

        when(repository.save(VentaSinId)).thenReturn(VentaConId);

        Venta response = service.save(VentaSinId);

        assertNotNull(response);
        assertEquals(10L, response.getidVenta());
        assertEquals("Nombre Producto nuevo", response.getProducto());

    verify(repository, times(1)).save(VentaSinId);

    }

    @Test

    public void modificarVentaTest() {
        Venta VentaOriginal = new Venta(10L, "Nombre Producto antiguo", 20000, "RutCliente antiguo");
        Venta VentaModificado = new Venta(10L, "Nombre Producto Actualizado", 18000, "RutCliente Actualizado");

        when(repository.findById(3L)).thenReturn(Optional.of(VentaOriginal));
        when(repository.save(any(Venta.class))).thenReturn(VentaModificado); 

        Optional<Venta> VentaBD = service.findById(3L);
        Venta actualizado = null;
        if (VentaBD.isPresent()) {
            Venta v = VentaBD.get();
            v.setProducto("Nombre Producto Actualizado");
            v.setPrecio(18000);
            v.setRut("RutCliente Actualizado");

            actualizado = service.save(v);
        }

        assertNotNull(actualizado);
        assertEquals("Nombre Producto Actualizado", actualizado.getProducto());
        assertEquals(18000, actualizado.getPrecio());
        assertEquals("RutCliente Actualizado", actualizado.getRut());

        verify(repository).findById(3L);
        verify(repository).save(any(Venta.class));
    }

    @Test
    public void eliminarVentaTest() {
        // Arrange
        Venta VentaAEliminar = new Venta(4L, "Producto a Eliminar", 10000, "Rut a eliminar");

        when(repository.findById(4L)).thenReturn(Optional.of(VentaAEliminar));

        // Act
        Optional<Venta> response = service.delete(VentaAEliminar);

        // Assert
        assertTrue(response.isPresent());
        assertEquals("Producto a Eliminar", response.get().getProducto());

        verify(repository, times(1)).findById(4L);
        verify(repository, times(1)).delete(VentaAEliminar);
    }


    public void  chargeVenta(){
        Venta venta1 =new Venta(1L, "Producto 1",10000,"Marca 1");        
        Venta venta2 =new Venta(2L, "Producto 2",20000,"Marca 2");        
        Venta venta3 =new Venta(3L, "Producto 3",10000,"Marca 3");        
        list.add(venta1);
        list.add(venta2);
        list.add(venta3);
    }


}