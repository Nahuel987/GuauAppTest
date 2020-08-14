package com.example.guauapp.api;

import com.example.guauapp.modelo.ListaDeRazasRespuesta;
import com.example.guauapp.modelo.ListaImagenesRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiGuau {

    @GET("api/breeds/list/")
    Call<ListaDeRazasRespuesta> getListaRazas();

    @GET("api/breed/{breed}/images/")
    Call<ListaImagenesRespuesta> getListaImagenesURL(@Path("breed") String breed);

}
