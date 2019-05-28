package com.example.paulo.apptecnico;

import android.content.DialogInterface;
import android.content.Intent;
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


public class TorneioCadastro extends AppCompatActivity {
    EditText editTextNomeTorneio;
    Button buttonCadastrarTorneio, btnSair;//, buttonFutsal, buttonCampo;
    AlertDialog.Builder alertDialog;
    String nomeTorneio_text, nomeCategoria_text, categoria;
    Boolean CheckEditText;
    private Spinner spinnerCategoria;
    private List<String> nomesCategorias = new ArrayList<String>();
    String nomeCategoria;

    String url = "http://192.168.15.17/cadastro_torneio.php";

    //String url = "https://appscout.000webhostapp.com/appscout/cadastro_torneio.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneio_cadastro);
        editTextNomeTorneio = findViewById(R.id.editTextTorneioCadastro);
        buttonCadastrarTorneio = findViewById(R.id.buttonTorneioCadastro);
        btnSair = findViewById(R.id.btnSair);

        nomesCategorias.add("Selecione a categoria:");
        nomesCategorias.add("Sub 10");
        nomesCategorias.add("Sub 12");
        nomesCategorias.add("Sub 14");
        nomesCategorias.add("Sub 16");
        nomesCategorias.add("Sub 18");
        nomesCategorias.add("Sub 20");
        nomesCategorias.add("Principal");
        nomesCategorias.add("Veteranos");

        spinnerCategoria = findViewById(R.id.spinnerCategorias);
        ArrayAdapter<String> arrayAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomesCategorias);
        ArrayAdapter<String> spinnerArrayAdapt = arrayAdapt;
        spinnerArrayAdapt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCategoria.setAdapter(spinnerArrayAdapt);
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Selecione a categoria:")) {
                    //faz nada
                } else {
                    nomeCategoria = parent.getItemAtPosition(position).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        //nomeCategoria = spinnerCategoria.getSelectedItem().toString();
        RequestQueue queue = Volley.newRequestQueue(TorneioCadastro.this);



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
                        spinnerCategoria.setSelection(0);
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
                params.put("categoria", nomeCategoria);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void VerificaCamposVazios() {
        nomeTorneio_text = editTextNomeTorneio.getText().toString().trim();
        nomeCategoria_text = spinnerCategoria.getSelectedItem().toString().trim();
        CheckEditText = !TextUtils.isEmpty(nomeTorneio_text)&&!TextUtils.isEmpty(nomeTorneio_text);
    }

    public void clickBtnSair(View view){
        Intent it;
        it = new Intent(TorneioCadastro.this, Sistema.class);
        startActivity(it);
    }

}