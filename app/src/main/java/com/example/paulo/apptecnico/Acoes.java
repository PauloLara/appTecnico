package com.example.paulo.apptecnico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class Acoes extends AppCompatActivity implements View.OnClickListener{
    final Arquivo arquivo = new Arquivo();

    public Button gravara1, gravara2, gravara3, gravara4, gravara5, gravara6, gravara7, gravara8, gravarb1,
 gravarb2, gravarb3, gravarb4, gravarb5, gravarb6, gravarb7, gravarb8, gravarc1, gravarc2, gravarc3,
 gravarc4, gravarc5, gravarc6, gravarc7, gravarc8, gravard1, gravard2, gravard3, gravard4, gravard5,
 gravard6, gravard7, gravard8, gravare1, gravare2, gravare3, gravare4, gravare5, gravare6, gravare7,
 gravare8, gravarf1, gravarf2, gravarf3, gravarf4, gravarf5, gravarf6, gravarf7, gravarf8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes);

        arquivo.criarArquivo();

        gravara1 = findViewById(R.id.a1);
        gravara2 = findViewById(R.id.a2);
        gravara3 = findViewById(R.id.a3);
        gravara4 = findViewById(R.id.a4);
        gravara5 = findViewById(R.id.a5);
        gravara6 = findViewById(R.id.a6);
        gravara7 = findViewById(R.id.a7);
        gravara8 = findViewById(R.id.a8);
        gravarb1 = findViewById(R.id.b1);
        gravarb2 = findViewById(R.id.b2);
        gravarb3 = findViewById(R.id.b3);
        gravarb4 = findViewById(R.id.b4);
        gravarb5 = findViewById(R.id.b5);
        gravarb6 = findViewById(R.id.b6);
        gravarb7 = findViewById(R.id.b7);
        gravarb8 = findViewById(R.id.b8);
        gravarc1 = findViewById(R.id.c1);
        gravarc2 = findViewById(R.id.c2);
        gravarc3 = findViewById(R.id.c3);
        gravarc4 = findViewById(R.id.c4);
        gravarc5 = findViewById(R.id.c5);
        gravarc6 = findViewById(R.id.c6);
        gravarc7 = findViewById(R.id.c7);
        gravarc8 = findViewById(R.id.c8);
        gravard1 = findViewById(R.id.d1);
        gravard2 = findViewById(R.id.d2);
        gravard3 = findViewById(R.id.d3);
        gravard4 = findViewById(R.id.d4);
        gravard5 = findViewById(R.id.d5);
        gravard6 = findViewById(R.id.d6);
        gravard7 = findViewById(R.id.d7);
        gravard8 = findViewById(R.id.d8);
        gravare1 = findViewById(R.id.e1);
        gravare2 = findViewById(R.id.e2);
        gravare3 = findViewById(R.id.e3);
        gravare4 = findViewById(R.id.e4);
        gravare5 = findViewById(R.id.e5);
        gravare6 = findViewById(R.id.e6);
        gravare7 = findViewById(R.id.e7);
        gravare8 = findViewById(R.id.e8);
        gravarf1 = findViewById(R.id.f1);
        gravarf2 = findViewById(R.id.f2);
        gravarf3 = findViewById(R.id.f3);
        gravarf4 = findViewById(R.id.f4);
        gravarf5 = findViewById(R.id.f5);
        gravarf6 = findViewById(R.id.f6);
        gravarf7 = findViewById(R.id.f7);
        gravarf8 = findViewById(R.id.f8);
    }

    public void clicka1(View v) throws IOException {
        int idd = gravara1.getId();
        String st = getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), "A1!", Toast.LENGTH_SHORT).show();
        arquivo.flushArquivo();
    }

    public void clicka2(View v) throws IOException {
        int idd = gravara2.getId();
        String st = getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), "A2!", Toast.LENGTH_SHORT).show();
        arquivo.flushArquivo();
    }

    public void clicka3(View v) throws IOException {
        int idd = gravara3.getId();
        String st = getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), "A3!", Toast.LENGTH_SHORT).show();
        arquivo.flushArquivo();
    }

    public void clicka4(View v) throws IOException {
        int idd = gravara4.getId();
        String st = getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), "A4!", Toast.LENGTH_SHORT).show();
        arquivo.flushArquivo();
    }

    public void clicka5(View v) throws IOException {
        int idd = gravara5.getId();
        String st = getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), "A5!", Toast.LENGTH_SHORT).show();
        arquivo.flushArquivo();
    }

    public void clicka6(View v) throws IOException {
        int idd = gravara6.getId();
        String st = getResources().getResourceEntryName(idd);
        arquivo.escreverArquivo(st);
        Toast.makeText(getApplicationContext(), "A6!", Toast.LENGTH_SHORT).show();
        arquivo.flushArquivo();
    }

    public void onClick(View v) {

    }
}
