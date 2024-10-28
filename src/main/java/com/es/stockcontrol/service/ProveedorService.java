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
    public Proveedor create(Proveedor proveedor) throws IllegalArgumentException{ // asegurando los parametros
        // Lógica parámetros
        // Validar que el nombre y dirección no sean nulos o vacíos
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            System.out.println("Error: Nombre no válido.");
            return null;
        }
        if (proveedor.getDireccion() == null || proveedor.getDireccion().trim().isEmpty()) {
            System.out.println("Error: Dirección no válida.");
            return null;
        }
        if (proveedor.getNombre().length() > 50) {
            System.out.println("Error: Tamaño excedido.");
            return null;
        }

        // Verificar si ya existe un proveedor con el mismo nombre
        if (repository.read(proveedor.getNombre()) != null) {
            System.out.println("Error: Ya existe un proveedor con el nombre especificado.");
            return null;
        }
        // Crear y guardar el nuevo proveedor
        Proveedor newProveedor = new Proveedor();
        newProveedor.setNombre(proveedor.getNombre());
        newProveedor.setDireccion(proveedor.getDireccion());
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
    public Proveedor update(Proveedor proveedor) {
        // Comprobar que no sea null ni el que se pasa ni el de la BD
        if (proveedor.getId() == 0 || repository.read(proveedor.getId()) == null) {
            System.out.println("Error: El proveedor con el ID especificado no existe.");
            return null;
        }

        // Validación de campos obligatorios
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty() || proveedor.getNombre().length() > 50) {
            System.out.println("Error: Nombre no válido.");
            return null;
        }
        if (proveedor.getDireccion() == null || proveedor.getDireccion().trim().isEmpty()) {
            System.out.println("Error: Dirección no válida.");
            return null;
        }

        return repository.update(proveedor);
    }

    // D
    public boolean delete(long id){
        return repository.delete(id);
    }

}
