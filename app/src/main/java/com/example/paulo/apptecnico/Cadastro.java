package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {
    DatabaseHelper db;
    EditText txtNome, txtEmail, txtSenha, txtRepeteSenha;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        db = new DatabaseHelper(this);
        txtNome = (EditText) findViewById(R.id.name);
        txtEmail=(EditText) findViewById(R.id.email);
        txtSenha=(EditText) findViewById(R.id.key);
        txtRepeteSenha=(EditText) findViewById(R.id.key_again);
        btnCadastrar=(Button) findViewById(R.id.cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strtxtNome = txtNome.getText().toString();
                String strtxtEmail = txtEmail.getText().toString();
                String strtxtSenha = txtSenha.getText().toString();
                String strtxtRepeteSenha = txtRepeteSenha.getText().toString();

               // if (strtxtNome.isEmpty() || strtxtEmail.equals("") || strtxtSenha.equals("") ||  strtxtRepeteSenha.equals("")) {
                if (strtxtNome.isEmpty() || strtxtEmail.isEmpty() || strtxtSenha.isEmpty() ||  strtxtRepeteSenha.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Campos estão vazios!", Toast.LENGTH_SHORT).show();
                } else {
                    if (strtxtSenha.equals(strtxtRepeteSenha)) {
                        Boolean checkmail = db.checkEmail(strtxtEmail);
                        if (checkmail == true) {
                            boolean insert = db.insert(strtxtNome, strtxtEmail, strtxtSenha);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email já existe!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Passwords diferentes!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //MÉTODO BOTÃO JÁ SOU CADASTRADO
    public void clickBtnJaTenho(View view){
        Intent it;
        it = new Intent(Cadastro.this, Login.class);
        startActivity(it);
    }

}
