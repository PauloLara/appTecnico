package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.senha);
        b1 = findViewById(R.id.entrar);
        b2 = findViewById(R.id.cadastre);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void clickBtnIrCadastrar(View view){
        Intent it;
        it = new Intent(Login.this, Cadastro.class);
        startActivity(it);
    }

    public void clickBtnIrInicial(View view){
        Intent it;
        it = new Intent(Login.this, Inicial.class);
        startActivity(it);
    }

    public void clickBtnIrPartidaConfig(View view) {
        Intent it;
        it = new Intent(Login.this, PartidaConfig.class);
        startActivity(it);

    }
}
