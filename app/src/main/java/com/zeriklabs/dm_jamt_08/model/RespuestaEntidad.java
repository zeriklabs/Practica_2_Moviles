package com.zeriklabs.dm_jamt_08.model;

public class RespuestaEntidad {
    private int estado;
    private String mensaje;
    private Empleado datos;

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

    public Empleado getDatos() {
        return datos;
    }

    public void setDatos(Empleado datos) {
        this.datos = datos;
    }
}