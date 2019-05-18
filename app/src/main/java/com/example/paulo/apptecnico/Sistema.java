package com.example.paulo.apptecnico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Sistema extends AppCompatActivity {

    Button btnSair;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistema);
        btnSair = findViewById(R.id.btnSair);
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

    public void clickBtnIrEquipeConfig(View view){
        Intent it;
        it = new Intent(Sistema.this, EquipeFutsalConfig.class);
        startActivity(it);
    }

    public void btnIrCadastradas(View view){
        Intent it;
        it = new Intent(Sistema.this, EquipesCadastradas.class);
        startActivity(it);
    }

    public void clickBtnSair(View view){
        Intent it;
        it = new Intent(Sistema.this, Inicial.class);
        startActivity(it);
    }
}
