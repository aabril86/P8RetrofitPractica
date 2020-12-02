package com.example.p8retrofitpractica;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.p8retrofitpractica.databinding.FragmentPokemonsBinding;
import com.example.p8retrofitpractica.databinding.ViewholderPokemonBinding;

import java.util.List;

public class PokemonsFragment extends Fragment {

    private FragmentPokemonsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return (binding = FragmentPokemonsBinding.inflate(inflater, container, false)).getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PokeViewModel pokeViewModel = new ViewModelProvider(this).get(PokeViewModel.class);

        pokeViewModel.obtenerPokemon();

        PokemonsAdapter pokemonsAdapter = new PokemonsAdapter();

        binding.recyclerviewContenidos.setAdapter(pokemonsAdapter);


        pokeViewModel.respuestaMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Poke.Pokemons>() {
            @Override
            public void onChanged(Poke.Pokemons respuesta) {
                pokemonsAdapter.establecerPokeomon(respuesta.documents);
            }
        });
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        ViewholderPokemonBinding binding;

        public PokemonViewHolder(@NonNull ViewholderPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class PokemonsAdapter extends RecyclerView.Adapter<PokemonViewHolder>{

        List<Poke.Pokemon> pokemons;

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PokemonViewHolder(ViewholderPokemonBinding.inflate(getLayoutInflater(), parent, false));        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
            Poke.Pokemon pokemon = pokemons.get(position);

            holder.binding.nombre.setText(pokemon.fields.nombre.stringValue);
            Glide.with(PokemonsFragment.this).load(pokemon.fields.imagen.stringValue).into(holder.binding.imagen);
        }


        @Override
        public int getItemCount() {
            return pokemons == null ? 0: pokemons.size();
        }

        void establecerPokeomon(List<Poke.Pokemon> pokemons){
            this.pokemons = pokemons;
            notifyDataSetChanged();
        }
    }
}