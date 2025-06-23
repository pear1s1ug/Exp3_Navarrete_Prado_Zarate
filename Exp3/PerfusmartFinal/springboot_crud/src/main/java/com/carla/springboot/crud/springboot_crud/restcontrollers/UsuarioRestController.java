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

import com.carla.springboot.crud.springboot_crud.entities.Producto;
import com.carla.springboot.crud.springboot_crud.entities.Usuario;
import com.carla.springboot.crud.springboot_crud.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@Tag(name = "Usuarios", description = "Operaciones relacionadas con Usuarios")
@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioService service;



    @Operation(summary = "Obtener lista de Usuarios",description = "Devuelve todos los Usuarios Registrados")
    @ApiResponse(responseCode = "200",description = "Lista de Usuarios retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema =@Schema(implementation = Producto.class)))
    @GetMapping
    public List<Usuario> List(){
        return service.findByAll();
    }


    @Operation(summary = "Obtener usuario por RUT",description = "Obtiene el detalle de un Rut específico")
    @ApiResponses(value={
        @ApiResponse(responseCode = "200",description = "Usuario encontrado",
                        content = @Content(mediaType = "application/json", schema =@Schema(implementation = Producto.class))),
        @ApiResponse(responseCode = "404",description = "Usuario no encontrado")
        })
    @GetMapping("/{rut}")
    public ResponseEntity<?> verDetalle(@PathVariable String rut){
        Optional <Usuario> usuarioOptional=service.findById(rut);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();

    }
    @Operation(summary = "Ingresar nuevo Usuario",description = "ingresa un usuario con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Usuario ingresado correctamente",
                content = @Content(mediaType = "application/json", schema =@Schema(implementation = Producto.class)))
    @PostMapping
    public ResponseEntity<Usuario> crear (@RequestBody Usuario unUsuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unUsuario));
    }

    @Operation(
    summary = "Modificar Usuario por ID",description = "Actualiza un usuario existente con los nuevos datos proporcionados.")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Usuario modificado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content
        )
    })
    @PutMapping("/{rut}")
    public ResponseEntity<?> modificar(@PathVariable String rut, @RequestBody Usuario unUsuario){
        Optional<Usuario> usuarioOptional=service.findById(rut);
        if (usuarioOptional.isPresent()){
            Usuario usuarioexistente=usuarioOptional.get();
            usuarioexistente.setNombre(unUsuario.getNombre());
            usuarioexistente.setApellido(unUsuario.getApellido());
            usuarioexistente.setcontrasenna(unUsuario.getcontrasenna());
            usuarioexistente.setRol(unUsuario.getRol());
            usuarioexistente.setCorreo(unUsuario.getCorreo());
            usuarioexistente.setNumero(unUsuario.getNumero());
            Usuario usuariomodificado= service.save(usuarioexistente);
            return ResponseEntity.ok(usuariomodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar usuario por ID", description = "Elimina un usuario específico si existe en la base de datos")
    @ApiResponses(value = {
    @ApiResponse(
            responseCode = "200",
            description = "Usuario eliminado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))
            ),
    @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content
            )
    })
    @DeleteMapping("/{rut}")
    public ResponseEntity<?> eliminar(@PathVariable String rut){
        Usuario unUsuario=new Usuario();
        unUsuario.setRut(rut);
        Optional<Usuario> usuarioOptional=service.delete(unUsuario);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}