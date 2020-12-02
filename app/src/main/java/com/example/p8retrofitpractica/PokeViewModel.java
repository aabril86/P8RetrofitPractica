package com.example.p8retrofitpractica;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeViewModel extends AndroidViewModel {

    MutableLiveData<Poke.Pokemons> respuestaMutableLiveData = new MutableLiveData<>();

    public PokeViewModel(@NonNull Application application) {
        super(application);
    }

    void obtenerPokemon() {
        Poke.api.obtenerPokemons().enqueue(new Callback<Poke.Pokemons>() {
            @Override
            public void onResponse(Call<Poke.Pokemons> call, Response<Poke.Pokemons> response) {
                respuestaMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<Poke.Pokemons> call, Throwable t) {

            }
        });
    }
}
