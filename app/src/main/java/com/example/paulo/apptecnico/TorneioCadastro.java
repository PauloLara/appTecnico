package com.example.paulo.apptecnico;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
    Button buttonCadastrarTorneio;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneio_cadastro);
        editTextNomeTorneio = findViewById(R.id.editTextTorneioCadastro);
        buttonCadastrarTorneio = findViewById(R.id.buttonTorneioCadastro);

        buttonCadastrarTorneio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
    }

}
