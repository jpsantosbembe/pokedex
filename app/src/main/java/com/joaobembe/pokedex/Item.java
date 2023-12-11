package com.joaobembe.pokedex;

public class Item {
    String nome;
    String cor;
    String idNacional;

    public Item(String nome, String cor, String idNacional) {
        this.nome = nome;
        this.cor = cor;
        this.idNacional = idNacional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setImage(String image) {
        this.cor = cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getIdNacional() {
        return idNacional;
    }

    public void setIdNacional(String idNacional) {
        this.idNacional = idNacional;
    }
}
