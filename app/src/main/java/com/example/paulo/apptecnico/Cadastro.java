package com.example.paulo.apptecnico;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity
{
    EditText txtNome, txtEmail, txtSenha, txtRepeteSenha;
    Button btnCadastrar;
    ProgressBar progressBar;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        txtNome = findViewById(R.id.name);
        txtEmail = findViewById(R.id.mail);
        txtSenha = findViewById(R.id.key);
        txtRepeteSenha = findViewById(R.id.key_again);
        btnCadastrar = findViewById(R.id.cadastrar);
        progressBar = findViewById(R.id.progressBar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nome, email, senha;
                nome = txtNome.getText().toString();
                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(Cadastro.this);

                String url = "http://192.168.15.17/cadastro.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                alertDialog = new AlertDialog.Builder(Cadastro.this);
                                //alertDialog.setTitle("Resposta do servidor:");
                                alertDialog.setMessage("Sucesso: " + response);
                                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        txtNome.setText("");
                                        txtEmail.setText("");
                                        txtSenha.setText("");
                                    }
                                });
                                AlertDialog alertDialog2 = alertDialog.create();
                                alertDialog2.show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Cadastro.this, "Erro!", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nome", nome);
                        params.put("email", email);
                        params.put("senha", senha);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }

    //MÉTODO BOTÃO JÁ SOU CADASTRADO
    public void clickBtnJaTenho(View view) {
        Intent it;
        it = new Intent(Cadastro.this, Login.class);
        startActivity(it);
    }
}