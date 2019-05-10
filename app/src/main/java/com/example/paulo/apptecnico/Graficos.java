package com.example.paulo.apptecnico;

import android.app.Activity;
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
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Graficos extends Activity {
    WebView myWebView;
    Spinner spinnerAcoes, spinnerJogadores, spinnerTorneios, spinnerJogos;
    String url_busca_acoes = "http://192.168.15.17/busca_acoes.php";
    String url_busca_jogadores = "http://192.168.15.17/busca_jogadores.php";
    String url_monta_graf_jogador = "http://192.168.15.17/pizza_acoes_jogador.php";
    String url_busca_toneios = "http://192.168.15.17/busca_torneios.php";
    String url_busca_jogo = "http://192.168.15.17/select_jogo_por_torneio_escolhido.php";
    String url_monta_graficos = "http://192.168.15.17/monta_graficos.php";
    String url_acao_jogador = "http://192.168.15.17/acao_jogador.php";
    String url_busca_ac_jog_torn = "http://192.168.15.17/ac_jog_torn.php";
    ArrayAdapter<String> adapter;
    TextView tvdados1, tvdados2, tvdados3, tvdados4, tvdados01, tvdados02, tvdados03, tvdados04, tvRes, tvResTxt;
    Button btnVerificar, btnAct, btnJog, btnTorn, btnDat;
    PieChart pieChart;
    BarChart barChart;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);

        spinnerAcoes = findViewById(R.id.spinnerAcoes);
        spinnerJogadores = findViewById(R.id.spinnerJogadores);
        spinnerTorneios = findViewById(R.id.spinnerTorneios);
        spinnerJogos = findViewById(R.id.spinnerJogos);
        tvdados1 = findViewById(R.id.tvdados1);
        tvdados1.setVisibility(View.INVISIBLE);
        tvdados2 = findViewById(R.id.tvdados2);
        tvdados2.setVisibility(View.INVISIBLE);
        tvdados3 = findViewById(R.id.tvdados3);
        tvdados3.setVisibility(View.INVISIBLE);
        tvdados4 = findViewById(R.id.tvdados4);
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
        btnAct = findViewById(R.id.btnAct);
        btnJog = findViewById(R.id.btnJog);
        btnTorn = findViewById(R.id.btnTorn);
        btnDat = findViewById(R.id.btnDat);
        btnAct.setVisibility(View.INVISIBLE);
        btnJog.setVisibility(View.INVISIBLE);
        btnTorn.setVisibility(View.INVISIBLE);
        btnDat.setVisibility(View.INVISIBLE);
        pieChart = findViewById(R.id.piechart);
        pieChart.setVisibility(View.INVISIBLE);
        barChart = (BarChart) findViewById(R.id.barchart);
        barChart.setVisibility(View.INVISIBLE);
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
                    //escolherAcao(acao);
                    //carregaWebViewsAcoes();
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



        loadSpinnerTorneios(url_busca_toneios);
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
                String acao = tvdados1.getText().toString();
                String jogador = tvdados2.getText().toString();
                String torneio = tvdados3.getText().toString();
                String data = tvdados4.getText().toString();

                if((!acao.equals("")||!acao.equals(null))&&(jogador.equals("")||jogador.equals(null))
                    &&(torneio.equals("")||torneio.equals(null))&&(data.equals("")||data.equals(null))){
                    escolherAcao(acao);
                    carregaWebViewsAcoes();
                }
                else if((!acao.equals("")||!acao.equals(null))&&(!jogador.equals("")||!jogador.equals(null))
                        &&(torneio.equals("")||torneio.equals(null))&&(data.equals("")||data.equals(null))){
                    escolherAcaoJogador(acao, jogador);
                    montaGrafico();
                    //carregaWebViewsJogadores();
                }
                else{
                    escolherTodos(acao, jogador, torneio, data);
                }
            }
        });

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados1.setText("");
                tvdados01.setVisibility(View.INVISIBLE);
                tvdados1.setVisibility(View.INVISIBLE);
                btnAct.setVisibility(View.INVISIBLE);
            }
        });
        btnJog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados2.setText("");
                tvdados02.setVisibility(View.INVISIBLE);
                tvdados2.setVisibility(View.INVISIBLE);
                btnJog.setVisibility(View.INVISIBLE);
            }
        });
        btnTorn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados3.setText("");
                tvdados03.setVisibility(View.INVISIBLE);
                tvdados3.setVisibility(View.INVISIBLE);
                btnTorn.setVisibility(View.INVISIBLE);
            }
        });
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvdados4.setText("");
                tvdados04.setVisibility(View.INVISIBLE);
                tvdados4.setVisibility(View.INVISIBLE);
                btnDat.setVisibility(View.INVISIBLE);
            }
        });


    }
    ///************************************** FIM DO ONCREATE *********************************************************************

    public void montaGrafico(){
        //Toast.makeText(Graficos.this, tvRes.getText().toString(), Toast.LENGTH_SHORT).show();
        if(tvRes.getText().toString()!=""){
            String res = tvRes.getText().toString();
            String resTxt1 = spinnerAcoes.getSelectedItem().toString();
            String resTxt2 = spinnerJogadores.getSelectedItem().toString();
            String resTxt3 = resTxt2+": "+res+" "+resTxt1;
            String dados[] = res.split(Pattern.quote(","));
            if(dados.length==1){
                tvResTxt.setText(resTxt3);
                tvResTxt.setVisibility(View.VISIBLE);
                //tvRes.setVisibility(View.VISIBLE);
            }
            else if(dados.length>1){
                int passer = Integer.parseInt(dados[0]);
                int chuteg = Integer.valueOf(dados[1]);
                int perdidas = Integer.valueOf(dados[0]);
                int intercept = Integer.valueOf(dados[0]);
                pieChart.addPieSlice(new PieModel("Passes errados", passer, Color.parseColor("#FE6DA8")));
                pieChart.addPieSlice(new PieModel("Chutes a gol", chuteg, Color.parseColor("#56B7F1")));
                pieChart.addPieSlice(new PieModel("Bolas Perdidas", perdidas, Color.parseColor("#CDA67F")));
                pieChart.addPieSlice(new PieModel("Interceptações", intercept, Color.parseColor("#FED70E")));
                pieChart.setVisibility(View.VISIBLE);
                pieChart.startAnimation();
            }
            tvdados01.setVisibility(View.INVISIBLE);
            tvdados1.setVisibility(View.INVISIBLE);
            btnAct.setVisibility(View.INVISIBLE);
            tvdados02.setVisibility(View.INVISIBLE);
            tvdados2.setVisibility(View.INVISIBLE);
            btnJog.setVisibility(View.INVISIBLE);
        }
        else{
            //Toast.makeText(Graficos.this, "Não foi possível montar o gráfico", Toast.LENGTH_SHORT).show();
        }
    }

    public void escolherAcao(final String acao){
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_busca_acoes, new Response.Listener<String>() {
            @Override
            public void onResponse(String resposta) {
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
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

    public void escolherTodos(final String nomeAcao, final String nomeJogador, final String nomeTorneio, final String dataJogo){
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_monta_graficos, new Response.Listener<String>() {
            @Override
            public void onResponse(String resposta) {
                //Toast.makeText(Graficos.this, resposta, Toast.LENGTH_SHORT).show();

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
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

    public void escolherAcaoJogador(final String nomeAcao, final String nomeJogador){
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_acao_jogador, new Response.Listener<String>() {
            @Override
            public void onResponse(String resposta) {
                tvRes.setText(resposta);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
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

 //INÍCIO- AQUI POPULA O SPINNER DE JOGADORES COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerJogadores(String urlSpin) {
        final ArrayList<String> listaJogadores = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("jogadores");
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
             myWebView.loadUrl("http://192.168.15.17/pizza_todas_acoes.php");
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Passes Errados")){
             myWebView.loadUrl("http://192.168.15.17/pizza_passes_errados.php");
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Chutes a gol")){
             myWebView.loadUrl("http://192.168.15.17/pizza_chutes_gol.php");
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Bolas perdidas")){
             myWebView.loadUrl("http://192.168.15.17/pizza_bolas_perdidas.php");
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }
         if(spinnerAcoes.getSelectedItem().toString().equalsIgnoreCase("Interceptações")){
             myWebView.loadUrl("http://192.168.15.17/pizza_interceptacoes.php");
             WebSettings webSettings = myWebView.getSettings();
             webSettings.setJavaScriptEnabled(true);
         }

     }

    public void carregaWebViewsJogadores(){
        myWebView.loadUrl("http://192.168.15.17/pizza_retorno_acoes_jogador.php");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

     public String removerCaracteresEspeciais(String string) {
         string = Normalizer.normalize(string, Normalizer.Form.NFD);
         string = string.replaceAll("[^\\p{ASCII}]", "");
         return string;
     }

    public void escolherAcaoJogadorTorneio(final String nomeAcao, final String nomeJogador, final String nomeTorneio){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(Graficos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_busca_ac_jog_torn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        int passeErrado = Integer.parseInt(jogadorj.getString("passeErrado"));
                        int chuteAgol = Integer.parseInt(jogadorj.getString("chuteAgol"));
                        int perdida = Integer.parseInt(jogadorj.getString("perdida"));
                        int interceptacao = Integer.parseInt(jogadorj.getString("interceptacao"));
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Graficos.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
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

}
