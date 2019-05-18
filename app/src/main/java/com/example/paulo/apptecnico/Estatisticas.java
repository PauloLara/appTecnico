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
    Spinner spequipeOuJogador;
    Button btnverifica, btnSair;
    ArrayAdapter<String> adapter;
    TextView txtGuarda, txtReservaTorn, txtReservaJog, txtcabecalho00, txtcabecalho01, txttitulo00, txttitulo01, txttitulo02,
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

   // PieChart pieChart;
    BarChart mBarChart;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);
        titulo = findViewById(R.id.txtTitulo);
        selecione = findViewById(R.id.txtSelecione);
        select = findViewById(R.id.txtSelect);
        quem = findViewById(R.id.radio_group_quem);
        jogos = findViewById(R.id.radio_group_jogos);
        rdjogador = findViewById(R.id.radio_jogador);
        rdequipe = findViewById(R.id.radio_equipe);
        rdultimo = findViewById(R.id.radio_ultimo);
        rdtres = findViewById(R.id.radio_tres);
        rdcinco = findViewById(R.id.radio_cinco);
        rddez = findViewById(R.id.radio_dez);
        rdtodos = findViewById(R.id.radio_todos);
        spequipeOuJogador = findViewById(R.id.spinnerEquipeOuJogador);
        btnverifica = findViewById(R.id.verifica);
        txtGuarda = findViewById(R.id.txtGuarda);
        txtGuarda.setVisibility(View.INVISIBLE);
        txtReservaTorn = findViewById(R.id.txtReservaTorn);
        txtReservaTorn.setVisibility(View.INVISIBLE);
        txtReservaJog = findViewById(R.id.txtReservaJog);
        txtReservaJog.setVisibility(View.INVISIBLE);
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

        //pieChart = findViewById(R.id.myPiechart);
        //pieChart.setVisibility(View.INVISIBLE);
        mBarChart = findViewById(R.id.barchart);
        mBarChart.setVisibility(View.INVISIBLE);

        elementoSelecionado();
        jogosSelecionado();

        btnverifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String escolhaData = txtGuarda.getText().toString();
                String jogoTorn = txtReservaTorn.getText().toString();
                String jogoJog = txtReservaJog.getText().toString();
                if(rdequipe.isChecked()){
                    verificarTorn(jogoTorn, escolhaData);
                    txtcabecalho00.setText(rdequipe.getText().toString());
                }
                if(rdjogador.isChecked()){
                    verificarJog(jogoJog, escolhaData);
                    txtcabecalho00.setText(rdjogador.getText().toString());
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
                txtcabecalho01.setText(spequipeOuJogador.getSelectedItem().toString());

            }
        });
    }

    public void elementoSelecionado(){
        final String[] opcao = new String[1];
        quem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rdequipe.isChecked()) {
                    loadSpinnerTorneios(url_busca_torneios);
                    spequipeOuJogador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (parent.getItemAtPosition(position).equals("Escolha o torneio:")) {
                                //faz nada
                            }else{
                                opcao[0] = spequipeOuJogador.getItemAtPosition(spequipeOuJogador.getSelectedItemPosition()).toString();
                                txtReservaTorn.setText(opcao[0]);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if (rdjogador.isChecked()) {
                    loadSpinnerJogadores(url_busca_jogadores);
                    spequipeOuJogador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (parent.getItemAtPosition(position).equals("Escolha o jogador:")) {
                                //faz nada
                            }else {
                                opcao[0] = spequipeOuJogador.getItemAtPosition(spequipeOuJogador.getSelectedItemPosition()).toString();
                                txtReservaJog.setText(opcao[0]);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
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
                    txtGuarda.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogo");
                    txtlinha10.setText(opcao[0]+" jogo");
                    txtlinha20.setText(opcao[0]+" jogo");
                    txtlinha30.setText(opcao[0]+" jogo");
                }
                if (rdtres.isChecked()) {
                    opcao[0] = "tres";
                    txtGuarda.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogos");
                    txtlinha10.setText(opcao[0]+" jogos");
                    txtlinha20.setText(opcao[0]+" jogos");
                    txtlinha30.setText(opcao[0]+" jogos");
                }
                if (rdcinco.isChecked()) {
                    opcao[0] = "cinco";
                    txtGuarda.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogos");
                    txtlinha10.setText(opcao[0]+" jogos");
                    txtlinha20.setText(opcao[0]+" jogos");
                    txtlinha30.setText(opcao[0]+" jogos");
                }
                if (rddez.isChecked()) {
                    opcao[0] = "dez";
                    txtGuarda.setText(opcao[0]);
                    txtlinha00.setText(opcao[0]+" jogos");
                    txtlinha10.setText(opcao[0]+" jogos");
                    txtlinha20.setText(opcao[0]+" jogos");
                    txtlinha30.setText(opcao[0]+" jogos");
                }
                if (rdtodos.isChecked()) {
                    opcao[0] = "todos";
                    txtGuarda.setText(opcao[0]);
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

                        /*pieChart.addPieSlice(new PieModel("Passes errados", Float.parseFloat(passeErrado), Color.parseColor("#FE6DA8")));
                        pieChart.addPieSlice(new PieModel("Chutes a gol", Float.parseFloat(chuteAgol), Color.parseColor("#56B7F1")));
                        pieChart.addPieSlice(new PieModel("Bolas Perdidas", Float.parseFloat(perdida), Color.parseColor("#CDA67F")));
                        pieChart.addPieSlice(new PieModel("Interceptações", Float.parseFloat(interceptacao), Color.parseColor("#FED70E")));
                        pieChart.setVisibility(View.VISIBLE);
                        pieChart.startAnimation();*/

                        mBarChart.addBar(new BarModel("Passes errados", Float.parseFloat(passeErrado), Color.parseColor("#FE6DA8")));
                        mBarChart.addBar(new BarModel("Chutes a gol", Float.parseFloat(chuteAgol), Color.parseColor("#56B7F1")));
                        mBarChart.addBar(new BarModel("Bolas Perdidas", Float.parseFloat(perdida), Color.parseColor("#CDA67F")));
                        mBarChart.addBar(new BarModel("Interceptações", Float.parseFloat(interceptacao), Color.parseColor("#FED70E")));
                        mBarChart.setVisibility(View.VISIBLE);
                        mBarChart.startAnimation();
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
                    listaTorneios.add("Escolha o torneio:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        String nomeTorneio = jobj.getString("nomeTorneio");
                        listaTorneios.add(nomeTorneio);
                    }
                    adapter = new ArrayAdapter<String>(Estatisticas.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios);
                    spequipeOuJogador.setAdapter(adapter);
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
                    JSONArray jsonArray = jsonObject.getJSONArray("jogadores");
                    listaJogador.add("Escolha o jogador:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jobj = jsonArray.getJSONObject(i);
                        String nomeJogador = jobj.getString("nomeJogador");
                        listaJogador.add(nomeJogador);
                    }
                    adapter = new ArrayAdapter<String>(Estatisticas.this, android.R.layout.simple_spinner_dropdown_item, listaJogador);
                    spequipeOuJogador.setAdapter(adapter);
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

