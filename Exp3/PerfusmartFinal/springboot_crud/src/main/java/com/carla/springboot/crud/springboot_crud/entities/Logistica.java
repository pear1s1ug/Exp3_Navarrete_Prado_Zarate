package com.carla.springboot.crud.springboot_crud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "logistica")
public class Logistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_envio")
    private Long idEnvio;
    @Column(name="id_producto")
    private int idProducto;
    private int precio;
    private int cantidad;
    private double peso;
    private String producto;
    @Column(name="fecha_envio")
    private String fechaEnvio;
    @Column(name="fecha_entrega")
    private String fechaEntrega;
    private String origen;
    private String destino;
    
    
    public Logistica() {
    }



    public Logistica(Long idEnvio, int idProducto, int precio, int cantidad, double peso, String producto,
            String fechaEnvio, String fechaEntrega, String origen, String destino) {
        this.idEnvio = idEnvio;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.peso = peso;
        this.producto = producto;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
        this.origen = origen;
        this.destino = destino;
    }



    public Long getIdEnvio() {
        return idEnvio;
    }



    public void setIdEnvio(Long idEnvio) {
        this.idEnvio = idEnvio;
    }



    public int getIdProducto() {
        return idProducto;
    }



    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }



    public int getPrecio() {
        return precio;
    }



    public void setPrecio(int precio) {
        this.precio = precio;
    }



    public int getCantidad() {
        return cantidad;
    }



    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }



    public double getPeso() {
        return peso;
    }



    public void setPeso(double peso) {
        this.peso = peso;
    }



    public String getProducto() {
        return producto;
    }



    public void setProducto(String producto) {
        this.producto = producto;
    }



    public String getFechaEnvio() {
        return fechaEnvio;
    }



    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }



    public String getFechaEntrega() {
        return fechaEntrega;
    }



    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }



    public String getOrigen() {
        return origen;
    }



    public void setOrigen(String origen) {
        this.origen = origen;
    }



    public String getDestino() {
        return destino;
    }



    public void setDestino(String destino) {
        this.destino = destino;
    }

    
    
}