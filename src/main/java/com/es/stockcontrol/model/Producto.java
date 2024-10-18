package com.es.stockcontrol.model;

import java.util.Date;

public class Producto {

    private String id;
    private String categoria;
    private String nombre;
    private String descripcion;
    private float precio_sin_iva;
    private float precio_con_iva;
    private Date fecha_alta;
    private int stock;


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

    public float getPrecio_sin_iva() {
        return precio_sin_iva;
    }

    public void setPrecio_sin_iva(float precio_sin_iva) {
        this.precio_sin_iva = precio_sin_iva;
    }

    public float getPrecio_con_iva() {
        return precio_con_iva;
    }

    public void setPrecio_con_iva(float precio_con_iva) {
        this.precio_con_iva = precio_con_iva;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
