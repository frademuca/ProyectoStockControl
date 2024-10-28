package com.es.stockcontrol.service;

import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.repository.ProductoRepository;

import java.util.Date;

// Lógica:
// id: El id se compone por: 3 primeras letras de categoria + 3 primeras letras de nombre + 3 primeras letras de proveedor
// categoria: longitud 50, not null
// nombre: longitud 50, not null
// precio_sin_iva: not null
// precio_con_iva: not null. Calcular el precio aplicando el IVA sobre el precio sin iva
// fecha_alta: fecha de hoy

public class ProductoService {
    private ProductoRepository repository;
    private static final float IVA = 0.21f; // 21% de IVA

    public ProductoService() {
        this.repository = new ProductoRepository();
    }

    // C
    public Producto createProducto(Producto producto) {

        // Condiciones:

        if (producto.getCategoria() == null || producto.getCategoria().trim().isEmpty() || producto.getCategoria().length() > 50) {
            System.out.println("Error: Categoría no válida.");
            return null;
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty() || producto.getNombre().length() > 50) {
            System.out.println("Error: Nombre no válido.");
            return null;
        }
        if (producto.getPrecioSinIva() <= 0) {
            System.out.println("Error: Precio sin IVA no válido.");
            return null;
        }

        // Si las pasa:

        // Generar ID del producto
        producto.setId(generarID(producto));

        // Calcular precio con IVA
        producto.setPrecioConIva(calcularPrecioConIva(producto.getPrecioSinIva()));

        // Asignar fecha de alta
        producto.setFechaAlta(new Date());

        // Crear producto en el repositorio
        return repository.create(producto);
    }

    // R
    public Producto read(String id) {
        return repository.read(id);
    }

    // U
    public Producto update(Producto producto) {
        if (producto.getId() == null || repository.read(producto.getId()) == null) {
            System.out.println("Error: El producto con el ID especificado no existe.");
            return null;
        }

        if (producto.getCategoria() == null || producto.getCategoria().trim().isEmpty() || producto.getCategoria().length() > 50) {
            System.out.println("Error: Categoría no válida.");
            return null;
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty() || producto.getNombre().length() > 50) {
            System.out.println("Error: Nombre no válido.");
            return null;
        }
        if (producto.getPrecioSinIva() <= 0) {
            System.out.println("Error: Precio sin IVA no válido.");
            return null;
        }

        // Calcular precio con IVA nuevamente en caso de cambios
        producto.setPrecioConIva(calcularPrecioConIva(producto.getPrecioSinIva()));

        return repository.update(producto);
    }

    // D
    public boolean delete(String id) {
        return repository.delete(id);
    }

    // Generar ID único de producto
    private String generarID(Producto producto) {
        String categoria = producto.getCategoria().substring(0, Math.min(producto.getCategoria().length(), 3)).toUpperCase();
        String nombre = producto.getNombre().substring(0, Math.min(producto.getNombre().length(), 3)).toUpperCase();
        String proveedor = producto.getProveedor().getNombre().substring(0, Math.min(producto.getProveedor().getNombre().length(), 3)).toUpperCase();
        return categoria + nombre + proveedor;
    }

    private float calcularPrecioConIva(float precioSinIva) {
        return precioSinIva * (1 + IVA);
    }
}
