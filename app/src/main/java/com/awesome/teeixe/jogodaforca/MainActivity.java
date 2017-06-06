package com.awesome.teeixe.jogodaforca;

import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdRequest;


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

    private int addcounter = 0;
    private InterstitialAd mInterstitialAd;


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

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4199783869437075/3606884946");

        final AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("8566238FD93D0F9BE1D0447B33998056")
                .build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(adRequest);
            }

        });


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
        if (addcounter == 2) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                addcounter=0;
            } else {

            }
        } else {
            addcounter++;
        }
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

    public void hardcode() {

        palavra p0 = new palavra("cachorro", 0, "animal");
        palavra p1 = new palavra("banana", 0, "fruta");
        palavra p2 = new palavra("disco", 1, "musica");
        palavra p3 = new palavra("tecnologia", 1, "conceito");
        palavra p4 = new palavra("boladao", 1, "bravo");
        palavra p5 = new palavra("vodka", 2, "bebida");
        palavra p6 = new palavra("Cuiaba", 1, "cidade");
        palavra p7 = new palavra("camera", 0, "objeto");
        palavra p8 = new palavra("show", 2, "evento");
        palavra p9 = new palavra("guitarra", 1, "musica");
        palavra p10 = new palavra("fax", 2, "objeto");
        palavra p11 = new palavra("sofa", 1, "objeto");
        palavra p12 = new palavra("chinelo", 1, "calcado");
        palavra p13 = new palavra("concerto", 1, "evento");
        palavra p14 = new palavra("catuaba", 1, "bebida");
        palavra p15 = new palavra("pizza", 2, "comida");
        palavra p16 = new palavra("abacate", 0, "fruta");
        palavra p17 = new palavra("pombo", 0, "animal");
        palavra p18 = new palavra("trofeu", 1, "objeto");
        palavra p19 = new palavra("buzina", 1, "objeto");
        palavra p20 = new palavra("cajon", 2, "musica");
        palavra p21 = new palavra("Kleber", 1, "gladiador");
        palavra p22 = new palavra("cobra", 0, "animal");
        palavra p23 = new palavra("ameixa", 1, "fruta");
        palavra p24 = new palavra("chicara", 2, "objeto");
        palavra p25 = new palavra("cadeira", 0, "objeto");
        palavra p26 = new palavra("mochila", 1, "objeto");
        palavra p27 = new palavra("caqui", 2, "fruta");
        palavra p28 = new palavra("Recife", 0, "cidade");
        palavra p29 = new palavra("Pitanga", 1, "cidade");
        palavra p30 = new palavra("amapa", 2, "evento");
        palavra p31 = new palavra("bateria", 0, "instrumento");
        palavra p32 = new palavra("baixo", 1, "instrumento");
        palavra p33 = new palavra("sax", 2, "instrumento");
        palavra p34 = new palavra("tenis", 0, "calcado");
        palavra p35 = new palavra("pantufa", 1, "calcado");
        palavra p36 = new palavra("suco", 2, "bebida");
        palavra p37 = new palavra("leao", 0, "animal");
        palavra p38 = new palavra("Gaviao", 1, "animal");
        palavra p39 = new palavra("hiena", 2, "animal");
        palavra p40 = new palavra("dvd", 2, "objeto");
        palavra p41 = new palavra("coberta", 0, "objeto");
        palavra p42 = new palavra("controle", 1, "objeto");
        palavra p43 = new palavra("espada", 0, "gladiador");
        palavra p44 = new palavra("escudo", 1, "gladiador");
        palavra p45 = new palavra("bola", 0, "esporte");
        palavra p46 = new palavra("estadio", 1, "esporte");
        palavra p47 = new palavra("saque", 1, "esporte");
        palavra p48 = new palavra("ace", 2, "esporte");
        palavra p49 = new palavra("tampa", 0, "objeto");
        palavra p50 = new palavra("Maceio", 1, "cidade");
        palavra p51 = new palavra("Carregador", 2, "objeto");
        palavra p52 = new palavra("foto", 0, "arte");
        palavra p53 = new palavra("trailer", 2, "arte");
        palavra p54 = new palavra("danca", 0, "arte");
        palavra p55 = new palavra("canto", 1, "arte");
        palavra p56 = new palavra("tripe", 2, "objeto");
        palavra p57 = new palavra("arbitro", 0, "esporte");
        palavra p58 = new palavra("rede", 1, "esporte");
        palavra p59 = new palavra("roxo", 2, "cor");
        palavra p60 = new palavra("branco", 0, "cor");
        palavra p61 = new palavra("cinza", 1, "cor");
        palavra p62 = new palavra("lilas", 2, "cor");
        palavra p63= new palavra("cerveja", 0, "bebida");
        palavra p64 = new palavra("vinho", 1, "bebida");
        palavra p65 = new palavra("gim", 2, "bebida");



        Palavras.add(p1);
        Palavras.add(p0);
        Palavras.add(p2);
        Palavras.add(p3);
        Palavras.add(p4);
        Palavras.add(p5);
        Palavras.add(p6);
        Palavras.add(p7);
        Palavras.add(p8);
        Palavras.add(p9);
        Palavras.add(p10);
        Palavras.add(p11);
        Palavras.add(p12);
        Palavras.add(p13);
        Palavras.add(p14);
        Palavras.add(p15);
        Palavras.add(p16);
        Palavras.add(p17);
        Palavras.add(p18);
        Palavras.add(p19);
        Palavras.add(p20);
        Palavras.add(p21);
        Palavras.add(p22);
        Palavras.add(p23);
        Palavras.add(p24);
        Palavras.add(p25);
        Palavras.add(p26);
        Palavras.add(p27);
        Palavras.add(p28);
        Palavras.add(p29);
        Palavras.add(p30);
        Palavras.add(p31);
        Palavras.add(p32);
        Palavras.add(p33);
        Palavras.add(p34);
        Palavras.add(p35);
        Palavras.add(p36);
        Palavras.add(p37);
        Palavras.add(p38);
        Palavras.add(p39);
        Palavras.add(p40);
        Palavras.add(p41);
        Palavras.add(p42);
        Palavras.add(p43);
        Palavras.add(p44);
        Palavras.add(p45);
        Palavras.add(p46);
        Palavras.add(p47);
        Palavras.add(p48);
        Palavras.add(p49);
        Palavras.add(p50);
        Palavras.add(p51);
        Palavras.add(p52);
        Palavras.add(p53);
        Palavras.add(p54);
        Palavras.add(p55);
        Palavras.add(p56);
        Palavras.add(p57);
        Palavras.add(p58);
        Palavras.add(p59);
        Palavras.add(p60);
        Palavras.add(p61);
        Palavras.add(p62);
        Palavras.add(p63);
        Palavras.add(p64);
        Palavras.add(p65);


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
}