package com.example.teeixe.jogodaforca;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvSegredo;
    private TextView tvFim;
    private TextView tvDica;
    private Button bRestartFacil;
    private Button bRestartMedio;
    private Button bRestartDificil;
    private Button bPlayAgain;
    private Button bDica;
    private char cTexButton;
    private String Segredo;
    private char[] auxChute;
    private char[] auxSegredo;
    private String strChute;
    private Button bChute;
    private int acerto;
    private int vidas;
    private ImageView ivFundo;
    private int dificuldade;
    private int index;
    private int flagDois;
    private TextView tvDificuldade;
    private Button bQ;     private Button bW;    private Button bE;    private Button bR;    private Button bT;
    private Button bYY;    private Button bU;    private Button bI;    private Button bO;    private Button bP;
    private Button bA;    private Button bS;    private Button bD;    private Button bF;    private Button bG;
    private Button bH;    private Button bJ;    private Button bK;    private Button bL;    private Button bZ;
    private Button bX;    private Button bC;    private Button bV;    private Button bB;    private Button bN;  private Button bM;


    private Random randomGen = new Random();

    private ArrayList<palavra> Palavras = new ArrayList();
    private ArrayList<palavra> Palavras0 = new ArrayList();
    private ArrayList<palavra> Palavras1 = new ArrayList();
    private ArrayList<palavra> Palavras2 = new ArrayList();
    private ArrayList<Button> Teclado = new ArrayList();

    private StringBuilder strSegredoDisplay = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSegredo = (TextView) findViewById(R.id.tvSegredo);
        tvFim = (TextView) findViewById(R.id.tvFim);
        tvDica = (TextView) findViewById(R.id.tvDica);
        tvDificuldade = (TextView) findViewById(R.id.tvDificuldade);
        ivFundo = (ImageView) findViewById(R.id.ivFundo);
        bRestartFacil = (Button) findViewById(R.id.bRestartFacil);
        bRestartMedio = (Button) findViewById(R.id.bRestartMedio);
        bRestartDificil = (Button) findViewById(R.id.bRestartDificil);
        bPlayAgain = (Button) findViewById(R.id.bPlayAgain);
        bDica = (Button) findViewById(R.id.bDica);


        tecladoCreate();

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        Segredo = extras.getString("txt1");
        flagDois = extras.getInt("flagDois");
        dificuldade = extras.getInt("Dificuldade");

        hardcode();

        iniciar();

    }

    public void hardcode() {

        palavra p0 = new palavra("cachorro", 0, "animal");
        palavra p1 = new palavra("banana", 0, "fruta");
        palavra p2 = new palavra("disco", 1, "musica");
        palavra p3 = new palavra("tecnologia", 1, "conceito");
        palavra p4 = new palavra("boladao", 1, "bravo");
        palavra p5 = new palavra("vodka", 2, "bebida");

        Palavras.add(p1);
        Palavras.add(p0);
        Palavras.add(p2);
        Palavras.add(p3);
        Palavras.add(p4);
        Palavras.add(p5);

        for (int i = 0; i < Palavras.size(); i++) {

            switch (Palavras.get(i).getDificuldade()) {
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
                tvDica.setText(Palavras0.get(index).getDica());
                break;
            }
            case 1:{
                index = randomGen.nextInt(Palavras1.size());
                Segredo = Palavras1.get(index).getNome();
                tvDificuldade.setText("Médio");
                tvDica.setText(Palavras1.get(index).getDica());
                break;
            }
            case 2:{
                index = randomGen.nextInt(Palavras2.size());
                Segredo = Palavras2.get(index).getNome();
                tvDificuldade.setText("Difícil");
                tvDica.setText(Palavras2.get(index).getDica());
                break;
            }
        }

    }

    public void iniciar() {

        if(flagDois !=1)
        buscarSegredo();
        else{
            tvDificuldade.setText("");
            tvDica.setText("");
            bDica.setVisibility(View.INVISIBLE);

        }

        tvFim.setVisibility(View.INVISIBLE);
        tvDica.setVisibility(View.INVISIBLE);

        for (int i =0; i<Teclado.size();i++){
            Teclado.get(i).setEnabled(true);
            Teclado.get(i).setTextColor(Color.BLACK);
        }

        Segredo = Segredo.toUpperCase();

        auxSegredo = Segredo.toCharArray();
        vidas = 6;
        acerto = 0;
        strChute = "";
        auxChute = new char[auxSegredo.length];

        for (int i = 0; i < auxChute.length; i++) {
            auxChute[i] = '_';
        }

        strChute = strChute.copyValueOf(auxChute);

        strSegredoDisplay.setLength(0);

        for (int i = 0; i < auxChute.length; i++) {
            strSegredoDisplay.append(auxChute[i] + " ");
        }

        tvSegredo.setText(strSegredoDisplay.toString());

        bRestartFacil.setVisibility(View.GONE);
        bRestartMedio.setVisibility(View.GONE);
        bRestartDificil.setVisibility(View.GONE);
        bPlayAgain.setVisibility(View.GONE);

        ivFundo.setImageResource(R.drawable.green_1_n);

    }

    public void onClickChute(View view) {

        bChute = (Button) view;
        //xalala

        cTexButton = bChute.getText().toString().charAt(0);

        strChute = "";
        strSegredoDisplay.setLength(0);

        for (int i = 0; i < auxSegredo.length; i++) { // passa por todas as letras do vetor segv
            if (auxSegredo[i] == cTexButton) { // se chute correto
                auxChute[i] = cTexButton; // guarda letra no vetor de chute
                acerto++;
                bChute.setEnabled(false);
                bChute.setTextColor(Color.GREEN);
            }
        }

        //escreve no tv da chave
        strChute = strChute.copyValueOf(auxChute);

        for (int i = 0; i < auxChute.length; i++) {
            strSegredoDisplay.append(auxChute[i] + " ");
        }

        tvSegredo.setText(strSegredoDisplay.toString());

        controlaForca(acerto);
        acerto = 0;


        if(strChute.equals(Segredo)) {
            tvFim.setVisibility(View.VISIBLE);
            tvFim.setTextColor(Color.GREEN);
            tvFim.setText("GANHOU!!");
            if (flagDois == 0) {
                bRestartFacil.setVisibility(View.VISIBLE);
                bRestartMedio.setVisibility(View.VISIBLE);
                bRestartDificil.setVisibility(View.VISIBLE);
            }
            else
                bPlayAgain.setVisibility(View.VISIBLE);
        }
    }

    public void onClickRestart(View view) {

        switch(view.getId()) {
            case R.id.bRestartFacil: {
                dificuldade = 0;
                break;
            }
            case R.id.bRestartMedio: {
                dificuldade = 1;
                break;
            }
            case R.id.bRestartDificil: {
                dificuldade = 2;
                break;
            }
        }
        flagDois = 0;
        bChute.setEnabled(true);
        bChute.setTextColor(Color.BLACK);
        iniciar();
    }

    public void onClickDica(View view){
        if(tvDica.getVisibility() == View.VISIBLE)
            tvDica.setVisibility(View.INVISIBLE);
        else
            tvDica.setVisibility(View.VISIBLE);
    }

    public void OnClickPlayAgain(View v){
        finish();
    }

    private void zeraChute(View view) {

        auxChute = new char[auxSegredo.length];
        for (int i = 0; i < auxChute.length; i++) {
            auxChute[i] = '_';
        }

    }

    private void controlaForca(int chute) {

        if (acerto == 0) {
            vidas--;
            //bChute.setBackgroundColor(Color.RED);

            bChute.setEnabled(false);
            bChute.setTextColor(Color.RED);
            //bChute.setHighlightColor(Color.GREEN);
            switch (vidas) {
                case 5:
                    ivFundo.setImageResource(R.drawable.green_2_n);
                    break;
                case 4:
                    ivFundo.setImageResource(R.drawable.green_3_n);
                    break;
                case 3:
                    ivFundo.setImageResource(R.drawable.green_4_n);
                    break;
                case 2:
                    ivFundo.setImageResource(R.drawable.green_5_n);
                    break;
                case 1:
                    ivFundo.setImageResource(R.drawable.green_6_n);
                    break;
                case 0: {
                    ivFundo.setImageResource(R.drawable.green_fim_n);
                    tvFim.setVisibility(View.VISIBLE);
                    tvFim.setTextColor(Color.RED);
                    tvFim.setText("PERDEU!!");
                    if (flagDois==0) {
                        bRestartFacil.setVisibility(View.VISIBLE);
                        bRestartMedio.setVisibility(View.VISIBLE);
                        bRestartDificil.setVisibility(View.VISIBLE);
                    }
                    else
                        bPlayAgain.setVisibility(View.VISIBLE);
                        for (int i =0; i<Teclado.size();i++){
                        Teclado.get(i).setEnabled(false);
                    }
                    break;
                }

            }
        }
    }

    private void tecladoCreate (){
        bQ = (Button) findViewById(R.id.bQ);
        bW = (Button) findViewById(R.id.bW);
        bE = (Button) findViewById(R.id.bE);
        bR = (Button) findViewById(R.id.bR);
        bT = (Button) findViewById(R.id.bT);
        bYY = (Button) findViewById(R.id.bYY);
        bU = (Button) findViewById(R.id.bU);
        bI = (Button) findViewById(R.id.bI);
        bO = (Button) findViewById(R.id.bO);
        bP = (Button) findViewById(R.id.bP);
        bA = (Button) findViewById(R.id.bA);
        bS = (Button) findViewById(R.id.bS);
        bD = (Button) findViewById(R.id.bD);
        bF = (Button) findViewById(R.id.bF);
        bG = (Button) findViewById(R.id.bG);
        bH = (Button) findViewById(R.id.bH);
        bJ = (Button) findViewById(R.id.bJ);
        bK = (Button) findViewById(R.id.bK);
        bL = (Button) findViewById(R.id.bL);
        bZ = (Button) findViewById(R.id.bZ);
        bX = (Button) findViewById(R.id.bX);
        bC = (Button) findViewById(R.id.bC);
        bV = (Button) findViewById(R.id.bV);
        bB = (Button) findViewById(R.id.bB);
        bN = (Button) findViewById(R.id.bN);
        bM = (Button) findViewById(R.id.bM);

        Teclado.add(bQ);
        Teclado.add(bW);
        Teclado.add(bE);
        Teclado.add(bR);
        Teclado.add(bT);
        Teclado.add(bYY);
        Teclado.add(bU);
        Teclado.add(bI);
        Teclado.add(bO);
        Teclado.add(bP);
        Teclado.add(bA);
        Teclado.add(bS);
        Teclado.add(bD);
        Teclado.add(bF);
        Teclado.add(bG);
        Teclado.add(bH);
        Teclado.add(bJ);
        Teclado.add(bK);
        Teclado.add(bL);
        Teclado.add(bZ);
        Teclado.add(bX);
        Teclado.add(bC);
        Teclado.add(bV);
        Teclado.add(bB);
        Teclado.add(bN);
        Teclado.add(bM);
    }
}