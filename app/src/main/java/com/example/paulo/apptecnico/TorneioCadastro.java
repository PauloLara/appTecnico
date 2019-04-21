package com.example.paulo.apptecnico;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


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
        buttonFutsal = findViewById(R.id.btnFutsal);
        buttonCampo = findViewById(R.id.btnFutebol);
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
    }



    public void CadastraTorneio(){
        final String nomeTorneio;
        nomeTorneio = editTextNomeTorneio.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(TorneioCadastro.this);
        String url = "http://192.168.15.17/cadastroTorneio.php";
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

}
