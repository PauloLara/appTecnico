package com.example.paulo.apptecnico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Sistema extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistema);

    }

    public void clickBtnCadastrarTorneios(View view){
        Intent it;
        it = new Intent(Sistema.this, TorneioCadastro.class);
        startActivity(it);
    }

    public void clickBtnCadastrarJogadores(View view){
        Intent it;
        it = new Intent(Sistema.this, CadastrarJogadores.class);
        startActivity(it);
    }

    public void clickBtnDeletar(View view){
        Intent it;
        it = new Intent(Sistema.this, DeletarDados.class);
        startActivity(it);
    }
}