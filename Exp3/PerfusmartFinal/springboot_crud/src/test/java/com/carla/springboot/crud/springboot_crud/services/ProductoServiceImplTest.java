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

import com.carla.springboot.crud.springboot_crud.entities.Producto;
import com.carla.springboot.crud.springboot_crud.repository.producto.ProductoRepository;
public class ProductoServiceImplTest {
    @InjectMocks
    private ProductoServiceImpl service;
    @Mock
    private ProductoRepository repository;
    

    List<Producto> list=new ArrayList<Producto>();
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeProducto();
    }

    @Test
    public void productoFindByAllTest(){
        when(repository.findAll()).thenReturn(list);
        List<Producto>response=service.findByAll();

        assertEquals(3, response.size());

        verify(repository,times(1)).findAll();
    }

     @Test
    public void productoFindByIdTest(){

        Producto producto=new Producto(2L, "Perfume2", 20000, "Marca2", "Tipo2", 20);
        when(repository.findById(2L)).thenReturn(Optional.of(producto));

        Optional<Producto> response = service.findById(2L);


        assertTrue(response.isPresent());
        assertEquals("Perfume2", response.get().getNombre());
        verify(repository, times(1)).findById(2L);

    }

    @Test
    public void crearProductoTest(){

        Producto productoSinId = new Producto(null, "Perfume Nuevo", 25000, "MarcaX", "TipoX", 15);

        Producto productoConId = new Producto(10L, "Perfume Nuevo", 25000, "MarcaX", "TipoX", 15);

        when(repository.save(productoSinId)).thenReturn(productoConId);

        Producto response = service.save(productoSinId);

        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("Perfume Nuevo", response.getNombre());

    verify(repository, times(1)).save(productoSinId);

    }

    @Test

    public void modificarProductoTest() {
        Producto productoOriginal = new Producto(3L, "Perfume Viejo", 15000, "MarcaAntigua", "TipoA", 5);
        Producto productoModificado = new Producto(3L, "Perfume Actualizado", 18000, "MarcaNueva", "TipoB", 10);

        when(repository.findById(3L)).thenReturn(Optional.of(productoOriginal));
        when(repository.save(any(Producto.class))).thenReturn(productoModificado); 

        Optional<Producto> productoBD = service.findById(3L);
        Producto actualizado = null;
        if (productoBD.isPresent()) {
            Producto p = productoBD.get();
            p.setNombre("Perfume Actualizado");
            p.setPrecio(18000);
            p.setMarca("MarcaNueva");
            p.setTipo("TipoB");
            p.setCantidad(10);

            actualizado = service.save(p);
        }

        assertNotNull(actualizado);
        assertEquals("Perfume Actualizado", actualizado.getNombre());
        assertEquals(18000, actualizado.getPrecio());
        assertEquals("MarcaNueva", actualizado.getMarca());

        verify(repository).findById(3L);
        verify(repository).save(any(Producto.class));
    }

    @Test
    public void eliminarProductoTest() {
        // Arrange
        Producto productoAEliminar = new Producto(4L, "Perfume Eliminar", 10000, "MarcaZ", "TipoZ", 3);

        when(repository.findById(4L)).thenReturn(Optional.of(productoAEliminar));

        // Act
        Optional<Producto> response = service.delete(productoAEliminar);

        // Assert
        assertTrue(response.isPresent());
        assertEquals("Perfume Eliminar", response.get().getNombre());

        verify(repository, times(1)).findById(4L);
        verify(repository, times(1)).delete(productoAEliminar);
    }


    public void  chargeProducto(){
        Producto prod1 =new Producto(1L, "Perfume1",10000,"Marca1","Tipo1",10);
        Producto prod2 =new Producto(2L, "Perfume2",20000,"Marca2","Tipo2",20);
        Producto prod3 =new Producto(3L, "Perfume3",30000,"Marca3","Tipo3",30);

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }






}