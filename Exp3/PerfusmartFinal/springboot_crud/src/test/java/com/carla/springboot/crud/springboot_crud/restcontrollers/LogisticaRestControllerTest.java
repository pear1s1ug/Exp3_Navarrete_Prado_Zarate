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
import com.carla.springboot.crud.springboot_crud.entities.Logistica;
import com.carla.springboot.crud.springboot_crud.services.LogisticaServiceImpl;


@SpringBootTest
@AutoConfigureMockMvc
public class LogisticaRestControllerTest {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private LogisticaServiceImpl LogisticaServiceImpl;
    private List<Logistica> logisticaLista;

    @Test
    public void verLogisticaTest() throws Exception{
        when(LogisticaServiceImpl.findByAll()).thenReturn(logisticaLista);
        mockmvc.perform(get("/api/logistica")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verDatosTest(){
        Logistica laLogistica = new Logistica(1L, 1234, 45000, 50, 1, "YSL", "25-02-2020", "30-02-2020", "SAN ANTONIO", "cuba"); 
        try{
            when(LogisticaServiceImpl.findById(1L)).thenReturn(Optional.of(laLogistica));
            mockmvc.perform(get("/api/logistica/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error "+ ex.getMessage());
        }
    }

    @Test
    public void productoNoExisteTest() throws Exception{
        when(LogisticaServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/logistica/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

   @Test
    public void crearDatoTest() throws Exception{
        Logistica laLogistica=new Logistica(null, 3043, 50000, 2, 5, "Amongus le parfum", "15-12-2020", "30-01-2020", "SAN Pepe", "tilin Perú");
        Logistica otraLogistica=new Logistica(4L, 3030, 123000, 53, 7, "Tilin Insano Edp", "10-12-2023", "25-01-2024", "Lo espejo", "casa Jeremy");
        when(LogisticaServiceImpl.save(any(Logistica.class))).thenReturn(otraLogistica);
        mockmvc.perform(post("/api/logistica")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(laLogistica)))
            .andExpect(status().isCreated());

    } 

    @Test
    public void eliminarDatosTest() throws Exception {
        Logistica datosAEliminar = new Logistica();
        datosAEliminar.setIdEnvio(2L);

        when(LogisticaServiceImpl.delete(any(Logistica.class))).thenReturn(Optional.of(datosAEliminar));

        mockmvc.perform(delete("/api/logistica/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void eliminarDatosNoExisteTest() throws Exception {
        when(LogisticaServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockmvc.perform(delete("/api/logistica/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void modificarDatosExistenteTest() throws Exception {
        Long idEnvio = 1L;

        Logistica datosExistentes = new Logistica(idEnvio, 30, 30000, 3, 1, "Duoc Intense", "10-04-2023", "30-09-2023", "Duoc maipu", "bing chilling");
        Logistica datosActualizado = new Logistica(idEnvio, 40, 30000, 3, 1, "Duoc eau le parfum", "31-10-2023", "30-10-2023", "Duoc Quintero", "chapa la pachala");

        when(LogisticaServiceImpl.findById(idEnvio)).thenReturn(Optional.of(datosExistentes));
        when(LogisticaServiceImpl.save(any(Logistica.class))).thenReturn(datosActualizado);

        mockmvc.perform(put("/api/logistica/{idEnvio}", idEnvio)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(datosActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void modificarDatosNoExisteTest() throws Exception {
        Long idEnvio = 999L;
        Logistica logistica = new Logistica(idEnvio, 30, 45000, 5, 1, "Duoc skibi intense", "30-08-2024", "30-10-2024", "lo prado", "san antonio");

        // Simular que no se encontró ningún producto con ese ID
        when(LogisticaServiceImpl.findById(idEnvio)).thenReturn(Optional.empty());

        mockmvc.perform(put("/api/logistica/{idEnvio}", idEnvio)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(logistica)))
                .andExpect(status().isNotFound());
    }


}