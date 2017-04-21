package com.example.teeixe.jogodaforca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {


    private EditText etSegredoDois;
    private Button bComeca;
    private int flagDois;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etSegredoDois = (EditText) findViewById(R.id.etDisplayView);
        etSegredoDois.setVisibility(View.INVISIBLE);
        bComeca = (Button) findViewById(R.id.bComecar);
        bComeca.setVisibility(View.INVISIBLE);
    }

    public void OnClickComeca(View view){

        String SegredoDois = etSegredoDois.getText().toString();

        Intent i = new Intent(this, MainActivity.class);

        i.putExtra("txt1", SegredoDois);
        i.putExtra("flagDois", flagDois);

        startActivity(i);

    }

    public void OnClickSolo(View view){

        flagDois = 0;
        bComeca.setVisibility(View.VISIBLE);
        etSegredoDois.setVisibility(View.INVISIBLE);
        etSegredoDois.setText("");
    }

    public void OnClickDois(View view){

        flagDois = 1;
        etSegredoDois.setVisibility(View.VISIBLE);
        bComeca.setVisibility(View.VISIBLE);
    }
}
