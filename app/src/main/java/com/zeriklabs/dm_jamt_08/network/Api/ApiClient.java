package com.zeriklabs.dm_jamt_08.network.Api;

import com.zeriklabs.dm_jamt_08.models.ResponseClients;
import com.zeriklabs.dm_jamt_08.models.clientRequest;
import com.zeriklabs.dm_jamt_08.models.responseClient;
import com.zeriklabs.dm_jamt_08.models.responseDeleteClient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiClient {

    @GET("api/v1/clients")
    Call<ResponseClients> obtainClients();

    @GET("api/v1/clients/{id}")
    Call<responseClient> obtainClientId(@Path("id") Long id);

    @POST("api/v1/clients")
    Call<responseClient> createClient(@Body clientRequest request);

    @PUT("api/v1/clients/{id}")
    Call<responseClient> updateClient(@Path("id") Long id, @Body clientRequest request);

    @DELETE("api/v1/clients/{id}")
    Call<responseDeleteClient> deleteClient(@Path("id") Long id);
}
