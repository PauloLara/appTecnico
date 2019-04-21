package com.example.paulo.apptecnico;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Calendar;
import android.widget.TextView;
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

public class Estatisticas extends AppCompatActivity {

    Spinner spinnerAtleta, spinnerTorneio, spinnerJogo;
    //TextView nome, passerr, chuteAgol, perdidas, interceptações;
    String nomeAtleta, nomeTorneio, dataJogo;
    Button btnVerificaEquipe;
    TextView tvGoleiro, tvFixo, tvAlaEsq, tvAlaDir, tvPivo, tvGoleiroRes, tvFixoRes, tvAlaEsqRes, tvAlaDirRes, tvPivoRes, tvGoleiroResRes, tvJogadorExtra;
    TextView textViewGoleiroAtleta, textAtletaGoleiro, textViewGoleiroPasseErrado, textViewGoleiroChuteAgol, textViewGoleiroPerdida, textViewGoleiroInterceptacao;
    TextView textViewFixoAtleta, textViewFixoPasseErrado, textViewFixoChuteAgol, textViewFixoPerdida, textViewFixoInterceptacao;
    TextView textViewAlaEsqAtleta, textViewAlaEsqPasseErrado, textViewAlaEsqChuteAgol, textViewAlaEsqPerdida, textViewAlaEsqInterceptacao;
    TextView textViewAlaDirAtleta, textViewAlaDirPasseErrado, textViewAlaDirChuteAgol, textViewAlaDirPerdida, textViewAlaDirInterceptacao;
    TextView textViewPivoAtleta, textViewPivoPasseErrado, textViewPivoChuteAgol, textViewPivoPerdida, textViewPivoInterceptacao;

    String URL_Spinners = "http://192.168.15.17/estatisticas/buscaPassesErrados.php";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        spinnerAtleta = findViewById(R.id.spinnerJogador);
        spinnerTorneio = findViewById(R.id.spinnerTorneio);
        spinnerJogo = findViewById(R.id.spinnerJogo);

        btnVerificaEquipe = findViewById(R.id.btnVerificaEquipe);
        textViewGoleiroAtleta = findViewById(R.id.fieldGoleiro);

        textAtletaGoleiro = findViewById(R.id.atleta_goleiro);
        textViewGoleiroPasseErrado = findViewById(R.id.passer_goleiro);
        textViewGoleiroChuteAgol = findViewById(R.id.chuteg_goleiro);
        textViewGoleiroPerdida = findViewById(R.id.perdida_goleiro);
        textViewGoleiroInterceptacao = findViewById(R.id.intercep_goleiro);

        textViewFixoAtleta = findViewById(R.id.atleta_fixo);
        textViewFixoPasseErrado = findViewById(R.id.passer_fixo);
        textViewFixoChuteAgol = findViewById(R.id.chuteg_fixo);
        textViewFixoPerdida = findViewById(R.id.perdida_fixo);
        textViewFixoInterceptacao = findViewById(R.id.intercep_fixo);

        textViewAlaEsqAtleta = findViewById(R.id.atleta_alaesq);
        textViewAlaEsqPasseErrado = findViewById(R.id.passer_alaesq);
        textViewAlaEsqChuteAgol = findViewById(R.id.chuteg_alaesq);
        textViewAlaEsqPerdida = findViewById(R.id.perdida_alaesq);
        textViewAlaEsqInterceptacao = findViewById(R.id.intercep_alaesq);

        textViewAlaDirAtleta = findViewById(R.id.atleta_aladir);
        textViewAlaDirPasseErrado = findViewById(R.id.passer_aladir);
        textViewAlaDirChuteAgol = findViewById(R.id.chuteg_aladir);
        textViewAlaDirPerdida = findViewById(R.id.perdida_aladir);
        textViewAlaDirInterceptacao = findViewById(R.id.intercep_aladir);

        textViewPivoAtleta = findViewById(R.id.atleta_pivo);
        textViewPivoPasseErrado = findViewById(R.id.passer_pivo);
        textViewPivoChuteAgol = findViewById(R.id.chuteg_pivo);
        textViewPivoPerdida = findViewById(R.id.perdida_pivo);
        textViewPivoInterceptacao = findViewById(R.id.intercep_pivo);

        tvGoleiro = findViewById(R.id.fieldGoleiro);
        tvFixo = findViewById(R.id.fieldFixo);
        tvAlaEsq = findViewById(R.id.fieldAlaEsq);
        tvAlaDir = findViewById(R.id.fieldAlaDir);
        tvPivo = findViewById(R.id.fieldPivo);
        tvGoleiroRes = findViewById(R.id.fieldGoleiroRes);
        tvFixoRes = findViewById(R.id.fieldFixoRes);
        tvAlaEsqRes = findViewById(R.id.fieldAlaEsqRes);
        tvAlaDirRes = findViewById(R.id.fieldAlaDirRes);
        tvPivoRes = findViewById(R.id.fieldPivoRes);
        tvGoleiroResRes = findViewById(R.id.fieldGoleiroResRes);
        tvJogadorExtra = findViewById(R.id.fieldJogadorExtra);

        loadSpinnerAtleta(URL_Spinners);
        spinnerAtleta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String atleta = spinnerAtleta.getItemAtPosition(spinnerAtleta.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        loadSpinnerTorneio(URL_Spinners);
        spinnerTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String torneio = spinnerTorneio.getItemAtPosition(spinnerTorneio.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loadSpinnerJogo(URL_Spinners);
        spinnerJogo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String jogo = spinnerJogo.getItemAtPosition(spinnerJogo.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //INÍCIO - AQUI POPULA O SPINNER ATLETA COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerAtleta(String url_spin_jogador) {
        final ArrayList<String> listaAtletas = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_spin_jogador, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    listaAtletas.add("Selecione o atleta:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        nomeAtleta = jsonObject1.getString("atleta");
                        listaAtletas.add(nomeAtleta);
                    }
                    spinnerAtleta.setAdapter(new ArrayAdapter<String>(Estatisticas.this, android.R.layout.simple_spinner_dropdown_item, listaAtletas));

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


    //INÍCIO - AQUI POPULA O TORNEIOS JOGADOR COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerTorneio(String url_spin_torneio) {
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_spin_torneio, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    listaTorneios.add("Selecione o torneio:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        nomeTorneio = jsonObject1.getString("nomeTorneio");
                        listaTorneios.add(nomeTorneio);
                    }
                    spinnerTorneio.setAdapter(new ArrayAdapter<String>(Estatisticas.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));

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

    //INÍCIO - AQUI POPULA O TORNEIOS JOGADOR COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerJogo(String url_spin_jogo) {
        final ArrayList<String> listaJogos = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_spin_jogo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    listaJogos.add("Selecione o jogo:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        dataJogo = jsonObject1.getString("dataJogo");
                        listaJogos.add(dataJogo);
                    }
                    spinnerJogo.setAdapter(new ArrayAdapter<String>(Estatisticas.this, android.R.layout.simple_spinner_dropdown_item, listaJogos));

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
