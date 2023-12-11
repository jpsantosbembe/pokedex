package com.joaobembe.pokedex.model;

import android.security.keystore.StrongBoxUnavailableException;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private int idNacional;
    private String nome;
    private int vida;
    private int ataque;
    private int ataqueEspecial;
    private int defesa;
    private int defesaEspecial;
    private int velocidade;
    private int proxEvolucao;
    private double peso;
    private double altura;
    private List<Habilidade> habilidades;
    private List<Tipo> tipos;

    public Pokemon(int idNacional, String nome, int vida, int ataque, int ataqueEspecial, int defesa, int defesaEspecial, int velocidade, int proxEvolucao, double peso, double altura, List<Habilidade> habilidades, List<Tipo> tipos) {
        this.idNacional = idNacional;
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
        this.defesa = defesa;
        this.defesaEspecial = defesaEspecial;
        this.velocidade = velocidade;
        this.proxEvolucao = proxEvolucao;
        this.peso = peso;
        this.altura = altura;
        this.habilidades = habilidades;
        this.tipos = tipos;
    }

    public int getIdNacional() {
        return idNacional;
    }

    public void setIdNacional(int idNacional) {
        this.idNacional = idNacional;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }

    public void setAtaqueEspecial(int ataqueEspecial) {
        this.ataqueEspecial = ataqueEspecial;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public int getDefesaEspecial() {
        return defesaEspecial;
    }

    public void setDefesaEspecial(int defesaEspecial) {
        this.defesaEspecial = defesaEspecial;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getProxEvolucao() {
        return proxEvolucao;
    }

    public void setProxEvolucao(int proxEvolucao) {
        this.proxEvolucao = proxEvolucao;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }
}

