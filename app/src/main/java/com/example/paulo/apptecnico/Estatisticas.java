package com.example.paulo.apptecnico;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Estatisticas extends AppCompatActivity {

    TextView titulo, selecione, select;
    RadioGroup quem, jogos;
    RadioButton rdjogador, rdequipe, rdultimo, rdtres, rdcinco, rddez, rdtodos;
    Spinner spinnerJogador, spinnerEquipe;
    Button btnverifica, btnSair;
    ArrayAdapter<String> adapter;
    TextView txtTorneioOUJogador, txtRangeJogo, txtcabecalho00, txtcabecalho01, txttitulo00, txttitulo01, txttitulo02,
            txtlinha00, txtlinha01, txtlinha02, txtlinha10, txtlinha11, txtlinha12, txtlinha20, txtlinha21, txtlinha22, txtlinha30,
            txtlinha31, txtlinha32;
    String url_busca_jogadores = "http://192.168.15.17/busca_jogadores.php";
    String url_busca_torneios = "http://192.168.15.17/busca_torneios.php";
    String url_busca_torneiojog = "http://192.168.15.17/busca_torneiojog.php";
    String url_busca_jogadorjog = "http://192.168.15.17/busca_jogadorjog.php";

    //String url_busca_jogadores = "https://appscout.000webhostapp.com/appscout/busca_jogadores.php";
    //String url_busca_torneios = "https://appscout.000webhostapp.com/appscout/busca_torneios.php";
    //String url_busca_torneiojog = "https://appscout.000webhostapp.com/appscout/busca_torneiojog.php";
    //String url_busca_jogadorjog = "https://appscout.000webhostapp.com/appscout/busca_jogadorjog.php";

    BarChart mBarChart;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        titulo = findViewById(R.id.txtTitulo);
        selecione = findViewById(R.id.txtSelecione);
        select = findViewById(R.id.txtSelect);
        jogos = findViewById(R.id.radio_group_jogos);
        txtTorneioOUJogador = findViewById(R.id.txtTorneioOUJogador);
        txtRangeJogo = findViewById(R.id.txtRangeJogo);
        rdultimo = findViewById(R.id.radio_ultimo);
        rdtres = findViewById(R.id.radio_tres);
        rdcinco = findViewById(R.id.radio_cinco);
        rddez = findViewById(R.id.radio_dez);
        rdtodos = findViewById(R.id.radio_todos);
        spinnerJogador = findViewById(R.id.spinnerJogador);
        spinnerEquipe = findViewById(R.id.spinnerEquipe);

        btnverifica = findViewById(R.id.verifica);
        btnSair = findViewById(R.id.btnSair);

        txtcabecalho00 = findViewById(R.id.txtcabecalho00);
        txtcabecalho00.setVisibility(View.INVISIBLE);
        txtcabecalho01 = findViewById(R.id.txtcabecalho01);
        txtcabecalho01.setVisibility(View.INVISIBLE);
        txttitulo00 = findViewById(R.id.txttitulo00);
        txttitulo00.setVisibility(View.INVISIBLE);
        txttitulo01 = findViewById(R.id.txttitulo01);
        txttitulo01.setVisibility(View.INVISIBLE);
        txttitulo02 = findViewById(R.id.txttitulo02);
        txttitulo02.setVisibility(View.INVISIBLE);
        txtlinha00 = findViewById(R.id.txtlinha00);
        txtlinha00.setVisibility(View.INVISIBLE);
        txtlinha01 = findViewById(R.id.txtlinha01);
        txtlinha01.setVisibility(View.INVISIBLE);
        txtlinha02 = findViewById(R.id.txtlinha02);
        txtlinha02.setVisibility(View.INVISIBLE);
        txtlinha10 = findViewById(R.id.txtlinha10);
        txtlinha10.setVisibility(View.INVISIBLE);
        txtlinha11 = findViewById(R.id.txtlinha11);
        txtlinha11.setVisibility(View.INVISIBLE);
        txtlinha12 = findViewById(R.id.txtlinha12);
        txtlinha12.setVisibility(View.INVISIBLE);
        txtlinha20 = findViewById(R.id.txtlinha20);
        txtlinha20.setVisibility(View.INVISIBLE);
        txtlinha21 = findViewById(R.id.txtlinha21);
        txtlinha21.setVisibility(View.INVISIBLE);
        txtlinha22 = findViewById(R.id.txtlinha22);
        txtlinha22.setVisibility(View.INVISIBLE);
        txtlinha30 = findViewById(R.id.txtlinha30);
        txtlinha30.setVisibility(View.INVISIBLE);
        txtlinha31 = findViewById(R.id.txtlinha31);
        txtlinha31.setVisibility(View.INVISIBLE);
        txtlinha32 = findViewById(R.id.txtlinha32);
        txtlinha32.setVisibility(View.INVISIBLE);
        mBarChart = findViewById(R.id.barchart);
        mBarChart.setVisibility(View.INVISIBLE);

        //elementoSelecionado();

        loadSpinnerTorneios(url_busca_torneios);
        spinnerEquipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Torneio:")) {
                    spinnerJogador.setEnabled(true);
                }else{
                    spinnerJogador.setEnabled(false);
                    String stTorneioSelecionado = spinnerEquipe.getSelectedItem().toString();
                    txtTorneioOUJogador.setText(stTorneioSelecionado);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        loadSpinnerJogadores(url_busca_jogadores);
        spinnerJogador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Jogador:")) {
                    spinnerEquipe.setEnabled(true);
                }else {
                    spinnerEquipe.setEnabled(false);
                    String stJogadorSelecionado = spinnerJogador.getSelectedItem().toString();
                    txtTorneioOUJogador.setText(stJogadorSelecionado);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        jogosSelecionado();

        btnverifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String escolhaTorneioOuJogador = txtTorneioOUJogador.getText().toString();
                String escolheRangeJogo = txtRangeJogo.getText().toString();

                if(escolheRangeJogo.equals(null)){
                    escolheRangeJogo = "todos";
                    Toast.makeText(Estatisticas.this, "Escolha o que deseja analisar", Toast.LENGTH_LONG).show();
                }

                if(!spinnerEquipe.getSelectedItem().toString().equals("Torneio:")){
                    verificarTorn(escolhaTorneioOuJogador, escolheRangeJogo);
                }
                if(!spinnerJogador.getSelectedItem().toString().equals("Jogador:")){
                    verificarJog(escolhaTorneioOuJogador, escolheRangeJogo);
                }

                txtcabecalho00.setVisibility(View.VISIBLE);
                txtcabecalho01.setVisibility(View.VISIBLE);
                txttitulo00.setVisibility(View.VISIBLE);
                txttitulo01.setVisibility(View.VISIBLE);
                txttitulo02.setVisibility(View.VISIBLE);
                txtlinha00.setVisibility(View.VISIBLE);
                txtlinha01.setVisibility(View.VISIBLE);
                txtlinha02.setVisibility(View.VISIBLE);
                txtlinha10.setVisibility(View.VISIBLE);
                txtlinha11.setVisibility(View.VISIBLE);
                txtlinha12.setVisibility(View.VISIBLE);
                txtlinha20.setVisibility(View.VISIBLE);
                txtlinha21.setVisibility(View.VISIBLE);
                txtlinha22.setVisibility(View.VISIBLE);
                txtlinha30.setVisibility(View.VISIBLE);
                txtlinha31.setVisibility(View.VISIBLE);
                txtlinha32.setVisibility(View.VISIBLE);
                //txtcabecalho01.setText(spequipeOuJogador.getSelectedItem().toString());

                txtlinha02.setBackgroundColor(Color.parseColor("#d2ff4d"));
                txtlinha12.setBackgroundColor(Color.parseColor("#56B7F1"));
                txtlinha22.setBackgroundColor(Color.parseColor("#CDA67F"));
                txtlinha32.setBackgroundColor(Color.parseColor("#FED70E"));
            }
        });
    }


    public void jogosSelecionado(){
        final String[] opcao = new String[1];
        jogos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdultimo.isChecked()) {
                    opcao[0] = "ultimo";
                    txtRangeJogo.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogo");
                    txtlinha10.setText(opcao[0]+" jogo");
                    txtlinha20.setText(opcao[0]+" jogo");
                    txtlinha30.setText(opcao[0]+" jogo");
                }
                if (rdtres.isChecked()) {
                    opcao[0] = "tres";
                    txtRangeJogo.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogos");
                    txtlinha10.setText(opcao[0]+" jogos");
                    txtlinha20.setText(opcao[0]+" jogos");
                    txtlinha30.setText(opcao[0]+" jogos");
                }
                if (rdcinco.isChecked()) {
                    opcao[0] = "cinco";
                    txtRangeJogo.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogos");
                    txtlinha10.setText(opcao[0]+" jogos");
                    txtlinha20.setText(opcao[0]+" jogos");
                    txtlinha30.setText(opcao[0]+" jogos");
                }
                if (rddez.isChecked()) {
                    opcao[0] = "dez";
                    txtRangeJogo.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogos");
                    txtlinha10.setText(opcao[0]+" jogos");
                    txtlinha20.setText(opcao[0]+" jogos");
                    txtlinha30.setText(opcao[0]+" jogos");
                }
                if (rdtodos.isChecked()) {
                    opcao[0] = "todos";
                    txtRangeJogo.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogos");
                    txtlinha10.setText(opcao[0]+" jogos");
                    txtlinha20.setText(opcao[0]+" jogos");
                    txtlinha30.setText(opcao[0]+" jogos");
                }
            }
        });
    }

    public void verificarJog(final String escolhaJog, final String jogoJog){
        //pieChart.clearChart();
        mBarChart.clearChart();
        RequestQueue queue = Volley.newRequestQueue(Estatisticas.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_busca_jogadorjog, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Estatisticas.this, response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("media_jogador");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        String passeErrado = jobj.getString("passeErrado");
                        String chuteAgol = jobj.getString("chuteAgol");
                        String perdida = jobj.getString("perdida");
                        String interceptacao = jobj.getString("interceptacao");
                        txtlinha02.setText(passeErrado);
                        txtlinha12.setText(chuteAgol);
                        txtlinha22.setText(perdida);
                        txtlinha32.setText(interceptacao);
                        carregaBarChart(passeErrado, chuteAgol, perdida, interceptacao);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Estatisticas.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("escolha", escolhaJog);
                params.put("jogo", jogoJog);
                return params;
            }
        };
        queue.add(stringRequest);
    }
    //CARREGAR GRÁFICOS -----------------------------------------------------------------------------------------------------------
    public void carregaBarChart(String passeErrado, String chuteAgol, String perdida, String interceptacao){
        mBarChart.addBar(new BarModel("Passes", Float.parseFloat(passeErrado), Color.parseColor("#d2ff4d")));
        mBarChart.addBar(new BarModel("Chut Gol", Float.parseFloat(chuteAgol), Color.parseColor("#56B7F1")));
        mBarChart.addBar(new BarModel("Perdidas", Float.parseFloat(perdida), Color.parseColor("#CDA67F")));
        mBarChart.addBar(new BarModel("Intercept", Float.parseFloat(interceptacao), Color.parseColor("#FED70E")));
        mBarChart.setVisibility(View.VISIBLE);
        mBarChart.startAnimation();
    }
