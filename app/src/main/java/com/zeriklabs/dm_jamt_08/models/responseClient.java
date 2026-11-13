package com.zeriklabs.dm_jamt_08.models;

public class responseClient {
    private int estado;
    private String mensaje;
    private client datos;

    public responseClient(int estado, String mensaje, client datos) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public responseClient() {
    }

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

    public client getDatos() {
        return datos;
    }

    public void setDatos(client datos) {
        this.datos = datos;
    }
}
