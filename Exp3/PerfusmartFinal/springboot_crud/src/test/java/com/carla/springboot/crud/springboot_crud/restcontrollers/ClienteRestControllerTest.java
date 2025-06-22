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

import com.carla.springboot.crud.springboot_crud.entities.Cliente;
import com.carla.springboot.crud.springboot_crud.services.ClienteServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc

public class ClienteRestControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ClienteServiceImpl clienteServiceImpl;
    private List<Cliente> clientesLista;

    @Test
    public void verClientesTest() throws Exception{
        when(clienteServiceImpl.findByAll()).thenReturn(clientesLista);
        mockmvc.perform(get("/api/clientes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verDetalleTest(){
        Cliente unCliente=new Cliente(1L, "Pablo", "Mármol", "849nfjisfns", "pablomarmol@gmail.com", "+56945465353", "Calle falsa 4563", "Tarjeta debito"); 
        try{
            when(clienteServiceImpl.findById(1L)).thenReturn(Optional.of(unCliente));
            mockmvc.perform(get("/api/clientes/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error "+ ex.getMessage());
        }
    }

    @Test
    public void clienteNoExisteTest() throws Exception{
        when(clienteServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/clientes/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void crearClienteTest() throws Exception{
        Cliente unCliente = new Cliente(null, "Pepito", "Elbandolero", "6ryurdgdr", "ppito@outlook.com", "+56964645353", "Calle real 454", "Tarjeta credito");
        Cliente otroCliente = new Cliente(4L, "Noob", "Saibot", "hgfhf445r4", "boontobias@gmail.com", "+13984398473", "Villa las springboot 543", "Efectivo");
        when(clienteServiceImpl.save(any(Cliente.class))).thenReturn(otroCliente);
        mockmvc.perform(post("/api/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unCliente)))
            .andExpect(status().isCreated());
    }

    @Test
    public void eliminarClienteTest() throws Exception {
        Cliente clienteAEliminar = new Cliente();
        clienteAEliminar.setId(2L);

        when(clienteServiceImpl.delete(any(Cliente.class))).thenReturn(Optional.of(clienteAEliminar));

        mockmvc.perform(delete("/api/clientes/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarClienteNoExisteTest() throws Exception {
        when(clienteServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockmvc.perform(delete("/api/clientes/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void modificarClienteExistenteTest() throws Exception {
        Long id = 1L;

        Cliente clienteExistente = new Cliente(id, "Ben", "10", "afadfasda2", "ben10@gmail.com", "+56934832423", "Calle las casas 3424", "Tarjeta debito");
        Cliente clienteActualizado = new Cliente(id, "Hola", "Chao", "fdkohgdfo43", "china@xijinping.ca", "+634543543", "Emperador Hirohito 3423", "Tarjeta credito");

        when(clienteServiceImpl.findById(id)).thenReturn(Optional.of(clienteExistente));
        when(clienteServiceImpl.save(any(Cliente.class))).thenReturn(clienteActualizado);

        mockmvc.perform(put("/api/clientes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clienteActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void modificarClienteNoExisteTest() throws Exception {
        Long id = 999L;
        Cliente cliente = new Cliente(id, "Desconocido", "Unknown", "5634534", "nose@nose.com", "+54645645", "calle inexistente 324", "N/A");

        when(clienteServiceImpl.findById(id)).thenReturn(Optional.empty());

        mockmvc.perform(put("/api/clientes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isNotFound());
    }

}
