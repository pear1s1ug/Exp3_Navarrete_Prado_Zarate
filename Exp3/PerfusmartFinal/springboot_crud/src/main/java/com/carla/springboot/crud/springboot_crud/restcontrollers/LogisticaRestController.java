package com.carla.springboot.crud.springboot_crud.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import com.carla.springboot.crud.springboot_crud.entities.Logistica;

import com.carla.springboot.crud.springboot_crud.services.LogisticaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Logistica", description = "Proceso de logistica por cada Logistica")
@RestController
@RequestMapping("api/logistica")

public class LogisticaRestController {

    @Autowired
    private LogisticaService service;

    @Operation(summary = "Obtener lista de Logisticas",description = "Devuelve todos los datos disponibles")
    @ApiResponse(responseCode = "200",description = "Lista de Datos retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema =@Schema(implementation = Logistica.class)))
    @GetMapping
    public List<Logistica> List(){
        return (List<Logistica>)service.findByAll();
    }

    @Operation(summary = "Obtener Logistica por ID",description = "Obtiene el detalle de un Logistica específico")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Logistica encontrado",
                        content = @Content(mediaType = "application/json", schema =@Schema(implementation = Logistica.class))),
        @ApiResponse(responseCode = "404",description = "Logistica no encontrado")
        })

    @GetMapping("/{idEnvio}")
    public ResponseEntity<?> verDetalle(@PathVariable Long idEnvio){
        Optional <Logistica> logisticaOptional=service.findById(idEnvio);
        if (logisticaOptional.isPresent()){
            return ResponseEntity.ok(logisticaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Crear nuevo Logistica",description = "crea un Logistica con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Logistica creado correctamente",
                content = @Content(mediaType = "application/json", schema =@Schema(implementation = Logistica.class)))
    @PostMapping
    public ResponseEntity<Logistica> crear (@RequestBody Logistica laLogistica){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(laLogistica));
    }

    @Operation(
    summary = "Modificar Logistica por ID",description = "Actualiza un Logistica existente con los nuevos datos proporcionados.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Logistica modificado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Logistica.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Logistica no encontrado",
            content = @Content
        )
    })

    @PutMapping("/{idEnvio}")
    public ResponseEntity<?> modificar(@PathVariable Long idEnvio, @RequestBody Logistica laLogistica){
        Optional<Logistica> logisticaOptional=service.findById(idEnvio);
        if (logisticaOptional.isPresent()){
            Logistica logisticaexistente=logisticaOptional.get();
            logisticaexistente.setIdProducto(laLogistica.getIdProducto());;
            logisticaexistente.setPrecio(laLogistica.getPrecio());
            logisticaexistente.setCantidad(laLogistica.getCantidad());
            logisticaexistente.setPeso(laLogistica.getPeso());
            logisticaexistente.setProducto(laLogistica.getProducto());
            logisticaexistente.setFechaEnvio(laLogistica.getFechaEnvio());
            logisticaexistente.setFechaEntrega(laLogistica.getFechaEntrega());
            logisticaexistente.setOrigen(laLogistica.getOrigen());
            logisticaexistente.setDestino(laLogistica.getDestino());
            Logistica logisticamodificado= service.save(logisticaexistente);
            return ResponseEntity.ok(logisticamodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar datos por ID", description = "Elimina un dato específico si existe en la base de datos")
    @ApiResponses(value = {
    @ApiResponse(
            responseCode = "200",
            description = "Logistica eliminado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Logistica.class))
            ),
    @ApiResponse(
            responseCode = "404",
            description = "Logistica no encontrado",
            content = @Content
            )
    })

    @DeleteMapping("/{idEnvio}")
    public ResponseEntity<?> eliminar(@PathVariable Long idEnvio){
        Logistica laLogistica=new Logistica();
        laLogistica.setIdEnvio(idEnvio);
        Optional<Logistica> logisticaOptional=service.delete(laLogistica);
        if(logisticaOptional.isPresent()){
            return ResponseEntity.ok(logisticaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}