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
import com.carla.springboot.crud.springboot_crud.entities.Venta;
import com.carla.springboot.crud.springboot_crud.services.VentaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
public class VentasRestControllerTest {
    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private VentaServiceImpl ventaServiceImpl;
    private List<Venta> ventasLista;


    @Test
    public void verventasTest() throws Exception{
        when(ventaServiceImpl.findByAll()).thenReturn(ventasLista);
        mockmvc.perform(get("/api/ventas")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verDetalleTest(){
        Venta unVenta=new Venta(1L, "Lancôme – La Vie Est Belle Eau de Parfum 100 ml", 45000,"21861087000"); 
        try{
            when(ventaServiceImpl.findById(1L)).thenReturn(Optional.of(unVenta));
            mockmvc.perform(get("/api/ventas/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error "+ ex.getMessage());
        }
    }

    @Test
    public void VentaNoExisteTest() throws Exception{
        when(ventaServiceImpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/ventas/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void crearVentaTest() throws Exception{
        Venta unVenta=new Venta(null, "Lancôme – La Vie Est Belle Eau de Parfum 100 ml", 45000,"21861087000");
        Venta otroVenta=new Venta(4L, "Yves Saint Laurent – Black Opium Eau de Parfum 90 ml", 100000,"21861087111");
        when(ventaServiceImpl.save(any(Venta.class))).thenReturn(otroVenta);
        mockmvc.perform(post("/api/ventas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(unVenta)))
            .andExpect(status().isCreated());

    }

    @Test
    public void eliminarVentaTest() throws Exception {
        Venta VentaAEliminar = new Venta();
        VentaAEliminar.setidVenta(2L);

        when(ventaServiceImpl.delete(any(Venta.class))).thenReturn(Optional.of(VentaAEliminar));

        mockmvc.perform(delete("/api/ventas/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
}

    @Test
    public void eliminarVentaNoExisteTest() throws Exception {
        when(ventaServiceImpl.findById(99L)).thenReturn(Optional.empty());

        mockmvc.perform(delete("/api/ventas/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void modificarVentaExistenteTest() throws Exception {
        Long id = 1L;

        Venta VentaExistente = new Venta(id, "Venta Vieja", 40000, "2187394670");
        Venta VentaActualizado = new Venta(id, "Venta Nueva",  45000, "1825639471");

        when(ventaServiceImpl.findById(id)).thenReturn(Optional.of(VentaExistente));
        when(ventaServiceImpl.save(any(Venta.class))).thenReturn(VentaActualizado);

        mockmvc.perform(put("/api/ventas/{idVenta}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VentaActualizado)))
                .andExpect(status().isOk());
    }

    @Test
    public void modificarVentaNoExisteTest() throws Exception {
        Long id = 999L;
        Venta Venta = new Venta(id,"Desconocido" ,12345 , "N/A");

        // Simular que no se encontró ningún Venta con ese ID
        when(ventaServiceImpl.findById(id)).thenReturn(Optional.empty());

        mockmvc.perform(put("/api/ventas/{idVenta}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Venta)))
                .andExpect(status().isNotFound());
    }

}