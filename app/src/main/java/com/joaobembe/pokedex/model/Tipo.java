package com.joaobembe.pokedex.model;

import java.io.Serializable;

public class Tipo implements Serializable {
    private String nomeTipo;

    public Tipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
