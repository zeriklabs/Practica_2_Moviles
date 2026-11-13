package com.zeriklabs.dm_jamt_08.models;

import java.util.List;

public class client {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String dirección;
    private boolean registrado;
    private List<link> links;

    public client(String nombre, String correo, String telefono, String dirección, boolean registrado, List<link> links) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.dirección = dirección;
        this.registrado = registrado;
        this.links = links;
    }

    public client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<link> getLinks() {
        return links;
    }

    public void setLinks(List<link> links) {
        this.links = links;
    }
}
