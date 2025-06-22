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
import com.carla.springboot.crud.springboot_crud.entities.Usuario;
import com.carla.springboot.crud.springboot_crud.services.UsuarioServiceImpl;
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private UsuarioServiceImpl usuarioServiceImpl;
    private List<Usuario> usuariosLista;
    
    @Test
    public void verusuariosTest() throws Exception{
        when(usuarioServiceImpl.findByAll()).thenReturn(usuariosLista);
        mockmvc.perform(get("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verDetalleTest(){
        Usuario unUsuario=new Usuario("218610800", "Gonzalo", "Navarrete","password ","Empleado","gonzalonavarrete@gmail.com","+569 99999999" ); 
        try{
            when(usuarioServiceImpl.findById("218610800")).thenReturn(Optional.of(unUsuario));
            mockmvc.perform(get("/api/usuarios/218610800")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error "+ ex.getMessage());
        }
    }

    @Test
    public void UsuarioNoExisteTest() throws Exception{
        when(usuarioServiceImpl.findById("218610801")).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/usuarios/218610801")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception{
        Usuario unUsuario=new Usuario(null, "Ariel", "Bravo","passw0rd","Administrador","Arielbravo@gmail.com","+569 99969999");
        Usuario otroUsuario=new Usuario("2186108777", "Pedro", "Lopez","contraasenna","Gerente","Pedrolopez@gmail.com","+569 99967999");
        when(usuarioServiceImpl.save(any(Usuario.class))).thenReturn(otroUsuario);
        mockmvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unUsuario)))
            .andExpect(status().isCreated());

    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        Usuario UsuarioAEliminar = new Usuario();
        UsuarioAEliminar.setRut("2186108999");

        when(usuarioServiceImpl.delete(any(Usuario.class))).thenReturn(Optional.of(UsuarioAEliminar));

        mockmvc.perform(delete("/api/usuarios/2186108999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
}

    @Test
    public void eliminarUsuarioNoExisteTest() throws Exception {
        when(usuarioServiceImpl.findById("21861087666")).thenReturn(Optional.empty());

        mockmvc.perform(delete("/api/usuarios/218610876669")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void modificarUsuarioExistenteTest() throws Exception {
        String id = "2186108777";

        Usuario UsuarioExistente = new Usuario(id, "Pedro", "Lopez","contraasenna","Gerente","Pedrolopez@gmail.com","+569 99967999");
        Usuario UsuarioActualizado = new Usuario(id, "Pedro", "Lopez","contraasenna","Desvinculado","Pedrolopez@gmail.com","+569 99967999");

        when(usuarioServiceImpl.findById(id)).thenReturn(Optional.of(UsuarioExistente));
        when(usuarioServiceImpl.save(any(Usuario.class))).thenReturn(UsuarioActualizado);

        mockmvc.perform(put("/api/usuarios/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(UsuarioActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void modificarUsuarioNoExisteTest() throws Exception {
        String id = "2186108777";
        Usuario Usuario = new Usuario(id, "Pedro", "Lopez","contraasenna","Desvinculado","Pedrolopez@gmail.com","+569 99967999");

        // Simular que no se encontró ningún Usuario con ese ID
        when(usuarioServiceImpl.findById(id)).thenReturn(Optional.empty());

        mockmvc.perform(put("/api/usuarios/{rut}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Usuario)))
                .andExpect(status().isNotFound());
    }
}