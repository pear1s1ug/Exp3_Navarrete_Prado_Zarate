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

import com.carla.springboot.crud.springboot_crud.entities.Cliente;
import com.carla.springboot.crud.springboot_crud.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "clientes", description = "Usuarios sitio web ecommerce")
@RestController
@RequestMapping("api/clientes")

public class ClienteRestController {
   
    @Autowired
    private ClienteService service;

    @Operation(summary = "Obtener lista de clientes", description = "Devuelve todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cliente.class)))
    @GetMapping
    public List<Cliente> List(){
        return (List<Cliente>)service.findByAll();
    }

    @Operation(summary = "Obtener cliente por ID", description = "Obtiene el detalle de un cliente específico")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Cliente encontrado",
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))),
        @ApiResponse(responseCode = "404",description = "Cliente no encontrado")
        })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional <Cliente> clienteOptional = service.findById(id);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear nuevo cliente",description = "crea un cliente con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Cliente creado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @PostMapping
    public ResponseEntity<Cliente> crear (@RequestBody Cliente unCliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unCliente));
    }

    @Operation(summary = "Modificar cliente por ID", description = "Actualiza un cliente existente con los nuevos datos proporcionados.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cliente modificado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Cliente unCliente){
        Optional<Cliente> clienteOptional = service.findById(id);
        if (clienteOptional.isPresent()){
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNombre(unCliente.getNombre());
            clienteExistente.setApellido(unCliente.getApellido());
            clienteExistente.setContrasenna(unCliente.getContrasenna());
            clienteExistente.setCorreo(unCliente.getCorreo());
            clienteExistente.setNumero(unCliente.getNumero());
            clienteExistente.setDireccion(unCliente.getDireccion());
            clienteExistente.setMetodoPago(unCliente.getMetodoPago());

            Cliente clienteModificado = service.save(clienteExistente);
            return ResponseEntity.ok(clienteModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar cliente por ID", description = "Elimina un cliente específico si existe en la base de datos")
    @ApiResponses(value = {
    @ApiResponse(
            responseCode = "200",
            description = "Cliente eliminado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))
            ),
    @ApiResponse(
            responseCode = "404",
            description = "Cliente no encontrado",
            content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Cliente unCliente = new Cliente();
        unCliente.setId(id);
        Optional<Cliente> clienteOptional = service.delete(unCliente);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}
