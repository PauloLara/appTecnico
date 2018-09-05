package com.example.paulo.apptecnico;

import android.content.Intent;
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
 gravare8, gravarf1, gravarf2, gravarf3, gravarf4, gravarf5, gravarf6, gravarf7, gravarf8, goleiro1,
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

        gravara1 = findViewById(R.id.a1);
        gravara1.setOnClickListener(this);
        gravara2 = findViewById(R.id.a2);
        gravara2.setOnClickListener(this);
        gravara3 = findViewById(R.id.a3);
        gravara3.setOnClickListener(this);
        gravara4 = findViewById(R.id.a4);
        gravara4.setOnClickListener(this);
        gravara5 = findViewById(R.id.a5);
        gravara5.setOnClickListener(this);
        gravara6 = findViewById(R.id.a6);
        gravara6.setOnClickListener(this);
        gravara7 = findViewById(R.id.a7);
        gravara7.setOnClickListener(this);
        gravara8 = findViewById(R.id.a8);
        gravara8.setOnClickListener(this);
        gravarb1 = findViewById(R.id.b1);
        gravarb1.setOnClickListener(this);
        gravarb2 = findViewById(R.id.b2);
        gravarb2.setOnClickListener(this);
        gravarb3 = findViewById(R.id.b3);
        gravarb3.setOnClickListener(this);
        gravarb4 = findViewById(R.id.b4);
        gravarb4.setOnClickListener(this);
        gravarb5 = findViewById(R.id.b5);
        gravarb5.setOnClickListener(this);
        gravarb6 = findViewById(R.id.b6);
        gravarb6.setOnClickListener(this);
        gravarb7 = findViewById(R.id.b7);
        gravarb7.setOnClickListener(this);
        gravarb8 = findViewById(R.id.b8);
        gravarb8.setOnClickListener(this);
        gravarc1 = findViewById(R.id.c1);
        gravarc1.setOnClickListener(this);
        gravarc2 = findViewById(R.id.c2);
        gravarc2.setOnClickListener(this);
        gravarc3 = findViewById(R.id.c3);
        gravarc3.setOnClickListener(this);
        gravarc4 = findViewById(R.id.c4);
        gravarc4.setOnClickListener(this);
        gravarc5 = findViewById(R.id.c5);
        gravarc5.setOnClickListener(this);
        gravarc6 = findViewById(R.id.c6);
        gravarc6.setOnClickListener(this);
        gravarc7 = findViewById(R.id.c7);
        gravarc7.setOnClickListener(this);
        gravarc8 = findViewById(R.id.c8);
        gravarc8.setOnClickListener(this);
        gravard1 = findViewById(R.id.d1);
        gravard1.setOnClickListener(this);
        gravard2 = findViewById(R.id.d2);
        gravard2.setOnClickListener(this);
        gravard3 = findViewById(R.id.d3);
        gravard3.setOnClickListener(this);
        gravard4 = findViewById(R.id.d4);
        gravard4.setOnClickListener(this);
        gravard5 = findViewById(R.id.d5);
        gravard5.setOnClickListener(this);
        gravard6 = findViewById(R.id.d6);
        gravard6.setOnClickListener(this);
        gravard7 = findViewById(R.id.d7);
        gravard7.setOnClickListener(this);
        gravard8 = findViewById(R.id.d8);
        gravard8.setOnClickListener(this);
        gravare1 = findViewById(R.id.e1);
        gravare1.setOnClickListener(this);
        gravare2 = findViewById(R.id.e2);
        gravare2.setOnClickListener(this);
        gravare3 = findViewById(R.id.e3);
        gravare3.setOnClickListener(this);
        gravare4 = findViewById(R.id.e4);
        gravare4.setOnClickListener(this);
        gravare5 = findViewById(R.id.e5);
        gravare5.setOnClickListener(this);
        gravare6 = findViewById(R.id.e6);
        gravare6.setOnClickListener(this);
        gravare7 = findViewById(R.id.e7);
        gravare7.setOnClickListener(this);
        gravare8 = findViewById(R.id.e8);
        gravare8.setOnClickListener(this);
        gravarf1 = findViewById(R.id.f1);
        gravarf1.setOnClickListener(this);
        gravarf2 = findViewById(R.id.f2);
        gravarf2.setOnClickListener(this);
        gravarf3 = findViewById(R.id.f3);
        gravarf3.setOnClickListener(this);
        gravarf4 = findViewById(R.id.f4);
        gravarf4.setOnClickListener(this);
        gravarf5 = findViewById(R.id.f5);
        gravarf5.setOnClickListener(this);
        gravarf6 = findViewById(R.id.f6);
        gravarf6.setOnClickListener(this);
        gravarf7 = findViewById(R.id.f7);
        gravarf7.setOnClickListener(this);
        gravarf8 = findViewById(R.id.f8);
        gravarf8.setOnClickListener(this);

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
        int idd = gravara1.getId();
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

}
