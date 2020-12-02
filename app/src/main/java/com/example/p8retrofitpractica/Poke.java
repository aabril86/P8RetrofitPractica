package com.example.p8retrofitpractica;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Poke {

    class Pokemons {
        List<Pokemon> documents;

    }

    class Pokemon {
        String name;
        Fields fields;
    }

    class Fields {
        StringValue imagen;
        StringValue nombre;
        StringValue tipo;
        IntValue nivel;
    }

    class StringValue{
        String stringValue;
    }

    class IntValue{
        String intValue;
    }

    public static Api api = new Retrofit.Builder()
            .baseUrl("https://firestore.googleapis.com/v1/projects/pokeapi-78228/databases/(default)/documents/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);

    public interface Api {
        @GET("pokemon")
        Call<Pokemons> obtenerPokemons();
    }

}
