package com.es.stockcontrol.model;

import java.util.Optional;

/**
 * Clase que almacena la respuesta que se da desde el Controller al Cliente (main)
 * @param <T> : T puede ser un objeto de tipo User, o de tipo Producto, o ...
 */
public class RespuestaHTTP<T> {

    private int codigo;
    private String mensaje;
    private T obj;

    public RespuestaHTTP(int codigo, String mensaje, T obj) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.obj = obj;
    }

    public RespuestaHTTP(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public RespuestaHTTP() {

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
