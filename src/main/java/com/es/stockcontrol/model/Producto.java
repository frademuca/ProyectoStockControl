package com.es.stockcontrol.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "categoria", length = 10, nullable = false)
    private String categoria;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name ="descripcion")
    private String descripcion;

    @Column(name="precio_sin_iva", nullable = false)
    private float precioSinIva;

    @Column(name="precio_con_iva", nullable = false)
    private float precioConIva;

    @Column(name="fecha_alta", nullable = false)
    private Date fechaAlta;

    @Column(name="stock")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "proveedor")
    private Proveedor proveedor;


    public Producto() {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(float precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public float getPrecioConIva() {
        return precioConIva;
    }

    public void setPrecioConIva(float precioConIva) {
        this.precioConIva = precioConIva;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
