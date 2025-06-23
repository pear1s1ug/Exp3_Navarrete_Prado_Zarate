package com.carla.springboot.crud.springboot_crud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="usuarios")

public class Usuario {


    @Id
    private Long rut;
    private String nombre;
    private String apellido;
    private String contrasenna;
    private String rol;
    private String correo;
    private String numero;



     
    

    public Usuario() {
    }

    




    
    public Usuario(Long rut, String nombre, String apellido, String contrasenna, String rol, String correo,
            String numero) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenna = contrasenna;
        this.rol = rol;
        this.correo = correo;
        this.numero = numero;
    }



    public Long getRut() {
        return rut;
    }



    public void setRut(Long rut) {
        this.rut = rut;
    }



    public String getNombre() {
        return nombre;
    }




    public void setNombre(String nombre) {
        this.nombre = nombre;
    }




    public String getApellido() {
        return apellido;
    }




    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public String getcontrasenna() {
        return contrasenna;
    }


    public void setcontrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }




    public String getRol() {
        return rol;
    }




    public void setRol(String rol) {
        this.rol = rol;
    }



    public String getCorreo() {
        return correo;
    }



    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getNumero() {
        return numero;
    }



    public void setNumero(String numero) {
        this.numero = numero;
    }



}
