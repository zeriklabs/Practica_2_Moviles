package com.zeriklabs.dm_jamt_08.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Embedded {
    @SerializedName("clienteDtoList")
    private List<Empleado> clienteDtoList;

    public List<Empleado> getClienteDtoList() {
        return clienteDtoList;
    }

    public void setClienteDtoList(List<Empleado> clienteDtoList) {
        this.clienteDtoList = clienteDtoList;
    }
}