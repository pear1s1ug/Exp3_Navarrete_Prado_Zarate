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

import com.carla.springboot.crud.springboot_crud.entities.Logistica;
import com.carla.springboot.crud.springboot_crud.repository.logistica.LogisticaRepository;
public class LogisticaServiceImplTest {
    @InjectMocks
    private LogisticaServiceImpl service;
    @Mock
    private LogisticaRepository repository;
    

    List<Logistica> list=new ArrayList<Logistica>();
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);

        this.chargeLogistica();
    }

    @Test
    public void logisticaFindByAllTest(){
        when(repository.findAll()).thenReturn(list);
        List<Logistica>response=service.findByAll();

        assertEquals(3, response.size());

        verify(repository,times(1)).findAll();
    }

     @Test
    public void logisticaFindByIdTest(){

        Logistica logistica=new Logistica(2L, 123, 5000 , 1, 0.3, "jean paul gaultier pour homme", "20-06-2025", "30-06-2025", "Duoc uc", "madagascar");
        when(repository.findById(2L)).thenReturn(Optional.of(logistica));

        Optional<Logistica> response = service.findById(2L);


        assertTrue(response.isPresent());
        assertEquals("jean paul gaultier pour homme", response.get().getProducto());
        verify(repository, times(1)).findById(2L);

    }

    @Test
    public void crearLogisticaTest(){

        Logistica logisticaSinId = new Logistica(null, 432, 54000, 2, 0.6, "jean paul gaultier le male", "27-06-2025", "10-06-2225", "Papu uc", "puchuncaví");

        Logistica logisticaConId = new Logistica( 10L, 432, 54000, 2, 0.6, "jean paul gaultier le male", "27-06-2025", "10-06-2225", "Papu uc", "puchuncaví");

        when(repository.save(logisticaSinId)).thenReturn(logisticaConId);

        Logistica response = service.save(logisticaSinId);

        assertNotNull(response);
        assertEquals(10L, response.getIdEnvio());
        assertEquals("jean paul gaultier le male", response.getProducto());

    verify(repository, times(1)).save(logisticaSinId);

    }

    @Test

    public void modificarLogisticaTest() {
        Logistica logisticaOriginal = new Logistica( 3L, 4032, 120000, 2, 0.9, "Azzaro Most Wanted", "27-10-2023", "10-06-2029", "skibidi uc", "san antonio");
        Logistica logisticaModificado = new Logistica( 3L, 4039, 180000, 1, 0.5, "Azzaro Most Wanted Elixir", "30-02-2024", "22-06-2030", "Changararanguiz uc", "san petes burgo");

        when(repository.findById(3L)).thenReturn(Optional.of(logisticaOriginal));
        when(repository.save(any(Logistica.class))).thenReturn(logisticaModificado); 

        Optional<Logistica> logisticaBD = service.findById(3L);
        Logistica actualizado = null;
        if (logisticaBD.isPresent()) {
            Logistica L = logisticaBD.get();
            L.setIdProducto(4032);
            L.setPrecio(180000);
            L.setCantidad(1);
            L.setPeso(0.5);
            L.setProducto("Azzaro Most Wanted Elixir");
            L.setFechaEnvio("30-02-2024");
            L.setFechaEntrega("22-06-2030");
            L.setOrigen("Changararanguiz uc");
            L.setDestino( "san petes burgo");

            actualizado = service.save(L);
        }

        assertNotNull(actualizado);
        assertEquals("Azzaro Most Wanted Elixir", actualizado.getProducto());
        assertEquals(180000, actualizado.getPrecio());
        assertEquals(4039, actualizado.getIdProducto());

        verify(repository).findById(3L);
        verify(repository).save(any(Logistica.class));
    }

    @Test
    public void eliminarLogisticaTest() {
        // Arrange
        Logistica logisticaAEliminar = new Logistica( 4L, 319, 200000, 1, 0.5, "Xerjoff Erba Pura", "21-01-2025", "22-01-2025", "Lo prado", "Duoc Uc");

        when(repository.findById(4L)).thenReturn(Optional.of(logisticaAEliminar));

        // Act
        Optional<Logistica> response = service.delete(logisticaAEliminar);

        // Assert
        assertTrue(response.isPresent());
        assertEquals("Xerjoff Erba Pura", response.get().getProducto());

        verify(repository, times(1)).findById(4L);
        verify(repository, times(1)).delete(logisticaAEliminar);
    }


    public void  chargeLogistica(){
        Logistica prod1 =new Logistica( 1L, 319, 200000, 1, 0.5, "Lataffa Khamra", "21-07-2025", "22-08-2025", "San pablo", "Maipu");
        Logistica prod2 =new Logistica( 2L, 320, 210000, 2, 1, "Invictus", "25-06-2025", "29-06-2025", "La reina", "Lo barnechea");
        Logistica prod3 =new Logistica( 3L, 321, 60000, 3, 1.5, "Halloween Man X", "24-01-2025", "29-01-2025", "La pintana", "Cerrillos");

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);
    }






}