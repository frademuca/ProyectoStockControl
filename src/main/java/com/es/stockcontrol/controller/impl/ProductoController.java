package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProductoControllerAPI;
import com.es.stockcontrol.model.Producto;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.service.ProductoService;
import com.es.stockcontrol.service.ProveedorService;

import java.util.List;

public class ProductoController implements ProductoControllerAPI {
    private ProductoService productoService;
    private ProveedorService proveedorService;

    public ProductoController(ProductoService productoService, ProveedorService proveedorService) {
        this.productoService = productoService;
        this.proveedorService = proveedorService;
    }

    @Override
    public RespuestaHTTP<Producto> altaProducto(String idProducto, String nombreProducto, String precioSinIva, String descripcionProducto, String nombreProveedor, String direccionProveedor) {
        try {
            float precioSinIvaFloat = Float.parseFloat(precioSinIva);

            // Crear proveedor completo
            Proveedor proveedor = new Proveedor(nombreProveedor, direccionProveedor);
            proveedor = proveedorService.create(proveedor); // Pasar el proveedor completo

            if (proveedor == null) {
                return new RespuestaHTTP<>(400, "Error: No se pudo crear el proveedor");
            }

            Producto nuevoProducto = new Producto(idProducto, nombreProducto, precioSinIvaFloat, descripcionProducto, proveedor);
            Producto productoCreado = productoService.createProducto(nuevoProducto);

            if (productoCreado != null) {
                return new RespuestaHTTP<>(201, "Producto creado con éxito");
            } else {
                return new RespuestaHTTP<>(400, "Error al crear el producto");
            }
        } catch (NumberFormatException e) {
            return new RespuestaHTTP<>(400, "Error: Precio sin IVA inválido");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespuestaHTTP<>(500, "Error interno al crear producto");
        }
    }

    @Override
    public RespuestaHTTP<Producto> bajaProducto(String id) {
        boolean isDeleted = productoService.delete(id);
        if (isDeleted) {
            return new RespuestaHTTP<>(200, "Producto eliminado con éxito");
        } else {
            return new RespuestaHTTP<>(404, "Error: Producto no encontrado");
        }
    }

    @Override
    public RespuestaHTTP<Producto> modificarNombreProducto(String id, String nuevoNombre) {

        Producto producto = productoService.read(id);

        if (producto != null) {
            return new RespuestaHTTP<>(200, "Nombre del producto modificado con éxito");
        } else {
            return new RespuestaHTTP<>(404, "Error: Producto no encontrado");
        }

    }

    @Override
    public RespuestaHTTP<Producto> modificarStockProducto(String id, String nuevoStock) {
        try {
            int nuevoStockInt = Integer.parseInt(nuevoStock);
            Producto producto = productoService.read(id);
            if (producto != null) {
                return new RespuestaHTTP<>(200, "Stock del producto modificado con éxito");
            } else {
                return new RespuestaHTTP<>(404, "Error: Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            return new RespuestaHTTP<>(400, "Error: Stock inválido");
        }
    }

    @Override
    public RespuestaHTTP<Producto> getProducto(String id) {
        Producto producto = productoService.read(id);
        if (producto != null) {
            return new RespuestaHTTP<>(200, "Producto encontrado");
        } else {
            return new RespuestaHTTP<>(404, "Error: Producto no encontrado");
        }
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosConStock() {
        List<Producto> productos = productoService.getProductosConStock();
        if (!productos.isEmpty()) {
            return new RespuestaHTTP<>(200, "Productos con stock disponibles");
        } else {
            return new RespuestaHTTP<>(404, "No hay productos con stock");
        }
    }

    @Override
    public RespuestaHTTP<List<Producto>> getProductosSinStock() {
        List<Producto> productos = productoService.getProductosSinStock();
        if (!productos.isEmpty()) {
            return new RespuestaHTTP<>(200, "Productos sin stock encontrados");
        } else {
            return new RespuestaHTTP<>(404, "No hay productos sin stock");
        }
    }
}
