package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.repository.ProveedorRespository;

import java.util.List;

// logica:
// nombre: único, longitud 50, not null
// direccion: not null
public class ProveedorService {
    private ProveedorRespository repository;

    public ProveedorService() {
        repository = new ProveedorRespository();
    }

    // C
    public boolean create(String nombre, String direccion, List<Producto> productos){
        // Nombre unico, longitud 50, not null, no vacio; direccion not null no vacio
        if (repository.read(nombre) == null || nombre.length() > 50 || nombre == null || nombre.isEmpty() ||
            direccion == null || direccion.isEmpty()
        ){
            return false;
        }
        // se crea y devuelve true
        return repository.create(new Proveedor(nombre, direccion, productos));
    }

    // R
    public Proveedor read(long id){
        if (id == 0) {
            return null;
        }
        return repository.read(id);
    }

    // U
    public

}