//FIM CARREGAR GRÁFICOS -----------------------------------------------------------------------------------------------------------

    public void verificarTorn(final String escolhaTorn, final String jogoTorn){
        RequestQueue queue = Volley.newRequestQueue(Estatisticas.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_busca_torneiojog, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Estatisticas.this, response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("media_torneio");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        String passeErrado = jobj.getString("passeErrado");
                        String chuteAgol = jobj.getString("chuteAgol");
                        String perdida = jobj.getString("perdida");
                        String interceptacao = jobj.getString("interceptacao");
                        txtlinha02.setText(passeErrado);
                        txtlinha12.setText(chuteAgol);
                        txtlinha22.setText(perdida);
                        txtlinha32.setText(interceptacao);
                        carregaBarChart(passeErrado, chuteAgol, perdida, interceptacao);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                //Toast.makeText(Estatisticas.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("escolha", escolhaTorn);
                params.put("jogo", jogoTorn);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void loadSpinnerTorneios(String urlSpin) {
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("torneios");
                    listaTorneios.add("Torneio:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        String nomeTorneio = jobj.getString("nomeTorneio");
                        listaTorneios.add(nomeTorneio);
                    }
                    adapter = new ArrayAdapter<String>(Estatisticas.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios);
                    //spequipeOuJogador.setAdapter(adapter);
                    spinnerEquipe.setAdapter(adapter);
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

    private void loadSpinnerJogadores(String urlSpin) {
        final ArrayList<String> listaJogador = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    listaJogador.add("Jogador:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        String nomeJogador = jobj.getString("nomeJogador");
                        listaJogador.add(nomeJogador);
                    }
                    adapter = new ArrayAdapter<>(Estatisticas.this, android.R.layout.simple_spinner_dropdown_item, listaJogador);
                    //spequipeOuJogador.setAdapter(adapter);
                    spinnerJogador.setAdapter(adapter);
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

    public void clickBtnSair(View view){
        Intent it;
        it = new Intent(Estatisticas.this, Inicial.class);
        startActivity(it);
    }
}
