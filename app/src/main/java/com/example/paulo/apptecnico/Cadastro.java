package com.example.paulo.apptecnico;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
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

public class Cadastro extends AppCompatActivity {
    EditText txtNome, txtEmail, txtSenha, txtNomeClube;
    String nomeText, emailText, senhaText, nomeClubeText;
    Button btnCadastrar;
    ProgressBar progressBar;
    AlertDialog.Builder alertDialog;
    Boolean CheckEditText;

    private void sendEmail() {
        String email = txtEmail.getText().toString().trim();
        SendMail sm = new SendMail(Cadastro.this, email, "Teste", "Quero ver ser chegou");
        sm.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        txtNome = findViewById(R.id.name);
        txtEmail = findViewById(R.id.mail);
        txtSenha = findViewById(R.id.key);
        txtNomeClube = findViewById(R.id.nomeClube);
        btnCadastrar = findViewById(R.id.cadastrar);
       // progressBar = findViewById(R.id.progressBar);


        txtNome.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    txtNome.setHint("");
                    txtEmail.setHint("E-mail");
                    txtSenha.setHint("Senha");
                    txtNomeClube.setHint("Nome do clube");
                }
                return false;
            }
        });

        txtEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    txtNome.setHint("Nome completo");
                    txtEmail.setHint("");
                    txtSenha.setHint("Senha");
                    txtNomeClube.setHint("Nome do clube");
                }
                return false;
            }
        });

        txtSenha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    txtNome.setHint("Nome completo");
                    txtEmail.setHint("E-mail");
                    txtSenha.setHint("");
                    txtNomeClube.setHint("Nome do clube");
                }
                return false;
            }
        });

        txtNomeClube.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    txtNome.setHint("Nome completo");
                    txtEmail.setHint("E-mail");
                    txtSenha.setHint("Senha");
                    txtNomeClube.setHint("");
                }
                return false;
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificaCamposVazios();
                if (CheckEditText) {
                    UserLogin();
                    sendEmail();
                } else {
                    Toast.makeText(Cadastro.this, "Favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void UserLogin() {
        final String nome, email, senha, nomeClube;
        nome = txtNome.getText().toString();
        email = txtEmail.getText().toString();
        senha = txtSenha.getText().toString();
        nomeClube = txtNomeClube.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(Cadastro.this);

        String url = "http://192.168.15.17/cadastro.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(final String response) {
                alertDialog = new AlertDialog.Builder(Cadastro.this);
                alertDialog.setMessage(response);

                if (response.equalsIgnoreCase("Cadastrado com sucesso")) {
                    txtNome.setText("");
                    txtEmail.setText("");
                    txtSenha.setText("");
                    txtNomeClube.setText("");
                }
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


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
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nome", nome);
                params.put("email", email);
                params.put("senha", senha);
                params.put("nomeClube", nomeClube);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void clickBtnJaTenho(View view) {
        Intent it;
        it = new Intent(Cadastro.this, Login.class);
        startActivity(it);
    }

    public void VerificaCamposVazios() {
        nomeText = txtNome.getText().toString().trim();
        emailText = txtEmail.getText().toString().trim();
        senhaText = txtSenha.getText().toString().trim();
        nomeClubeText = txtNomeClube.getText().toString().trim();
        CheckEditText = !TextUtils.isEmpty(nomeText) && !TextUtils.isEmpty(emailText)
                && !TextUtils.isEmpty(senhaText) && !TextUtils.isEmpty(nomeClubeText);

    }

}