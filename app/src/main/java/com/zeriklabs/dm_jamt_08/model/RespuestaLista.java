package com.zeriklabs.dm_jamt_08.model;

public class RespuestaLista {
    private int estado;
    private String mensaje;
    private DatosLista datos;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public DatosLista getDatos() {
        return datos;
    }

    public void setDatos(DatosLista datos) {
        this.datos = datos;
    }
}