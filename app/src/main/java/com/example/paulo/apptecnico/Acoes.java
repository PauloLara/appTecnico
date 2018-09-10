package com.example.paulo.apptecnico;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class Acoes extends AppCompatActivity implements View.OnClickListener{
    final Arquivo arquivo = new Arquivo();


    public Button a1, a2, a3, a4, a5, a6, a7, a8, b1,
 b2, b3, b4, b5, b6, b7, b8, c1, c2, c3,
 c4, c5, c6, c7, c8, d1, d2, d3, d4, d5,
 d6, d7, d8, e1, e2, e3, e4, e5, e6, e7,
 e8, f1, f2, f3, f4, f5, f6, f7, f8, goleiro1,
 lateral2, zagueiro3, zagueiro4, cabArea5, lateral6, atacante7, volante8, centroavante9, camisa10, atacante11,
 salvar, finalizar, limpar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes);

        arquivo.criarArquivo();

        goleiro1 = findViewById(R.id.goleiro1);
        goleiro1.setOnClickListener(this);
        lateral2 = findViewById(R.id.lat_direito2);
        lateral2.setOnClickListener(this);
        zagueiro3 = findViewById(R.id.zagueiro3);
        zagueiro3.setOnClickListener(this);
        zagueiro4 = findViewById(R.id.zagueiro4);
        zagueiro4.setOnClickListener(this);
        cabArea5 = findViewById(R.id.cabeca_area5);
        cabArea5.setOnClickListener(this);
        lateral6 = findViewById(R.id.lat_esquerdo6);
        lateral6.setOnClickListener(this);
        atacante7 = findViewById(R.id.atacante7);
        atacante7.setOnClickListener(this);
        volante8 = findViewById(R.id.volante8);
        volante8.setOnClickListener(this);
        centroavante9 = findViewById(R.id.centroavante9);
        centroavante9.setOnClickListener(this);
        camisa10 = findViewById(R.id.camisa10);
        camisa10.setOnClickListener(this);
        atacante11 = findViewById(R.id.atacante11);
        atacante11.setOnClickListener(this);

        a1 = findViewById(R.id.a1);
        a1.setOnClickListener(this);
        a2 = findViewById(R.id.a2);
        a2.setOnClickListener(this);
        a3 = findViewById(R.id.a3);
        a3.setOnClickListener(this);
        a4 = findViewById(R.id.a4);
        a4.setOnClickListener(this);
        a5 = findViewById(R.id.a5);
        a5.setOnClickListener(this);
        a6 = findViewById(R.id.a6);
        a6.setOnClickListener(this);
        a7 = findViewById(R.id.a7);
        a7.setOnClickListener(this);
        a8 = findViewById(R.id.a8);
        a8.setOnClickListener(this);
        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.b3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.b4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.b5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.b6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.b7);
        b7.setOnClickListener(this);
        b8 = findViewById(R.id.b8);
        b8.setOnClickListener(this);
        c1 = findViewById(R.id.c1);
        c1.setOnClickListener(this);
        c2 = findViewById(R.id.c2);
        c2.setOnClickListener(this);
        c3 = findViewById(R.id.c3);
        c3.setOnClickListener(this);
        c4 = findViewById(R.id.c4);
        c4.setOnClickListener(this);
        c5 = findViewById(R.id.c5);
        c5.setOnClickListener(this);
        c6 = findViewById(R.id.c6);
        c6.setOnClickListener(this);
        c7 = findViewById(R.id.c7);
        c7.setOnClickListener(this);
        c8 = findViewById(R.id.c8);
        c8.setOnClickListener(this);
        d1 = findViewById(R.id.d1);
        d1.setOnClickListener(this);
        d2 = findViewById(R.id.d2);
        d2.setOnClickListener(this);
        d3 = findViewById(R.id.d3);
        d3.setOnClickListener(this);
        d4 = findViewById(R.id.d4);
        d4.setOnClickListener(this);
        d5 = findViewById(R.id.d5);
        d5.setOnClickListener(this);
        d6 = findViewById(R.id.d6);
        d6.setOnClickListener(this);
        d7 = findViewById(R.id.d7);
        d7.setOnClickListener(this);
        d8 = findViewById(R.id.d8);
        d8.setOnClickListener(this);
        e1 = findViewById(R.id.e1);
        e1.setOnClickListener(this);
        e2 = findViewById(R.id.e2);
        e2.setOnClickListener(this);
        e3 = findViewById(R.id.e3);
        e3.setOnClickListener(this);
        e4 = findViewById(R.id.e4);
        e4.setOnClickListener(this);
        e5 = findViewById(R.id.e5);
        e5.setOnClickListener(this);
        e6 = findViewById(R.id.e6);
        e6.setOnClickListener(this);
        e7 = findViewById(R.id.e7);
        e7.setOnClickListener(this);
        e8 = findViewById(R.id.e8);
        e8.setOnClickListener(this);
        f1 = findViewById(R.id.f1);
        f1.setOnClickListener(this);
        f2 = findViewById(R.id.f2);
        f2.setOnClickListener(this);
        f3 = findViewById(R.id.f3);
        f3.setOnClickListener(this);
        f4 = findViewById(R.id.f4);
        f4.setOnClickListener(this);
        f5 = findViewById(R.id.f5);
        f5.setOnClickListener(this);
        f6 = findViewById(R.id.f6);
        f6.setOnClickListener(this);
        f7 = findViewById(R.id.f7);
        f7.setOnClickListener(this);
        f8 = findViewById(R.id.f8);
        f8.setOnClickListener(this);

        salvar = findViewById(R.id.salvar);
        finalizar = findViewById(R.id.finalizar);
        limpar = findViewById(R.id.limpar);
    }

    public void onClick(View v) {
        int idd = v.getId();
        String st = v.getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
        try {
            arquivo.flushArquivo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //BOTÕES A1 ATÉ A8

    /*public void clicka1(View v) throws IOException {
        int idd = a1.getId();
        String st = getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), "A1!", Toast.LENGTH_SHORT).show();
        arquivo.flushArquivo();
    }*/

    public void clickBtnVoltarInicial(){
        Intent it;
        it = new Intent(Acoes.this, Inicial.class);
        startActivity(it);
    }

    public void limpar(View v) throws IOException {
        arquivo.limparArquivo();
    }

    public void salvar(View v) throws IOException {
        arquivo.novaLinha();
    }

    public void finalizar(View v) throws IOException {
        arquivo.fecharArquivo();
        clickBtnVoltarInicial();
    }

    public void lerArquivo(View v) throws IOException {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Conteúdo:");
            String st = arquivo.getCaminho()+""+arquivo.getNomeArquivo();
            FileReader fr = new FileReader(st);
            BufferedReader br;
            br = new BufferedReader(fr);
            String digitado = br.readLine();
            while (digitado != null) {
                builder.setMessage(digitado).show();
                digitado = br.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
