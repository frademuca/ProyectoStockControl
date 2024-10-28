package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProveedorRespository;

// logica:
// nombre: único, longitud 50, not null
// direccion: not null
public class ProveedorService {
    private ProveedorRespository repository;

    public ProveedorService() {
        repository = new ProveedorRespository();
    }

    // C
    public Proveedor create(String nombre, String direccion) throws IllegalArgumentException{ // asegurando los parametros
        // Lógica parámetros
        // Validar que el nombre y dirección no sean nulos o vacíos
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío o ser nulo.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía o ser nula.");
        }
        if (nombre.length() > 50) {
            throw new IllegalArgumentException("El nombre no puede superar los 50 caracteres.");
        }

        // Verificar si ya existe un proveedor con el mismo nombre
        if (repository.read(nombre) != null) {
            throw new IllegalArgumentException("Ya existe un proveedor con el nombre especificado.");
        }
        // Crear y guardar el nuevo proveedor
        Proveedor newProveedor = new Proveedor();
        newProveedor.setNombre(nombre);
        newProveedor.setDireccion(direccion);
        return repository.create(newProveedor);
    }

    // R x id
    public Proveedor read(long id){
        if (id == 0) {
            return null;
        }
        return repository.read(id);
    }

    // R x nombre
    public Proveedor read(String nombre){
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return repository.read(nombre);
    }

    // U
//    public

    // D

}
