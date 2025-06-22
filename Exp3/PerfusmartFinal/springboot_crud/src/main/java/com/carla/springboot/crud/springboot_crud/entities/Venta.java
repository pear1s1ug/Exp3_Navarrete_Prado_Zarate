package com.carla.springboot.crud.springboot_crud.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_venta")
    private Long idVenta;
    
    private String producto;
    private int precio;
    private String rut;



    public Venta() {
    }



    public Venta(Long idVenta, String producto, int precio, String rut) {
        this.idVenta = idVenta;
        this.producto = producto;
        this.precio = precio;
        this.rut = rut;
    }



    public Long getidVenta() {
        return idVenta;
    }



    public void setidVenta(Long idVenta) {
        this.idVenta = idVenta;
    }



    public String getProducto() {
        return producto;
    }



    public void setProducto(String producto) {
        this.producto = producto;
    }



    public int getPrecio() {
        return precio;
    }



    public void setPrecio(int precio) {
        this.precio = precio;
    }



    public String getRut() {
        return rut;
    }



    public void setRut(String rut) {
        this.rut = rut;
    }

    
    

}