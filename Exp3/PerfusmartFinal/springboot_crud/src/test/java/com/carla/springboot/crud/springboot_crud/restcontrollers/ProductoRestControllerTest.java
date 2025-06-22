package com.carla.springboot.crud.springboot_crud.restcontrollers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import com.carla.springboot.crud.springboot_crud.entities.Producto;
import com.carla.springboot.crud.springboot_crud.services.ProductoServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductoRestControllerTest {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private ProductoServiceImpl productoServiceImpl;
    private List<Producto> productosLista;

    @Test
    public void verProductosTest() throws Exception{
        when(productoServiceImpl.findByAll()).thenReturn(productosLista);
        mockmvc.perform(get("/api/productos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verDetalleTest(){
        Producto unProducto=new Producto(1L, "La Vie Est Belle Eau de Parfum", 45000,"Lancôme ","De mujer",50 ); 
        try{
            when(productoServiceImpl.findById(1L)).thenReturn(Optional.of(unProducto));
            mockmvc.perform(get("/api/productos/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error "+ ex.getMessage());
        }
    }

    @Test
    public void productoNoExisteTest() throws Exception{
        when(productoServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/productos/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void crearProductoTest() throws Exception{
        Producto unProducto=new Producto(null, "La Vie Est Belle Eau de Parfum 100ml", 45000,"Lancôme ","De mujer",50);
        Producto otroProducto=new Producto(4L, "Black Opium Eau de Parfum 90 ml", 100000,"Yves Saint Laurent ","De mujer",50);
        when(productoServiceImpl.save(any(Producto.class))).thenReturn(otroProducto);
        mockmvc.perform(post("/api/productos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unProducto)))
            .andExpect(status().isCreated());

    }

    @Test
    public void eliminarProductoTest() throws Exception {
        Producto productoAEliminar = new Producto();
        productoAEliminar.setId(2L);

        when(productoServiceImpl.delete(any(Producto.class))).thenReturn(Optional.of(productoAEliminar));

        mockmvc.perform(delete("/api/productos/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
}

    @Test
    public void eliminarProductoNoExisteTest() throws Exception {
        when(productoServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockmvc.perform(delete("/api/productos/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void modificarProductoExistenteTest() throws Exception {
        Long id = 1L;

        Producto productoExistente = new Producto(id, "Perfume Viejo", 40000, "MarcaX", "De mujer", 10);
        Producto productoActualizado = new Producto(id, "Perfume Nuevo", 45000, "MarcaY", "De mujer", 20);

        when(productoServiceImpl.findById(id)).thenReturn(Optional.of(productoExistente));
        when(productoServiceImpl.save(any(Producto.class))).thenReturn(productoActualizado);

        mockmvc.perform(put("/api/productos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productoActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void modificarProductoNoExisteTest() throws Exception {
        Long id = 999L;
        Producto producto = new Producto(id, "Desconocido", 12345, "N/A", "N/A", 0);

        // Simular que no se encontró ningún producto con ese ID
        when(productoServiceImpl.findById(id)).thenReturn(Optional.empty());

        mockmvc.perform(put("/api/productos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isNotFound());
    }



}