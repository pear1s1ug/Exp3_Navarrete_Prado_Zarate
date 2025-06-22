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

import com.carla.springboot.crud.springboot_crud.entities.Producto;
import com.carla.springboot.crud.springboot_crud.entities.Venta;
import com.carla.springboot.crud.springboot_crud.services.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;
@Tag(name = "Ventas", description = "Operaciones relacionadas con ventas")
@RestController
@RequestMapping("api/ventas")
public class VentaRestController {
    @Autowired
    private VentaService service;



    @Operation(summary = "Obtener lista de Ventas",description = "Devuelve todas las Ventas Registrados")
    @ApiResponse(responseCode = "200",description = "Lista de Ventas retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema =@Schema(implementation = Producto.class)))
    @GetMapping
    public List<Venta> List(){
        return service.findByAll();
    }

    @Operation(summary = "Obtener Venta por id",description = "Obtiene el detalle de una venta específica por su id")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Venta encontrada",
                        content = @Content(mediaType = "application/json", schema =@Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404",description = "Venta no encontrada")
        })
    @GetMapping("/{idVenta}")
    public ResponseEntity<?> verDetalle(@PathVariable Long idVenta){
        Optional <Venta> ventaOptional=service.findById(idVenta);
        if (ventaOptional.isPresent()){
            return ResponseEntity.ok(ventaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Ingresar nueva Venta",description = "ingresa una Venta con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Venta ingresada correctamente",
                content = @Content(mediaType = "application/json", schema =@Schema(implementation = Producto.class)))
    @PostMapping
    public ResponseEntity<Venta> crear (@RequestBody Venta unVenta){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unVenta));
    }
    @Operation(
    summary = "Modificar Venta por ID",description = "Actualiza una Venta existente con los nuevos datos proporcionados.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Venta modificada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Venta no encontrada",
            content = @Content
        )
    })
    @PutMapping("/{idVenta}")
    public ResponseEntity<?> modificar(@PathVariable Long idVenta, @RequestBody Venta unVenta){
        Optional<Venta> ventaOptional=service.findById(idVenta);
        if (ventaOptional.isPresent()){
            Venta ventaexistente=ventaOptional.get();
            ventaexistente.setProducto(unVenta.getProducto());
            ventaexistente.setPrecio(unVenta.getPrecio());
            ventaexistente.setRut(unVenta.getRut());
            Venta ventamodificado= service.save(ventaexistente);
            return ResponseEntity.ok(ventamodificado);
        }
        return ResponseEntity.notFound().build();
    }

     @Operation(
        summary = "Eliminar Venta por ID", description = "Elimina una Venta específico si existe en la base de datos")
    @ApiResponses(value = {
    @ApiResponse(
            responseCode = "200",
            description = "Venta eliminada correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
            ),
    @ApiResponse(
            responseCode = "404",
            description = "Venta no encontrada",
            content = @Content
            )
    })
    @DeleteMapping("/{idVenta}")
    public ResponseEntity<?> eliminar(@PathVariable Long idVenta){
        Venta unVenta=new Venta();
        unVenta.setidVenta(idVenta);
        Optional<Venta> ventaOptional=service.delete(unVenta);
        if(ventaOptional.isPresent()){
            return ResponseEntity.ok(ventaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }



}