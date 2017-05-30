package com.example.teeixe.jogodaforca;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;

public class Main2Activity extends AppCompatActivity {


    private EditText etSegredoDois;
    private Button bComeca;
    private Button bSolo;
    private Button bDois;
    private Button bFacil;
    private Button bMedio;
    private Button bDificil;
    private LinearLayout llBDif;
    private int flagDois;
    private TextView tvInfo;
    private int dificuldade;
    private TextView tvGeral;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //MobileAds.initialize(this, "@string/app_id");

        AdView mAdView = (AdView) findViewById(R.id.adViewTeste);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        etSegredoDois = (EditText) findViewById(R.id.etDisplayView);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        bComeca = (Button) findViewById(R.id.bComecar);
        bSolo = (Button) findViewById(R.id.bSolo);
        bDois = (Button) findViewById(R.id.bDois);
        bFacil = (Button) findViewById(R.id.bFacil);
        bMedio = (Button) findViewById(R.id.bMedio);
        bDificil = (Button) findViewById(R.id.bDificil);
        llBDif = (LinearLayout) findViewById(R.id.llBDif);
        tvGeral = (TextView) findViewById(R.id.textViewGeral);
        etSegredoDois.setVisibility(View.INVISIBLE);
        tvInfo.setVisibility(View.INVISIBLE);
        bComeca.setVisibility(View.INVISIBLE);

        llBDif.setVisibility(View.INVISIBLE);
    }

    public void OnClickComeca(View view){

        String SegredoDois = etSegredoDois.getText().toString();

        switch(view.getId()){
            case R.id.bFacil:{
                dificuldade = 0;
                etSegredoDois.setVisibility(View.INVISIBLE);
                break;
            }
            case R.id.bMedio:{
                dificuldade = 1;
                break;
            }
            case R.id.bDificil:{
                dificuldade = 2;
                break;
            }
        }


        if((SegredoDois.length() < 2) && (View.VISIBLE == etSegredoDois.getVisibility())){
            Toast.makeText(getApplicationContext(),"Escreva uma palavra segredo de pelo menos 2 letras",Toast.LENGTH_SHORT).show();
        }
        else {

            Intent i = new Intent(this, MainActivity.class);

            i.putExtra("txt1", SegredoDois);
            i.putExtra("flagDois", flagDois);
            i.putExtra("Dificuldade", dificuldade);

            etSegredoDois.setText("");
            bComeca.setVisibility(View.INVISIBLE);
            etSegredoDois.setVisibility(View.INVISIBLE);
            bSolo.setTextColor(Color.BLACK);
            bDois.setTextColor(Color.BLACK);
            bSolo.setVisibility(View.VISIBLE);
            bDois.setVisibility(View.VISIBLE);
            tvInfo.setVisibility(View.INVISIBLE);
            llBDif.setVisibility(View.INVISIBLE);
            tvGeral.setVisibility(View.VISIBLE);
            startActivity(i);
        }
    }

    public void OnClickSolo(View view){

        flagDois = 0;

        llBDif.setVisibility(View.VISIBLE);

        etSegredoDois.setVisibility(View.INVISIBLE);
        etSegredoDois.setText("");
        bDois.setTextColor(Color.BLACK);
        bSolo.setTextColor(Color.BLUE);
        bDois.setVisibility(View.INVISIBLE);
        tvInfo.setVisibility(View.INVISIBLE);
        tvGeral.setVisibility(View.INVISIBLE);
    }

    public void OnClickDois(View view){

        flagDois = 1;
        dificuldade = 0;

        etSegredoDois.setVisibility(View.VISIBLE);
        bComeca.setVisibility(View.VISIBLE);
        tvInfo.setVisibility(View.VISIBLE);
        bSolo.setTextColor(Color.BLACK);
        bSolo.setVisibility(View.INVISIBLE);
        bDois.setTextColor(Color.BLUE);
        tvGeral.setVisibility(View.INVISIBLE);

    }

}
