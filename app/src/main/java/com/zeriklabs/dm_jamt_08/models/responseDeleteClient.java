package com.zeriklabs.dm_jamt_08.models;

public class responseDeleteClient {
    private int estado;
    private String nombre;

    public responseDeleteClient(int estado, String nombre) {
        this.estado = estado;
        this.nombre = nombre;
    }

    public responseDeleteClient() {
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
