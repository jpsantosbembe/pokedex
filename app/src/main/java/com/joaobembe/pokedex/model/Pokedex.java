package com.joaobembe.pokedex.model;

import java.io.Serializable;
import java.util.List;

public class Pokedex implements Serializable {
    private List<Pokemon> pokemons;

    public Pokedex(List<Pokemon> pokemons) {

        this.pokemons = pokemons;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
