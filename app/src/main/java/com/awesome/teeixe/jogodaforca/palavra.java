package com.awesome.teeixe.jogodaforca;

/**
 * Created by Guilherme on 17/04/2017.
 */

public class palavra {

    private String Nome;
    private int Dificuldade;
    private String Dica;



    public palavra (String Nome, int Dificuldade, String Dica){

        this.Nome = Nome;
        this.Dificuldade = Dificuldade;
        this.Dica = Dica;
    }

    public palavra (String Nome){

        this.Nome = Nome;
    }

    public String getDica() {
        return Dica;
    }

    public void setDica(String dica) {
        Dica = dica;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getDificuldade() {
        return Dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        Dificuldade = Dificuldade;
    }
}
