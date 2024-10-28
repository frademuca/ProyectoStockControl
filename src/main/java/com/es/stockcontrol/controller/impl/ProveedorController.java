package com.es.stockcontrol.controller.impl;

import com.es.stockcontrol.controller.api.ProveedorControllerAPI;
import com.es.stockcontrol.model.Proveedor;
import com.es.stockcontrol.model.RespuestaHTTP;
import com.es.stockcontrol.service.ProveedorService;

import java.util.List;

public class ProveedorController implements ProveedorControllerAPI {
    @Override
    public RespuestaHTTP<List<Proveedor>> getProveedoresProducto(String id) {

        try {
            List<ProveedorService> proveedor = ProveedorService.read(id);

            return proveedor != null ?
                    new RespuestaHTTP<>(200, "ID Correcto") :
                    new RespuestaHTTP<>(400, "ID Erróneo");

            return (RespuestaHTTP<List<Proveedor>>) proveedor;

        } catch (Exception e) {
            return new RespuestaHTTP<>(500, "Error del servidor");
        }
    }

    @Override
    public RespuestaHTTP<List<Proveedor>> getTodosProveedores() {

        //return (RespuestaHTTP<List<Proveedor>>);
    }
}
