package com.example.paulo.apptecnico;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Cadastro extends AppCompatActivity
{
    EditText txtNome, txtEmail, txtSenha, txtRepeteSenha;
    Button btnCadastrar;
    ProgressBar progressBar;
    ConnectionClass connectionClass;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        connectionClass = new ConnectionClass();
        txtNome = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);
        txtSenha = findViewById(R.id.senha);
        txtRepeteSenha = findViewById(R.id.key_again);
        btnCadastrar = findViewById(R.id.cadastrar);
        progressBar = findViewById(R.id.progressBar);

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
            }
        });
    }

   @SuppressLint("StaticFieldLeak")
    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String message = "";
        Boolean isSuccess = false;
        String nome = txtNome.getText().toString();
        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r) {
            progressBar.setVisibility(View.GONE);
            if(isSuccess) {
                Toast.makeText(getApplicationContext(),"sucesso",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(),"erro",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... params)
        {
            if(email.trim().equals("")|| senha.trim().equals(""))
                message = "Insira email e/ou senha";
            else
            {
                try
                {
                    Connection con = connectionClass.CONN();
                    if (con == null)
                    {
                        message = "Verifique a conexão com a internet";
                    }
                    else
                    {
                        String query = "'INSERT INTO Usuario(email, nome, senha) VALUES("+ email +", "+ nome +", "+ senha +")'";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if(rs.next())
                        {
                            message = "Logado";
                            isSuccess=true;
                            con.close();
                        }
                        else
                        {
                            message = "Credenciais inválidas";
                            isSuccess = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),"insucesso",Toast.LENGTH_SHORT).show();
                    isSuccess = false;
                    message = ex.getMessage();
                }
            }
            return message;
        }
    }

    //MÉTODO BOTÃO JÁ SOU CADASTRADO
    public void clickBtnJaTenho(View view) {
        Intent it;
        it = new Intent(Cadastro.this, Login.class);
        startActivity(it);
    }
}
