package com.example.paulo.apptecnico;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CadastrarJogadores extends AppCompatActivity{
    private Spinner spinnerPosicoes;
    private List<String> nomesPosicoes = new ArrayList<String>();
    private String posicao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_jogadores);
        //Adicionando Nomes no ArrayList
        nomesPosicoes.add("Selecione a posição:");
        nomesPosicoes.add("goleiro");
        nomesPosicoes.add("fixo");
        nomesPosicoes.add("ala");
        nomesPosicoes.add("pivo");
        nomesPosicoes.add("fixo / ala");
        nomesPosicoes.add("ala / pivo");

        //Identifica o Spinner no layout
        spinnerPosicoes = (Spinner) findViewById(R.id.spinnerPosicao);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomesPosicoes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPosicoes.setAdapter(spinnerArrayAdapter);


        //Método do Spinner para capturar o item selecionado
        spinnerPosicoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                //pega nome pela posição
                posicao = parent.getItemAtPosition(position).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                Toast.makeText(CadastrarJogadores.this, "Nome Selecionado: " + posicao, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}

/*
public class TorneioCadastro extends AppCompatActivity {
    EditText editTextNomeTorneio;
    Button buttonCadastrarTorneio, buttonFutsal, buttonCampo;
    AlertDialog.Builder alertDialog;
    String nomeTorneio_text;
    Boolean CheckEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneio_cadastro);
        editTextNomeTorneio = findViewById(R.id.editTextTorneioCadastro);
        buttonCadastrarTorneio = findViewById(R.id.buttonTorneioCadastro);
        buttonFutsal = findViewById(R.id.btnConfigClube);
        buttonCampo = findViewById(R.id.btnConfigJogadores);
        int color = 0x8ffffff;
        buttonFutsal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color1 = 0x80fc731e; // 50% verde
                buttonFutsal.getBackground().setColorFilter(color1, PorterDuff.Mode.MULTIPLY);
                buttonCampo.getBackground().setColorFilter(null);
                editTextNomeTorneio.setHint("Nome do torneio de futsal");
            }
        });

        buttonCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color2 = 0x8000FF00; // 50% verde
                buttonCampo.getBackground().setColorFilter(color2, PorterDuff.Mode.MULTIPLY);
                buttonFutsal.getBackground().setColorFilter(null);
                editTextNomeTorneio.setHint("Nome do torneio de campo");
            }
        });

        editTextNomeTorneio.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    editTextNomeTorneio.setHint("");
                }
                return false;
            }
        });

        buttonCadastrarTorneio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaCamposVazios();
                if (CheckEditText) {
                    CadastraTorneio();
                } else {
                    Toast.makeText(TorneioCadastro.this, "Preencha o campo com nome do torneio.", Toast.LENGTH_LONG).show();
                }
            }
        });

       */
/* LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificaCamposVazios();
                if (CheckEditText) {
                    UserLogin();
                } else {
                    Toast.makeText(Login.this, "Favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });*//*

    }

   */
/* public void VerificaCamposVazios() {
        email_Text = Email.getText().toString().trim();
        senha_Text = Password.getText().toString().trim();
        CheckEditText = !TextUtils.isEmpty(email_Text) && !TextUtils.isEmpty(senha_Text);
    }*//*



    public void CadastraTorneio(){
        final String nomeTorneio;
        nomeTorneio = editTextNomeTorneio.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(TorneioCadastro.this);
        String url = "http://192.168.15.17/cadastro_torneio.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alertDialog = new AlertDialog.Builder(TorneioCadastro.this);
                //alertDialog.setTitle("Resposta do servidor:");
                alertDialog.setMessage("Resposta: " + response);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editTextNomeTorneio.setText("");
                    }
                });
                AlertDialog alertDialog2 = alertDialog.create();
                alertDialog2.show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TorneioCadastro.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeTorneio", nomeTorneio);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void VerificaCamposVazios() {
        nomeTorneio_text = editTextNomeTorneio.getText().toString().trim();
        CheckEditText = !TextUtils.isEmpty(nomeTorneio_text);
    }

}*/
