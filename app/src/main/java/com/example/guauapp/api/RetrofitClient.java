package com.example.guauapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;

    private static final String BASE_URL="https://dog.ceo/";

    public static Retrofit getRetrofitInstance(){

        if (retrofit==null){
            retrofit=new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //GSON Converter FORMATEA LA RESPUESTA DEL JSON
                    .addConverterFactory(GsonConverterFactory.create()) //DEVUELVE LA INSTANCIA EN OBJETO
                    .build();
        }

        return retrofit;
    }

}
