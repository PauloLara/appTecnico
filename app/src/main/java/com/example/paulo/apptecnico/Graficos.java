package com.example.paulo.apptecnico;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.PieModel;
import org.eazegraph.lib.models.StackedBarModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graficos extends Activity {
    WebView myWebView;
    Spinner spinnerAcoes, spinnerJogadores, spinnerTorneios, spinnerJogos;

    String url_busca_acoes =  "http://192.168.15.17/busca_acoes_pizza.php";
    String url_busca_jogadores =  "http://192.168.15.17/busca_jogadores.php";
    String url_busca_torneios =  "http://192.168.15.17/busca_torneios.php";
    String url_busca_jogo =  "http://192.168.15.17/select_jogo_por_torneio_escolhido.php";
    String url_monta_graficos =  "http://192.168.15.17/monta_graficos.php";
    String url_acao_jogador =  "http://192.168.15.17/acao_jogador.php";
    String url_busca_ac_jog_torn =  "http://192.168.15.17/ac_jog_torn.php";

    String url_todas_acoes =  "http://192.168.15.17/pizza_todas_acoes.php";
    String url_passes_errados =  "http://192.168.15.17/busca_passes.php";
    String url_chutes_gol =  "http://192.168.15.17/busca_chutes.php";
    String url_perdidas =  "http://192.168.15.17/busca_perdidas.php";
    String url_interceptacoes = "http://192.168.15.17/busca_interceptacoes.php";

    //String url_busca_acoes = "https://appscout.000webhostapp.com/appscout/busca_acoes.php";
   // String url_busca_jogadores = "https://appscout.000webhostapp.com/appscout/busca_jogadores.php"; //popula spinner jogadores
   // String url_busca_torneios = "https://appscout.000webhostapp.com/appscout/busca_torneios.php";
   // String url_busca_jogo = "https://appscout.000webhostapp.com/appscout/select_jogo_por_torneio_escolhido.php";
   // String url_monta_graficos = "https://appscout.000webhostapp.com/appscout/monta_graficos.php";
   /// String url_acao_jogador = "https://appscout.000webhostapp.com/appscout/acao_jogador.php";
   // String url_busca_ac_jog_torn = "https://appscout.000webhostapp.com/appscout/ac_jog_torn.php";
   // String url_todas_acoes = "https://appscout.000webhostapp.com/appscout/pizza_todas_acoes.php";
   // String url_passes_errados = "https://appscout.000webhostapp.com/appscout/pizza_passes_errados.php";
   // String url_chutes_gol = "https://appscout.000webhostapp.com/appscout/pizza_chutes_gol.php";
   // String url_perdidas = "https://appscout.000webhostapp.com/appscout/pizza_bolas_perdidas.php";
   // String url_interceptacoes = "https://appscout.000webhostapp.com/appscout/pizza_interceptacoes.php";


    ArrayAdapter<String> adapter;
    TextView tvdados1, tvdados2, tvdados3, tvdados4, tvdados01, tvdados02, tvdados03, tvdados04, tvRes, tvResTxt, tv1, tv2, tv3, tv4;
    Button btnVerificar, btnLimpar, btnAct, btnJog, btnTorn, btnDat, btnSair;
    PieChart pieChart;
    BarChart mBarChart;
    StackedBarChart mStackedBarChart;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);

        spinnerAcoes = findViewById(R.id.spinnerAcoes);
        spinnerJogadores = findViewById(R.id.spinnerJogadores);
        spinnerTorneios = findViewById(R.id.spinnerTorneios);
        spinnerJogos = findViewById(R.id.spinnerJogos);
        tvdados1 = findViewById(R.id.tvdados1);
        tvdados1.setText("");
        tvdados1.setVisibility(View.INVISIBLE);
        tvdados2 = findViewById(R.id.tvdados2);
        tvdados2.setText("");
        tvdados2.setVisibility(View.INVISIBLE);
        tvdados3 = findViewById(R.id.tvdados3);
        tvdados3.setText("");
        tvdados3.setVisibility(View.INVISIBLE);
        tvdados4 = findViewById(R.id.tvdados4);
        tvdados4.setText("");
        tvdados4.setVisibility(View.INVISIBLE);
        tvdados01 = findViewById(R.id.tvdados01);
        tvdados01.setVisibility(View.INVISIBLE);
        tvdados02 = findViewById(R.id.tvdados02);
        tvdados02.setVisibility(View.INVISIBLE);
        tvdados03 = findViewById(R.id.tvdados03);
        tvdados03.setVisibility(View.INVISIBLE);
        tvdados04 = findViewById(R.id.tvdados04);
        tvdados04.setVisibility(View.INVISIBLE);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setVisibility(View.INVISIBLE);
        tvResTxt = findViewById(R.id.tvResTxt);
        tvResTxt.setVisibility(View.INVISIBLE);
        btnVerificar = findViewById(R.id.btnVerificar);
        btnSair = findViewById(R.id.btnSair);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnAct = findViewById(R.id.btnAct);
        btnJog = findViewById(R.id.btnJog);
        btnTorn = findViewById(R.id.btnTorn);
        btnDat = findViewById(R.id.btnDat);
        btnAct.setVisibility(View.INVISIBLE);
        btnJog.setVisibility(View.INVISIBLE);
        btnTorn.setVisibility(View.INVISIBLE);
        btnDat.setVisibility(View.INVISIBLE);

        pieChart = findViewById(R.id.myPiechart);
        pieChart.setVisibility(View.INVISIBLE);
        mBarChart = findViewById(R.id.barchart);
        mBarChart.setVisibility(View.INVISIBLE);
        mStackedBarChart = findViewById(R.id.stackedbarchart);
        mStackedBarChart.setVisibility(View.INVISIBLE);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv1.setText("");
        tv2.setText("");
        tv3.setText("");
        tv4.setText("");

        myWebView = findViewById(R.id.webview);

        List<String> listaAcoes = new ArrayList<String>();
        listaAcoes.add("Escolha a ação:");
        listaAcoes.add("Todos os dados");
        listaAcoes.add("Passes errados");
        listaAcoes.add("Chutes a gol");
        listaAcoes.add("Bolas perdidas");
        listaAcoes.add("Interceptações");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Graficos.this, android.R.layout.simple_spinner_dropdown_item, listaAcoes);
        spinnerAcoes.setAdapter(dataAdapter);

        spinnerAcoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Escolha a ação:")) {
                    //faz nada
                } else {
                    String acao = spinnerAcoes.getItemAtPosition(spinnerAcoes.getSelectedItemPosition()).toString();
                    tvdados1.setText(acao);
                    tvdados01.setVisibility(View.VISIBLE);
                    tvdados1.setVisibility(View.VISIBLE);
                    btnAct.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        loadSpinnerJogadores(url_busca_jogadores);
        spinnerJogadores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Escolha o jogador:")) {
                    //faz nada
                } else {
                    String jogador = spinnerJogadores.getItemAtPosition(spinnerJogadores.getSelectedItemPosition()).toString();
                    //escolherJogador(acao, jogador);
                    tvdados2.setText(jogador);
                    tvdados02.setVisibility(View.VISIBLE);
                    tvdados2.setVisibility(View.VISIBLE);
                    btnJog.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadSpinnerTorneios(url_busca_torneios);
        spinnerTorneios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Escolha o torneio:")) {
                    //faz nada
                } else {
                    String torneio = spinnerTorneios.getItemAtPosition(spinnerTorneios.getSelectedItemPosition()).toString();
                    tvdados3.setText(torneio);
                    tvdados03.setVisibility(View.VISIBLE);
                    tvdados3.setVisibility(View.VISIBLE);
                    btnTorn.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadSpinnerJogos(url_busca_jogo);
        spinnerJogos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Selecione o jogo:")) {
                    //faz nada
                } else {
                    String jogo = spinnerJogos.getItemAtPosition(spinnerJogos.getSelectedItemPosition()).toString();
                    tvdados4.setText(jogo);
                    tvdados04.setVisibility(View.VISIBLE);
                    tvdados4.setVisibility(View.VISIBLE);
                    btnDat.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((tvdados1.getText().toString()!="")&&
                  ((tvdados2.getText().toString()=="") && (tvdados3.getText().toString()=="") && (tvdados4.getText().toString()==""))){
                    escolherAcao(tvdados1.getText().toString());
                    carregaWebViewsAcoes();
                }
                else if(((tvdados1.getText().toString()!="")&&(tvdados2.getText().toString()!=""))&&
                        ((tvdados3.getText().toString()=="")&&(tvdados4.getText().toString()==""))){
                    escolherAcaoJogador(tvdados1.getText().toString(), tvdados2.getText().toString());
                }
                else if(((tvdados1.getText().toString()!="")&&(tvdados2.getText().toString()!="")&&(tvdados3.getText().toString()!="")) &&
                    (tvdados4.getText().toString()=="")){
                    escolherAcaoJogadorTorneio(tvdados1.getText().toString(), tvdados2.getText().toString(), tvdados3.getText().toString());
                }
                else if(((tvdados1.getText().toString()!="") && (tvdados2.getText().toString()!="")&&
                        (tvdados3.getText().toString()!="") && (tvdados4.getText().toString()!=""))){
                    escolherTodos(tvdados1.getText().toString(), tvdados2.getText().toString(), tvdados3.getText().toString(), tvdados4.getText().toString());
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
            }
        });

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados1.setText("");
                tvdados01.setVisibility(View.INVISIBLE);
                tvdados1.setVisibility(View.INVISIBLE);
                btnAct.setVisibility(View.INVISIBLE);
                spinnerAcoes.setSelection(0);
            }
        });
        btnJog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados2.setText("");
                tvdados02.setVisibility(View.INVISIBLE);
                tvdados2.setVisibility(View.INVISIBLE);
                btnJog.setVisibility(View.INVISIBLE);
                spinnerJogadores.setSelection(0);
            }
        });
        btnTorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados3.setText("");
                tvdados03.setVisibility(View.INVISIBLE);
                tvdados3.setVisibility(View.INVISIBLE);
                btnTorn.setVisibility(View.INVISIBLE);
                spinnerTorneios.setSelection(0);
            }
        });
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados4.setText("");
                tvdados04.setVisibility(View.INVISIBLE);
                tvdados4.setVisibility(View.INVISIBLE);
                btnDat.setVisibility(View.INVISIBLE);
                spinnerJogos.setSelection(0);
            }
        });
    }
    ///************************************** FIM DO ONCREATE *********************************************************************

    public void limpaCampos(){
        tvdados01.setVisibility(View.INVISIBLE);
        tvdados1.setVisibility(View.INVISIBLE);
        tvdados1.setText(null);

        tvdados02.setVisibility(View.INVISIBLE);
        tvdados2.setVisibility(View.INVISIBLE);
        tvdados2.setText(null);

        tvdados03.setVisibility(View.INVISIBLE);
        tvdados3.setVisibility(View.INVISIBLE);
        tvdados3.setText(null);

        tvdados04.setVisibility(View.INVISIBLE);
        tvdados4.setVisibility(View.INVISIBLE);
        tvdados4.setText(null);

        btnAct.setVisibility(View.INVISIBLE);
        btnJog.setVisibility(View.INVISIBLE);
        btnTorn.setVisibility(View.INVISIBLE);
        btnDat.setVisibility(View.INVISIBLE);

        spinnerAcoes.setSelection(0);
        spinnerJogadores.setSelection(0);
        spinnerTorneios.setSelection(0);
        spinnerJogos.setSelection(0);

        pieChart.clearChart();
        pieChart.setVisibility(View.INVISIBLE);
        mStackedBarChart.clearChart();
        mStackedBarChart.setVisibility(View.INVISIBLE);
        myWebView.clearView();
    }

    public void carregaBarChart(String passeErrado, String chuteAgol, String perdida, String interceptacao){
        mBarChart.addBar(new BarModel("Passes", Float.parseFloat(passeErrado), Color.parseColor("#d2ff4d")));
        mBarChart.addBar(new BarModel("Chut Gol", Float.parseFloat(chuteAgol), Color.parseColor("#56B7F1")));
        mBarChart.addBar(new BarModel("Perdidas", Float.parseFloat(perdida), Color.parseColor("#CDA67F")));
        mBarChart.addBar(new BarModel("Intercept", Float.parseFloat(interceptacao), Color.parseColor("#FED70E")));
        mBarChart.setVisibility(View.VISIBLE);
        mBarChart.startAnimation();
    }

    public void carregaPieChart(String passeErrado, String chuteAgol, String perdida, String interceptacao){
        pieChart.addPieSlice(new PieModel("Passes errados", Integer.parseInt(passeErrado), Color.parseColor("#FE6DA8")));
        pieChart.addPieSlice(new PieModel("Chutes a gol", Integer.parseInt(chuteAgol), Color.parseColor("#56B7F1")));
        pieChart.addPieSlice(new PieModel("Bolas Perdidas", Integer.parseInt(perdida), Color.parseColor("#CDA67F")));
        pieChart.addPieSlice(new PieModel("Interceptações", Integer.parseInt(interceptacao), Color.parseColor("#FED70E")));
        pieChart.setVisibility(View.VISIBLE);
        pieChart.startAnimation();
    }

    //COM QUATRO PARÂMETROS 4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
    public void escolherTodos(final String nomeAcao, final String nomeJogador, final String nomeTorneio, final String dataJogo){
        mBarChart.clearChart();
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_monta_graficos, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(spinnerAcoes.getSelectedItem().toString().equals("Todos os dados")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";

                            carregaPieChart(passeErrado, chuteAgol, perdida, interceptacao);
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Passes errados")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            pieChart.addPieSlice(new PieModel("Passes errados", Integer.parseInt(passeErrado), Color.parseColor("#FE6DA8")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Chutes a gol")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            pieChart.addPieSlice(new PieModel("Chutes a gol", Integer.parseInt(chuteAgol), Color.parseColor("#56B7F1")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Bolas perdidas")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            pieChart.addPieSlice(new PieModel("Bolas Perdidas", Integer.parseInt(perdida), Color.parseColor("#CDA67F")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Interceptações")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";
                            pieChart.addPieSlice(new PieModel("Interceptações", Integer.parseInt(interceptacao), Color.parseColor("#FED70E")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeAcao", nomeAcao);
                params.put("nomeJogador", nomeJogador);
                params.put("nomeTorneio", nomeTorneio);
                params.put("dataJogo", dataJogo);
                return params;
            }
        };
        queue.add(stringRequest);
    }


    //COM TRÊS PARÂMETROS  33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333
    String passeErrado, chuteAgol, perdida, interceptacao;
    public void escolherAcaoJogadorTorneio(final String nomeAcao, final String nomeJogador, final String nomeTorneio){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_busca_ac_jog_torn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(spinnerAcoes.getSelectedItem().toString().equals("Todos os dados")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";
                            carregaPieChart(passeErrado, chuteAgol, perdida, interceptacao);
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Passes errados")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            pieChart.addPieSlice(new PieModel("Passes errados", Integer.parseInt(passeErrado), Color.parseColor("#FE6DA8")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Chutes a gol")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            pieChart.addPieSlice(new PieModel("Chutes a gol", Integer.parseInt(chuteAgol), Color.parseColor("#56B7F1")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Bolas perdidas")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            pieChart.addPieSlice(new PieModel("Bolas Perdidas", Integer.parseInt(perdida), Color.parseColor("#CDA67F")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Interceptações")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";
                            pieChart.addPieSlice(new PieModel("Interceptações", Integer.parseInt(interceptacao), Color.parseColor("#FED70E")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeAcao", nomeAcao);
                params.put("nomeJogador", nomeJogador);
                params.put("nomeTorneio", nomeTorneio);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    //COM DOIS PARÂMETROS 222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
    public void escolherAcaoJogador(final String nomeAcao, final String nomeJogador){
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_acao_jogador, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(spinnerAcoes.getSelectedItem().toString().equals("Todos os dados")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";
                            carregaPieChart(passeErrado, chuteAgol, perdida, interceptacao);
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Passes errados")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            pieChart.addPieSlice(new PieModel("Passes errados",
                                                    Integer.parseInt(passeErrado),
                                                    Color.parseColor("#FE6DA8")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Chutes a gol")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            pieChart.addPieSlice(new PieModel("Chutes a gol", Integer.parseInt(chuteAgol), Color.parseColor("#56B7F1")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Bolas perdidas")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            pieChart.addPieSlice(new PieModel("Bolas Perdidas", Integer.parseInt(perdida), Color.parseColor("#CDA67F")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Interceptações")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";
                            pieChart.addPieSlice(new PieModel("Interceptações", Integer.parseInt(interceptacao), Color.parseColor("#FED70E")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeAcao", nomeAcao);
                params.put("nomeJogador", nomeJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    //COM UM PARÂMETRO 11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    //SERÁ MONTADO O GRÁFICO COM A WEBVIEW
    public void escolherAcao(final String acao){
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_busca_acoes, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(spinnerAcoes.getSelectedItem().toString().equals("Todos os dados")){
                    pieChart.clearChart();
                    mStackedBarChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";
                            String atleta = jo.getString("atleta");
                            StackedBarModel s1 = new StackedBarModel(atleta);
                            s1.addBar(new BarModel(Math.round(Integer.parseInt(passeErrado)), 0xFF63CBB0));
                            s1.addBar(new BarModel(Math.round(Integer.parseInt(chuteAgol)), 0xFF56B7F1));
                            s1.addBar(new BarModel(Math.round(Integer.parseInt(perdida)), 0xFFCDA67F));
                            s1.addBar(new BarModel(Math.round(Integer.parseInt(interceptacao)), Color.parseColor("#990000")));
                            mStackedBarChart.addBar(s1);
                            mStackedBarChart.setVisibility(View.VISIBLE);
                            mStackedBarChart.startAnimation();

                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }

                if(spinnerAcoes.getSelectedItem().toString().equals("Passes errados")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if(passeErrado=="") passeErrado = "0";
                            String atleta = jo.getString("atleta");
                            pieChart.addPieSlice(new PieModel(atleta, Integer.parseInt(passeErrado), Color.parseColor("#990000")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Chutes a gol")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            chuteAgol = jo.getString("chuteAgol");
                            if(chuteAgol=="") chuteAgol = "0";
                            String atleta = jo.getString("atleta");
                            pieChart.addPieSlice(new PieModel(atleta, Integer.parseInt(chuteAgol), Color.parseColor("#56B7F1")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Bolas perdidas")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            perdida = jo.getString("perdida");
                            if(perdida=="") perdida = "0";
                            String atleta = jo.getString("atleta");
                            pieChart.addPieSlice(new PieModel(atleta, Integer.parseInt(perdida), Color.parseColor("#CDA67F")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(spinnerAcoes.getSelectedItem().toString().equals("Interceptações")){
                    pieChart.clearChart();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            interceptacao = jo.getString("interceptacao");
                            if(interceptacao=="") interceptacao = "0";
                            String atleta = jo.getString("atleta");
                            pieChart.addPieSlice(new PieModel(atleta, Integer.parseInt(interceptacao), Color.parseColor("#FED70E")));
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.startAnimation();
                        }
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("acao", acao);
                return params;
            }
        };
        queue.add(stringRequest);
    }

 //INÍCIO- AQUI POPULA O SPINNER DE JOGADORES COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerJogadores(String urlSpin) {
        final ArrayList<String> listaJogadores = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    listaJogadores.add("Escolha o jogador:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject goleiroj = jsonArray.getJSONObject(i);
                        String nomeJogador = goleiroj.getString("nomeJogador");
                        listaJogadores.add(nomeJogador);
                    }
                    adapter = new ArrayAdapter<String>(Graficos.this, android.R.layout.simple_spinner_dropdown_item, listaJogadores);
                    spinnerJogadores.setAdapter(adapter);
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

    //FIM - AQUI POPULA O SPINNER DE JOGADORES COM DADOS DO BANCO *******************************************************************

    //INÍCIO- AQUI POPULA O SPINNER DE TORNEIOS COM DADOS DO BANCO *******************************************************************
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
                        JSONObject goleiroj = jsonArray.getJSONObject(i);
                        String nomeTorneio = goleiroj.getString("nomeTorneio");
                        listaTorneios.add(nomeTorneio);
                    }
                    adapter = new ArrayAdapter<String>(Graficos.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios);
                    spinnerTorneios.setAdapter(adapter);
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
    // FIM - AQUI POPULA O SPINNER COM DADOS DO BANCO    *******************************************************************


    //INÍCIO- AQUI POPULA O SPINNER DE JOGOS COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerJogos(String urlSpin) {
        final ArrayList<String> listaJogos = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("jogos");
                    listaJogos.add("Selecione o jogo:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String nomeAdversario = jsonObject1.getString("nomeAdversario");
                        String dataJogo = jsonObject1.getString("dataJogo");
                        listaJogos.add(nomeAdversario+" - "+dataJogo);
                    }
                    adapter = new ArrayAdapter<String>(Graficos.this, android.R.layout.simple_spinner_dropdown_item, listaJogos);
                    spinnerJogos.setAdapter(adapter);
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
    // FIM DOS JOGOS *******************************************************************

     public void carregaWebViewsAcoes(){
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Todos os dados")){
             myWebView.loadUrl(url_todas_acoes);
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Passes Errados")){
             myWebView.loadUrl(url_passes_errados);
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Chutes a gol")){
             myWebView.loadUrl(url_chutes_gol);
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Bolas perdidas")){
             myWebView.loadUrl(url_perdidas);
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Interceptações")){
             myWebView.loadUrl(url_interceptacoes);
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
     }

    public void clickBtnSair(View view){
        Intent it;
        it = new Intent(Graficos.this, Inicial.class);
        startActivity(it);
    }
}
