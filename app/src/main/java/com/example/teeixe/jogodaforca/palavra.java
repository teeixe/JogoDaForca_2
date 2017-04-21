package com.example.teeixe.jogodaforca;

/**
 * Created by Guilherme on 17/04/2017.
 */

public class palavra {

    private String Nome;
    private int Tipo;

    public palavra (String Nome, int Tipo){

        this.Nome = Nome;
        this.Tipo = Tipo;
    }

    public palavra (String Nome){

        this.Nome = Nome;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }
}
