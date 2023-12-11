package com.joaobembe.pokedex.model;

import java.io.Serializable;

public class Habilidade implements Serializable {
    private String nomeHabilidade;

    public Habilidade(String nomeHabilidade) {
        this.nomeHabilidade = nomeHabilidade;
    }

    public String getNomeHabilidade() {
        return nomeHabilidade;
    }

    public void setNomeHabilidade(String nomeHabilidade) {
        this.nomeHabilidade = nomeHabilidade;
    }
}
