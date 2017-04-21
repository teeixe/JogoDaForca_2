package com.example.teeixe.jogodaforca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvSegredo;
    private Button bRestartFacil;
    private Button bRestartMedio;
    private Button bRestartDificil;
    private char cTexButton;
    private String Segredo;
    private char[] auxChute;
    private char[] auxSegredo;
    private String strChute;
    private Button bChute;
    private int acerto;
    private int vidas;
    private int perdeu;
    private ImageView ivFundo;
    private int dificuldade;
    private int index;
    private int flagDois;
    private TextView tvDificuldade;

    private Random randomGen = new Random();

    private ArrayList<palavra> Palavras = new ArrayList();
    private ArrayList<palavra> Palavras0 = new ArrayList();
    private ArrayList<palavra> Palavras1 = new ArrayList();
    private ArrayList<palavra> Palavras2 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSegredo = (TextView) findViewById(R.id.tvSegredo);
        ivFundo = (ImageView) findViewById(R.id.ivFundo);
        bRestartFacil = (Button) findViewById(R.id.bRestartFacil);
        bRestartMedio = (Button) findViewById(R.id.bRestartMedio);
        bRestartDificil = (Button) findViewById(R.id.bRestartDificil);
        tvDificuldade = (TextView) findViewById(R.id.tvDificuldade);

        dificuldade = 0;

        hardcode();

        iniciar();

    }

    public void hardcode() {

        palavra p0 = new palavra("cachorro", 0);
        palavra p1 = new palavra("banana", 0);
        palavra p2 = new palavra("risque", 1);
        palavra p3 = new palavra("tecnologia", 2);
        palavra p4 = new palavra("boladao", 1);
        palavra p5 = new palavra("Esquematico", 2);

        Palavras.add(p1);
        Palavras.add(p0);
        Palavras.add(p2);
        Palavras.add(p3);
        Palavras.add(p4);
        Palavras.add(p5);

        for (int i = 0; i < Palavras.size(); i++) {

            switch (Palavras.get(i).getTipo()) {
                case 0: {
                    Palavras0.add(Palavras.get(i));
                    break;
                }
                case 1: {
                    Palavras1.add(Palavras.get(i));
                    break;
                }
                case 2: {
                    Palavras2.add(Palavras.get(i));
                    break;
                }
            }
        }
    }

    public void buscarSegredo(){

        switch (dificuldade){
            case 0:{
                index = randomGen.nextInt(Palavras0.size());
                Segredo = Palavras0.get(index).getNome();
                tvDificuldade.setText("Fácil");
                break;
            }
            case 1:{
                index = randomGen.nextInt(Palavras1.size());
                Segredo = Palavras1.get(index).getNome();
                tvDificuldade.setText("Médio");
                break;
            }
            case 2:{
                index = randomGen.nextInt(Palavras2.size());
                Segredo = Palavras2.get(index).getNome();
                tvDificuldade.setText("Difícil");
                break;
            }
        }

    }

    public void iniciar() {

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        Segredo = extras.getString("txt1");
        flagDois = extras.getInt("flagDois");

        if(flagDois !=1)
        buscarSegredo();

        Segredo = Segredo.toUpperCase();

        auxSegredo = Segredo.toCharArray();
        vidas = 7;
        acerto = 0;
        strChute = "";
        auxChute = new char[auxSegredo.length];

        for (int i = 0; i < auxChute.length; i++) {
            auxChute[i] = '_';
        }

        strChute = strChute.copyValueOf(auxChute);
        tvSegredo.setText(strChute.toString());

        bRestartFacil.setVisibility(View.GONE);
        bRestartMedio.setVisibility(View.GONE);
        bRestartDificil.setVisibility(View.GONE);

        ivFundo.setImageResource(R.drawable.green_1);

    }

    public void onClickChute(View view) {

        bChute = (Button) view;

        cTexButton = bChute.getText().toString().charAt(0);

        strChute = "";

        for (int i = 0; i < auxSegredo.length; i++) { // passa por todas as letras do vetor segv
            if (auxSegredo[i] == cTexButton) { // se chute correto
                auxChute[i] = cTexButton; // guarda letra no vetor de chute
                acerto++;
            }
        }



        //verifica vidas e altera a imagem de fundo

        //escreve no tv da chave
        strChute = strChute.copyValueOf(auxChute);
        tvSegredo.setText(strChute.toString());

        controlaForca(acerto);
        acerto = 0;


        if(strChute.equals(Segredo)){
            tvSegredo.setText("ganhou!!");
            bRestartFacil.setVisibility(View.VISIBLE);
            bRestartMedio.setVisibility(View.VISIBLE);
            bRestartDificil.setVisibility(View.VISIBLE);        }

    }

    public void onClickRestart(View view) {

        switch(view.getId()){
            case R.id.bRestartFacil:
                dificuldade = 0;
            case R.id.bRestartMedio:
                dificuldade = 1;
            case R.id.bRestartDificil:
                dificuldade = 2;
        }
        flagDois = 0;
        iniciar();
    }

    private void zeraChute() {

        auxChute = new char[auxSegredo.length];
        for (int i = 0; i < auxChute.length; i++) {
            auxChute[i] = '_';
        }

    }

    private void controlaForca(int chute) {

        if (acerto == 0) {
            vidas--;
            switch (vidas) {
                case 6:
                    ivFundo.setImageResource(R.drawable.green_2);
                    break;
                case 5:
                    ivFundo.setImageResource(R.drawable.green_3);
                    break;
                case 4:
                    ivFundo.setImageResource(R.drawable.green_4);
                    break;
                case 3:
                    ivFundo.setImageResource(R.drawable.green_5);
                    break;
                case 2:
                    ivFundo.setImageResource(R.drawable.green_6);
                    break;
                case 1:
                    ivFundo.setImageResource(R.drawable.green_fim);
                    break;
                case 0: {
                    tvSegredo.setText("perdeu!!");
                    bRestartFacil.setVisibility(View.VISIBLE);
                    bRestartMedio.setVisibility(View.VISIBLE);
                    bRestartDificil.setVisibility(View.VISIBLE);
                }
                    break;
            }
        }
    }
}