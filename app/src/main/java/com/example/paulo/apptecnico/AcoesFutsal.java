package com.example.paulo.apptecnico;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AcoesFutsal extends Activity {

    Button btnVerificaEquipe;
    AlertDialog.Builder alertDialog;
    EditText editTextGoleiro;
    Spinner spinner;
    String URL = "http://192.168.15.17/busca_torneios.php";
    ArrayList<String> torneioName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_futsal);
        editTextGoleiro = findViewById(R.id.txtGoleiro);
        torneioName = new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.spinnerTorneio);
        btnVerificaEquipe = findViewById(R.id.btnVerificaEquipe);
        loadSpinnerData(URL);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String torneio = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                //Toast.makeText(getApplicationContext(), torneio, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnVerificaEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String stnomeTorneio = (String) spinner.getSelectedItem();
                Toast.makeText(getApplicationContext(), stnomeTorneio, Toast.LENGTH_LONG).show();
                //stnomeGoleiro = editTextGoleiro.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(AcoesFutsal.this);
                String url = "http://192.168.15.17/busca_dados_equipe.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog = new AlertDialog.Builder(AcoesFutsal.this);
                        //alertDialog.setTitle("Resposta do servidor:");
                        alertDialog.setMessage("Resposta: " + response);
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //editTextGoleiro.setText("");
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog2 = alertDialog.create();
                        alertDialog2.show();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(AcoesFutsal.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nomeTorneio", stnomeTorneio);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }


    /*Spinner spinner;
    String URL = "http://192.168.15.17/busca_torneios.php";
    ArrayList<String> CountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_futsal);CountryName = new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.spinnerTorneio);
        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String country = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(), country, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }*/


    private void loadSpinnerData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("torneios");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String torneios = jsonObject1.getString("nomeTorneio");
                        torneioName.add(torneios);
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(AcoesFutsal.this, android.R.layout.simple_spinner_dropdown_item, torneioName));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(stringRequest);
    }
}