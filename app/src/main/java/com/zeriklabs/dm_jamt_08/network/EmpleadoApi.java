package com.zeriklabs.dm_jamt_08.network;

import com.zeriklabs.dm_jamt_08.model.Empleado;
import com.zeriklabs.dm_jamt_08.model.RespuestaEntidad;
import com.zeriklabs.dm_jamt_08.model.RespuestaLista;
import com.zeriklabs.dm_jamt_08.model.RespuestaVacia;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmpleadoApi {

    @GET("/api/v1/clientes")
    Call<RespuestaLista> obtenerTodos();

    @GET("/api/v1/clientes/{id}")
    Call<RespuestaEntidad> obtenerPorId(@Path("id") Long id);

    @POST("/api/v1/clientes")
    Call<RespuestaEntidad> registrar(@Body Empleado empleado);

    @PUT("/api/v1/clientes/{id}")
    Call<RespuestaEntidad> actualizar(@Path("id") Long id, @Body Empleado empleado);

    @DELETE("/api/v1/clientes/{id}")
    Call<RespuestaVacia> eliminar(@Path("id") Long id);
}