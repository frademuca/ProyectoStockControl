package com.es.stockcontrol.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @OneToMany
    @JoinColumn(name = "productos")
    private List<Producto> productos;


    public Proveedor() {}

    // sin el id puesto que se genera auto
    public Proveedor(String nombre, String direccion, List<Producto> productos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.productos = productos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}