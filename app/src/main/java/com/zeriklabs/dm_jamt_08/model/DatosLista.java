package com.zeriklabs.dm_jamt_08.model;

import com.google.gson.annotations.SerializedName;

public class DatosLista {
    @SerializedName("content")
    private java.util.List<Empleado> content;

    public java.util.List<Empleado> getContent() {
        return content;
    }

    public void setContent(java.util.List<Empleado> content) {
        this.content = content;
    }
}