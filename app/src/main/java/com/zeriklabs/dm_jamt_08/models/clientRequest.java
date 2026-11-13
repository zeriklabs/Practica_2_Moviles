package com.zeriklabs.dm_jamt_08.models;

public class clientRequest {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String dirección;
    private boolean registrado;

    public clientRequest(String nombre, String correo, String telefono, String dirección, boolean registrado) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.dirección = dirección;
        this.registrado = registrado;
    }

    public clientRequest() {
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public boolean isRegistrado() {
        return registrado;
    }

    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }
}