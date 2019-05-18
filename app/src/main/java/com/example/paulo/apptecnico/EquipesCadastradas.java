package com.example.paulo.apptecnico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EquipesCadastradas extends AppCompatActivity {
    ImageView imgView;
    TextView apelidoEquipe, goleiro, goleiroRes, goleiroResRes, fixo, fixoRes, alaEsq, alaEsqRes, alaDir, alaDirRes, pivo, pivoRes,
            jogadorExtra0, jogadorExtra1, jogadorExtra2, jogadorExtra3, jogadorExtra4, jogadorExtra5, jogadorExtra6, jogadorExtra7,
            jogadorExtra8, jogadorExtra9, jogadorExtra10, jogadorExtra11, jogadorExtra12, jogadorExtra13,
            jogadorExtra14, jogadorExtra15, jogadorExtra16, jogadorExtra17, jogadorExtra18;

    TextView goleiroPosicao, goleiroNumero, goleiroEquipes, fixoPosicao, fixoNumero, fixoEquipes,
             alaEsqEquipes, alaEsqPosicao, alaEsqNumero, alaDirPosicao, alaDirNumero, alaDirEquipes,
             pivoPosicao, pivoNumero, pivoEquipes, goleiroResPosicao, goleiroResNumero, goleiroResEquipes,
             fixoResPosicao, fixoResNumero, fixoResEquipes, alaEsqResPosicao, alaEsqResNumero, alaEsqResEquipes,
             alaDirResPosicao, alaDirResNumero, alaDirResEquipes, pivoResPosicao, pivoResNumero, pivoResEquipes,
            jogadorExtra0Posicao, jogadorExtra0Numero, jogadorExtra0Equipes, jogadorExtra1Posicao, jogadorExtra1Numero, jogadorExtra1Equipes,
            jogadorExtra2Posicao, jogadorExtra2Numero, jogadorExtra2Equipes, jogadorExtra3Posicao, jogadorExtra3Numero, jogadorExtra3Equipes,
            jogadorExtra4Posicao, jogadorExtra4Numero, jogadorExtra4Equipes, jogadorExtra5Posicao, jogadorExtra5Numero, jogadorExtra5Equipes,
            jogadorExtra6Posicao, jogadorExtra6Numero, jogadorExtra6Equipes, jogadorExtra7Posicao, jogadorExtra7Numero, jogadorExtra7Equipes,
            jogadorExtra8Posicao, jogadorExtra8Numero, jogadorExtra8Equipes, jogadorExtra9Posicao, jogadorExtra9Numero, jogadorExtra9Equipes,
            jogadorExtra10Posicao, jogadorExtra10Numero, jogadorExtra10Equipes, jogadorExtra11Posicao, jogadorExtra11Numero, jogadorExtra11Equipes,
            jogadorExtra12Posicao, jogadorExtra12Numero, jogadorExtra12Equipes, jogadorExtra13Posicao, jogadorExtra13Numero, jogadorExtra13Equipes,
            jogadorExtra14Posicao, jogadorExtra14Numero, jogadorExtra14Equipes, jogadorExtra15Posicao, jogadorExtra15Numero, jogadorExtra15Equipes,
            jogadorExtra16Posicao, jogadorExtra16Numero, jogadorExtra16Equipes, jogadorExtra17Posicao, jogadorExtra17Numero, jogadorExtra17Equipes,
            jogadorExtra18Posicao, jogadorExtra18Numero, jogadorExtra18Equipes, goleiroResResPosicao, goleiroResResNumero, goleiroResResEquipes;

    String URLbusca = "http://192.168.15.17/busca_dados_jogador.php";
    String URL = "http://192.168.15.17/busca_torneios.php";
    String URLbuscaPorTorneio = "http://192.168.15.17/busca_equipe_por_torneio.php";
    String url_acao_jogador = "http://192.168.15.17/acao_jogador.php";

    //String url_acao_jogador = "https://appscout.000webhostapp.com/appscout/acao_jogador.php";
    //String URLbusca = "https://appscout.000webhostapp.com/appscout/busca_dados_jogador.php";
    //String URL = "https://appscout.000webhostapp.com/appscout/busca_torneios.php";
    //String URLbuscaPorTorneio = "https://appscout.000webhostapp.com/appscout/busca_equipe_por_torneio.php";

    Spinner spTorneio;
    AlertDialog.Builder alertDialog;
    Button btnJogadoresCadastrados, btnSair;
    Button btnGoleiroGraf, btngoleiroResGraf, btnfixoGraf, btnfixoResGraf,
           btnalaEsqGraf, btnalaEsqResGraf, btnalaDirGraf, btnalaDirResGraf, btnpivoGraf,
           btnpivoResGraf, btnjogadorExtra0Graf, btnjogadorExtra1Graf, btngoleiroResResGraf, btnjogadorExtra2Graf,
           btnjogadorExtra3Graf, btnjogadorExtra11Graf, btnjogadorExtra4Graf, btnjogadorExtra5Graf, btnjogadorExtra6Graf,
           btnjogadorExtra7Graf, btnjogadorExtra8Graf, btnjogadorExtra9Graf, btnjogadorExtra10Graf, btnjogadorExtra12Graf,
           btnjogadorExtra13Graf, btnjogadorExtra14Graf, btnjogadorExtra15Graf, btnjogadorExtra16Graf, btnjogadorExtra17Graf,
           btnjogadorExtra18Graf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes_cadastradas);
        spTorneio = findViewById(R.id.spTorneio);
        apelidoEquipe = findViewById(R.id.apelidoEquipe);
        btnJogadoresCadastrados = findViewById(R.id.btnVerificaCadastrados);
        btnSair = findViewById(R.id.btnSair);

        goleiro = findViewById(R.id.fieldGoleiro);
        goleiroRes = findViewById(R.id.fieldGoleiroRes);
        goleiroResRes = findViewById(R.id.fieldGoleiroResRes);
        fixo = findViewById(R.id.fieldFixo);
        fixoRes = findViewById(R.id.fieldFixoRes);
        alaEsq = findViewById(R.id.fieldAlaEsq);
        alaEsqRes = findViewById(R.id.fieldAlaEsqRes);
        alaDir = findViewById(R.id.fieldAlaDir);
        alaDirRes = findViewById(R.id.fieldAlaDirRes);
        pivo = findViewById(R.id.fieldPivo);
        pivoRes = findViewById(R.id.fieldPivoRes);
        goleiroResRes = findViewById(R.id.fieldGoleiroResRes);
        jogadorExtra0 = findViewById(R.id.fieldJogadorExtra0);
        jogadorExtra1 = findViewById(R.id.fieldJogadorExtra1);
        jogadorExtra2 = findViewById(R.id.fieldJogadorExtra2);
        jogadorExtra3 = findViewById(R.id.fieldJogadorExtra3);
        jogadorExtra4 = findViewById(R.id.fieldJogadorExtra4);
        jogadorExtra5 = findViewById(R.id.fieldJogadorExtra5);
        jogadorExtra6 = findViewById(R.id.fieldJogadorExtra6);
        jogadorExtra7 = findViewById(R.id.fieldJogadorExtra7);
        jogadorExtra8 = findViewById(R.id.fieldJogadorExtra8);
        jogadorExtra9 = findViewById(R.id.fieldJogadorExtra9);
        jogadorExtra10 = findViewById(R.id.fieldJogadorExtra10);
        jogadorExtra11 = findViewById(R.id.fieldJogadorExtra11);
        jogadorExtra12 = findViewById(R.id.fieldJogadorExtra12);
        jogadorExtra13 = findViewById(R.id.fieldJogadorExtra13);
        jogadorExtra14 = findViewById(R.id.fieldJogadorExtra14);
        jogadorExtra15 = findViewById(R.id.fieldJogadorExtra15);
        jogadorExtra16 = findViewById(R.id.fieldJogadorExtra16);
        jogadorExtra17 = findViewById(R.id.fieldJogadorExtra17);
        jogadorExtra18 = findViewById(R.id.fieldJogadorExtra18);

        goleiroPosicao = findViewById(R.id.goleiroPosicao);
        goleiroNumero = findViewById(R.id.goleiroNumero);
        goleiroEquipes = findViewById(R.id.goleiroEquipes);
        fixoPosicao = findViewById(R.id.fixoPosicao);
        fixoNumero = findViewById(R.id.fixoNumero);
        fixoEquipes = findViewById(R.id.fixoEquipes);
        alaEsqPosicao = findViewById(R.id.alaEsqPosicao);
        alaEsqNumero = findViewById(R.id.alaEsqNumero);
        alaEsqEquipes = findViewById(R.id.alaEsqEquipes);
        alaDirPosicao = findViewById(R.id.alaDirPosicao);
        alaDirNumero = findViewById(R.id.alaDirNumero);
        alaDirEquipes = findViewById(R.id.alaDirEquipes);
        pivoPosicao = findViewById(R.id.pivoPosicao);
        pivoNumero = findViewById(R.id.pivoNumero);
        pivoEquipes = findViewById(R.id.pivoEquipes);
        goleiroResPosicao = findViewById(R.id.goleiroResPosicao);
        goleiroResNumero = findViewById(R.id.goleiroResNumero);
        goleiroResEquipes = findViewById(R.id.goleiroResEquipes);
        fixoResPosicao = findViewById(R.id.fixoResPosicao);
        fixoResNumero = findViewById(R.id.fixoResNumero);
        fixoResEquipes = findViewById(R.id.fixoResEquipes);
        alaEsqResPosicao = findViewById(R.id.alaEsqResPosicao);
        alaEsqResNumero = findViewById(R.id.alaEsqResNumero);
        alaEsqResEquipes = findViewById(R.id.alaEsqResEquipes);
        alaDirResPosicao = findViewById(R.id.alaDirResPosicao);
        alaDirResNumero = findViewById(R.id.alaDirResNumero);
        alaDirResEquipes = findViewById(R.id.alaDirResEquipes);
        pivoResPosicao = findViewById(R.id.pivoResPosicao);
        pivoResNumero = findViewById(R.id.pivoResNumero);
        pivoResEquipes = findViewById(R.id.pivoResEquipes);
        goleiroResResPosicao = findViewById(R.id.goleiroResResPosicao);
        goleiroResResNumero = findViewById(R.id.goleiroResResNumero);
        goleiroResResEquipes = findViewById(R.id.goleiroResResEquipes);

        jogadorExtra0Posicao = findViewById(R.id.jogadorExtra0Posicao);
        jogadorExtra0Numero = findViewById(R.id.jogadorExtra0Numero);
        jogadorExtra0Equipes = findViewById(R.id.jogadorExtra0Equipes);

        jogadorExtra1Posicao = findViewById(R.id.jogadorExtra1Posicao);
        jogadorExtra1Numero = findViewById(R.id.jogadorExtra1Numero);
        jogadorExtra1Equipes = findViewById(R.id.jogadorExtra1Equipes);

        jogadorExtra2Posicao = findViewById(R.id.jogadorExtra2Posicao);
        jogadorExtra2Numero = findViewById(R.id.jogadorExtra2Numero);
        jogadorExtra2Equipes = findViewById(R.id.jogadorExtra2Equipes);

        jogadorExtra3Posicao = findViewById(R.id.jogadorExtra3Posicao);
        jogadorExtra3Numero = findViewById(R.id.jogadorExtra3Numero);
        jogadorExtra3Equipes = findViewById(R.id.jogadorExtra3Equipes);

        jogadorExtra4Posicao = findViewById(R.id.jogadorExtra4Posicao);
        jogadorExtra4Numero = findViewById(R.id.jogadorExtra4Numero);
        jogadorExtra4Equipes = findViewById(R.id.jogadorExtra4Equipes);

        jogadorExtra5Posicao = findViewById(R.id.jogadorExtra5Posicao);
        jogadorExtra5Numero = findViewById(R.id.jogadorExtra5Numero);
        jogadorExtra5Equipes = findViewById(R.id.jogadorExtra5Equipes);

        jogadorExtra6Posicao = findViewById(R.id.jogadorExtra6Posicao);
        jogadorExtra6Numero = findViewById(R.id.jogadorExtra6Numero);
        jogadorExtra6Equipes = findViewById(R.id.jogadorExtra6Equipes);

        jogadorExtra7Posicao = findViewById(R.id.jogadorExtra7Posicao);
        jogadorExtra7Numero = findViewById(R.id.jogadorExtra7Numero);
        jogadorExtra7Equipes = findViewById(R.id.jogadorExtra7Equipes);

        jogadorExtra8Posicao = findViewById(R.id.jogadorExtra8Posicao);
        jogadorExtra8Numero = findViewById(R.id.jogadorExtra8Numero);
        jogadorExtra8Equipes = findViewById(R.id.jogadorExtra8Equipes);

        jogadorExtra9Posicao = findViewById(R.id.jogadorExtra9Posicao);
        jogadorExtra9Numero = findViewById(R.id.jogadorExtra9Numero);
        jogadorExtra9Equipes = findViewById(R.id.jogadorExtra9Equipes);

        jogadorExtra10Posicao = findViewById(R.id.jogadorExtra10Posicao);
        jogadorExtra10Numero = findViewById(R.id.jogadorExtra10Numero);
        jogadorExtra10Equipes = findViewById(R.id.jogadorExtra10Equipes);

        jogadorExtra11Posicao = findViewById(R.id.jogadorExtra11Posicao);
        jogadorExtra11Numero = findViewById(R.id.jogadorExtra11Numero);
        jogadorExtra11Equipes = findViewById(R.id.jogadorExtra11Equipes);

        jogadorExtra12Posicao = findViewById(R.id.jogadorExtra12Posicao);
        jogadorExtra12Numero = findViewById(R.id.jogadorExtra12Numero);
        jogadorExtra12Equipes = findViewById(R.id.jogadorExtra12Equipes);

        jogadorExtra13Posicao = findViewById(R.id.jogadorExtra13Posicao);
        jogadorExtra13Numero = findViewById(R.id.jogadorExtra13Numero);
        jogadorExtra13Equipes = findViewById(R.id.jogadorExtra13Equipes);

        jogadorExtra14Posicao = findViewById(R.id.jogadorExtra14Posicao);
        jogadorExtra14Numero = findViewById(R.id.jogadorExtra14Numero);
        jogadorExtra14Equipes = findViewById(R.id.jogadorExtra14Equipes);

        jogadorExtra15Posicao = findViewById(R.id.jogadorExtra15Posicao);
        jogadorExtra15Numero = findViewById(R.id.jogadorExtra15Numero);
        jogadorExtra15Equipes = findViewById(R.id.jogadorExtra15Equipes);

        jogadorExtra16Posicao = findViewById(R.id.jogadorExtra16Posicao);
        jogadorExtra16Numero = findViewById(R.id.jogadorExtra16Numero);
        jogadorExtra16Equipes = findViewById(R.id.jogadorExtra16Equipes);

        jogadorExtra17Posicao = findViewById(R.id.jogadorExtra17Posicao);
        jogadorExtra17Numero = findViewById(R.id.jogadorExtra17Numero);
        jogadorExtra17Equipes = findViewById(R.id.jogadorExtra17Equipes);

        jogadorExtra18Posicao = findViewById(R.id.jogadorExtra18Posicao);
        jogadorExtra18Numero = findViewById(R.id.jogadorExtra18Numero);
        jogadorExtra18Equipes = findViewById(R.id.jogadorExtra18Equipes);

         btnGoleiroGraf = findViewById(R.id.btnGoleiroGraf);
         btngoleiroResGraf = findViewById(R.id.btnGoleiroResGraf);
         btnfixoGraf = findViewById(R.id.btnFixoGraf);
         btnfixoResGraf = findViewById(R.id.btnFixoResGraf);
         btnalaEsqGraf = findViewById(R.id.btnAlaEsqGraf);
         btnalaEsqResGraf = findViewById(R.id.btnAlaEsqResGraf);
         btnalaDirGraf = findViewById(R.id.btnAlaDirGraf);
         btnalaDirResGraf = findViewById(R.id.btnAlaDirResGraf);
         btnpivoGraf = findViewById(R.id.btnPivoGraf);
         btnjogadorExtra18Graf = findViewById(R.id.btnJogadorExtra18Graf);
         btnpivoResGraf = findViewById(R.id.btnPivoResGraf);
         btnjogadorExtra0Graf = findViewById(R.id.btnJogadorExtra0Graf);
         btnjogadorExtra1Graf = findViewById(R.id.btnJogadorExtra1Graf);
         btngoleiroResResGraf = findViewById(R.id.btnGoleiroResResGraf);
         btnjogadorExtra2Graf = findViewById(R.id.btnJogadorExtra2Graf);
         btnjogadorExtra3Graf = findViewById(R.id.btnJogadorExtra3Graf);
         btnjogadorExtra11Graf = findViewById(R.id.btnJogadorExtra11Graf);
         btnjogadorExtra4Graf = findViewById(R.id.btnJogadorExtra4Graf);
         btnjogadorExtra5Graf = findViewById(R.id.btnJogadorExtra5Graf);
         btnjogadorExtra6Graf = findViewById(R.id.btnJogadorExtra6Graf);
         btnjogadorExtra7Graf = findViewById(R.id.btnJogadorExtra7Graf);
         btnjogadorExtra8Graf = findViewById(R.id.btnJogadorExtra8Graf);
         btnjogadorExtra9Graf = findViewById(R.id.btnJogadorExtra9Graf);
         btnjogadorExtra10Graf = findViewById(R.id.btnJogadorExtra10Graf);
         btnjogadorExtra12Graf = findViewById(R.id.btnJogadorExtra12Graf);
         btnjogadorExtra13Graf = findViewById(R.id.btnJogadorExtra13Graf);
         btnjogadorExtra14Graf = findViewById(R.id.btnJogadorExtra14Graf);
         btnjogadorExtra15Graf = findViewById(R.id.btnJogadorExtra15Graf);
         btnjogadorExtra16Graf = findViewById(R.id.btnJogadorExtra16Graf);
         btnjogadorExtra17Graf = findViewById(R.id.btnJogadorExtra17Graf);
        btnjogadorExtra18Graf = findViewById(R.id.btnJogadorExtra18Graf);

        loadSpinnerTorneios(URL);
        spTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String torneio = spTorneio.getItemAtPosition(spTorneio.getSelectedItemPosition()).toString();
                buscaEquipePorTorneio(torneio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnJogadoresCadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montarDados();
            }
        });

        btnGoleiroGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogGol();
            }
        });

        btngoleiroResGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogGolRes();
            }
        });

        btnfixoGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogFixo();
            }
        });

        btnfixoResGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogFixoRes();
            }
        });

        btnalaEsqGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogAlaEsq();
            }
        });

        btnalaEsqResGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogAlaEsqRes();
            }
        });

        btnalaDirGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogAlaDir();
            }
        });

        btnalaDirResGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogAlaDirRes();
            }
        });

        btnpivoGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogPivo();
            }
        });

        btnpivoResGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogPivoRes();
            }
        });

        btngoleiroResResGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogGoleResRes();
            }
        });

        btnjogadorExtra0Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog0();
            }
        });

        btnjogadorExtra1Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog1();
            }
        });

        btnjogadorExtra2Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog2();
            }
        });

        btnjogadorExtra3Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog3();
            }
        });

        btnjogadorExtra4Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog4();
            }
        });

        btnjogadorExtra5Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog5();
            }
        });

        btnjogadorExtra6Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog6();
            }
        });

        btnjogadorExtra7Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog7();
            }
        });

        btnjogadorExtra8Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog8();
            }
        });

        btnjogadorExtra9Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog9();
            }
        });

        btnjogadorExtra10Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog10();
            }
        });

        btnjogadorExtra11Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog11();
            }
        });

        btnjogadorExtra12Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog12();
            }
        });

        btnjogadorExtra13Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog13();
            }
        });

        btnjogadorExtra14Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog14();
            }
        });

        btnjogadorExtra15Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog15();
            }
        });

        btnjogadorExtra16Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog16();
            }
        });


        btnjogadorExtra17Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog17();
            }
        });

        btnjogadorExtra18Graf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog18();
            }
        });

    }
    //FIM DO ON CREATE



    public String removerCaracteresEspeciais(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }


    String stNomeTorneios;
    public void buscaEquipePorTorneio(String torneio){
        String stIDnomeTorneio = torneio;
        String retiraCaracter = removerCaracteresEspeciais(stIDnomeTorneio);
        stNomeTorneios = retiraCaracter.replaceAll("[^a-zA-Z\\s]", "");
        final String stNomeTorneio = stNomeTorneios.trim();

        RequestQueue queue = Volley.newRequestQueue(EquipesCadastradas.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorTorneio, new Response.Listener<String>() {
            @Override
            public void onResponse(String resposta) {
                //Toast.makeText(EquipesCadastradas.this, resposta, Toast.LENGTH_LONG).show();
                try {
                    JSONObject objetoJson = new JSONObject(resposta);
                    JSONArray jsonArray = objetoJson.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        apelidoEquipe.setText(jogadorj.getString("apelidoEquipe"));
                        goleiro.setText(jogadorj.getString("goleiro"));
                        fixo.setText(jogadorj.getString("fixo"));
                        alaEsq.setText(jogadorj.getString("alaEsq"));
                        alaDir.setText(jogadorj.getString("alaDir"));
                        pivo.setText(jogadorj.getString("pivo"));
                        goleiroRes.setText(jogadorj.getString("goleiroRes"));
                        fixoRes.setText(jogadorj.getString("fixoRes"));
                        alaEsqRes.setText(jogadorj.getString("alaEsqRes"));
                        alaDirRes.setText(jogadorj.getString("alaDirRes"));
                        pivoRes.setText(jogadorj.getString("pivoRes"));
                        goleiroResRes.setText(jogadorj.getString("goleiroResRes"));
                        jogadorExtra0.setText(jogadorj.getString("jogadorExtra"));
                        jogadorExtra1.setText(jogadorj.getString("jogadorExtra1"));
                        jogadorExtra2.setText(jogadorj.getString("jogadorExtra2"));
                        jogadorExtra3.setText(jogadorj.getString("jogadorExtra3"));
                        jogadorExtra4.setText(jogadorj.getString("jogadorExtra4"));
                        jogadorExtra5.setText(jogadorj.getString("jogadorExtra5"));
                        jogadorExtra6.setText(jogadorj.getString("jogadorExtra6"));
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipesCadastradas.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })
        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeTorneio", stNomeTorneio);
                return params;
            }
        };
            queue.add(stringRequest);
    }


    private void loadSpinnerTorneios(String url) {
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("torneios");
                    listaTorneios.add("Selecione o torneio:");
                    for (int i = 1; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        int idTorneio = jsonObject1.getInt("ID_torneio");
                        String nomeTorneio = jsonObject1.getString("nomeTorneio");
                        String var = String.valueOf(idTorneio);
                        listaTorneios.add(var+"-"+nomeTorneio);
                    }
                    spTorneio.setAdapter(new ArrayAdapter<String>(EquipesCadastradas.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
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

    private int somenteDigitos(String palavra) {
        String digitos = "";
        char[] letras  = palavra.toCharArray();
        for (char letra : letras) {
            if(Character.isDigit(letra)) {
                digitos += letra;
            }
        }
        return Integer.parseInt(digitos);
    }



    public void montarDados(){
            RequestQueue queue = Volley.newRequestQueue(EquipesCadastradas.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbusca, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    alertDialog = new AlertDialog.Builder(EquipesCadastradas.this);
                    alertDialog.setMessage("Resposta: " + response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject goleiroj = jsonArray.getJSONObject(0);
                            goleiro.setText(goleiroj.getString("nomeJogador"));
                            goleiroPosicao.setText(goleiroj.getString("posicao"));
                            goleiroNumero.setText(goleiroj.getString("numeroJogador"));
                            goleiroEquipes.setText(goleiroj.getString("apelidoEquipe"));
                            strIMGgoleiroj = goleiroj.getString("foto");


                            JSONObject fixoj = jsonArray.getJSONObject(1);
                            fixo.setText(fixoj.getString("nomeJogador"));
                            fixoPosicao.setText(fixoj.getString("posicao"));
                            fixoNumero.setText(fixoj.getString("numeroJogador"));
                            fixoEquipes.setText(fixoj.getString("apelidoEquipe"));
                            strIMGfixoj = goleiroj.getString("foto");

                            JSONObject alaEsqj = jsonArray.getJSONObject(2);
                            alaEsq.setText(alaEsqj.getString("nomeJogador"));
                            alaEsqPosicao.setText(alaEsqj.getString("posicao"));
                            alaEsqNumero.setText(alaEsqj.getString("numeroJogador"));
                            alaEsqEquipes.setText(alaEsqj.getString("apelidoEquipe"));
                            strIMGalaEsqj = goleiroj.getString("foto");

                            JSONObject alaDirj = jsonArray.getJSONObject(3);
                            alaDir.setText(alaDirj.getString("nomeJogador"));
                            alaDirPosicao.setText(alaDirj.getString("posicao"));
                            alaDirNumero.setText(alaDirj.getString("numeroJogador"));
                            alaDirEquipes.setText(alaDirj.getString("apelidoEquipe"));
                            strIMGalaDirj = goleiroj.getString("foto");

                            JSONObject pivoj = jsonArray.getJSONObject(4);
                            pivo.setText(pivoj.getString("nomeJogador"));
                            pivoPosicao.setText(pivoj.getString("posicao"));
                            pivoNumero.setText(pivoj.getString("numeroJogador"));
                            pivoEquipes.setText(pivoj.getString("apelidoEquipe"));
                            strIMGpivoj = goleiroj.getString("foto");

                            JSONObject goleiroResj = jsonArray.getJSONObject(5);
                            goleiroRes.setText(goleiroResj.getString("nomeJogador"));
                            goleiroResPosicao.setText(goleiroResj.getString("posicao"));
                            goleiroResNumero.setText(goleiroResj.getString("numeroJogador"));
                            goleiroResEquipes.setText(goleiroResj.getString("apelidoEquipe"));
                            strIMGgoleiroResj = goleiroj.getString("foto");

                            JSONObject fixoResj = jsonArray.getJSONObject(6);
                            fixoRes.setText(fixoResj.getString("nomeJogador"));
                            fixoResPosicao.setText(fixoResj.getString("posicao"));
                            fixoResNumero.setText(fixoResj.getString("numeroJogador"));
                            fixoResEquipes.setText(fixoResj.getString("apelidoEquipe"));
                            strIMGfixoResj = goleiroj.getString("foto");

                            JSONObject alaEsqResj = jsonArray.getJSONObject(7);
                            alaEsqRes.setText(alaEsqResj.getString("nomeJogador"));
                            alaEsqResPosicao.setText(alaEsqResj.getString("posicao"));
                            alaEsqResNumero.setText(alaEsqResj.getString("numeroJogador"));
                            alaEsqResEquipes.setText(alaEsqResj.getString("apelidoEquipe"));
                            strIMGalaEsqResj = goleiroj.getString("foto");

                            JSONObject alaDirResj = jsonArray.getJSONObject(8);
                            alaDirRes.setText(alaDirResj.getString("nomeJogador"));
                            alaDirResPosicao.setText(alaDirResj.getString("posicao"));
                            alaDirResNumero.setText(alaDirResj.getString("numeroJogador"));
                            alaDirResEquipes.setText(alaDirResj.getString("apelidoEquipe"));
                            strIMGalaDirResj = goleiroj.getString("foto");

                            JSONObject pivoResj = jsonArray.getJSONObject(9);
                            pivoRes.setText(pivoResj.getString("nomeJogador"));
                            pivoResPosicao.setText(pivoResj.getString("posicao"));
                            pivoResNumero.setText(pivoResj.getString("numeroJogador"));
                            pivoResEquipes.setText(pivoResj.getString("apelidoEquipe"));
                            strIMGpivoResj = goleiroj.getString("foto");

                            JSONObject goleiroResResj = jsonArray.getJSONObject(10);
                            goleiroResRes.setText(goleiroResResj.getString("nomeJogador"));
                            goleiroResResPosicao.setText(goleiroResResj.getString("posicao"));
                            goleiroResResNumero.setText(goleiroResResj.getString("numeroJogador"));
                            goleiroResResEquipes.setText(goleiroResResj.getString("apelidoEquipe"));
                            strIMGgoleiroResResj = goleiroj.getString("foto");

                            JSONObject jogadorExtra0j = jsonArray.getJSONObject(11);
                            jogadorExtra0.setText(jogadorExtra0j.getString("nomeJogador"));
                            jogadorExtra0Posicao.setText(jogadorExtra0j.getString("posicao"));
                            jogadorExtra0Numero.setText(jogadorExtra0j.getString("numeroJogador"));
                            jogadorExtra0Equipes.setText(jogadorExtra0j.getString("apelidoEquipe"));
                            strIMGjogadorExtra0j = goleiroj.getString("foto");

                            JSONObject jogadorExtra1j = jsonArray.getJSONObject(12);
                            jogadorExtra1.setText(jogadorExtra1j.getString("nomeJogador"));
                            jogadorExtra1Posicao.setText(jogadorExtra1j.getString("posicao"));
                            jogadorExtra1Numero.setText(jogadorExtra1j.getString("numeroJogador"));
                            jogadorExtra1Equipes.setText(jogadorExtra1j.getString("apelidoEquipe"));
                            strIMGjogadorExtra1j = goleiroj.getString("foto");

                            JSONObject jogadorExtra2j = jsonArray.getJSONObject(13);
                            jogadorExtra2.setText(jogadorExtra2j.getString("nomeJogador"));
                            jogadorExtra2Posicao.setText(jogadorExtra2j.getString("posicao"));
                            jogadorExtra2Numero.setText(jogadorExtra2j.getString("numeroJogador"));
                            jogadorExtra2Equipes.setText(jogadorExtra2j.getString("apelidoEquipe"));
                            strIMGjogadorExtra2j = goleiroj.getString("foto");

                            JSONObject jogadorExtra3j = jsonArray.getJSONObject(14);
                            jogadorExtra3.setText(jogadorExtra3j.getString("nomeJogador"));
                            jogadorExtra3Posicao.setText(jogadorExtra3j.getString("posicao"));
                            jogadorExtra3Numero.setText(jogadorExtra3j.getString("numeroJogador"));
                            jogadorExtra3Equipes.setText(jogadorExtra3j.getString("apelidoEquipe"));
                            strIMGjogadorExtra3j = goleiroj.getString("foto");

                            JSONObject jogadorExtra4j = jsonArray.getJSONObject(15);
                            jogadorExtra4.setText(jogadorExtra4j.getString("nomeJogador"));
                            jogadorExtra4Posicao.setText(jogadorExtra4j.getString("posicao"));
                            jogadorExtra4Numero.setText(jogadorExtra4j.getString("numeroJogador"));
                            jogadorExtra4Equipes.setText(jogadorExtra4j.getString("apelidoEquipe"));
                            strIMGjogadorExtra4j = goleiroj.getString("foto");

                            JSONObject jogadorExtra5j = jsonArray.getJSONObject(16);
                            jogadorExtra5.setText(jogadorExtra5j.getString("nomeJogador"));
                            jogadorExtra5Posicao.setText(jogadorExtra5j.getString("posicao"));
                            jogadorExtra5Numero.setText(jogadorExtra5j.getString("numeroJogador"));
                            jogadorExtra5Equipes.setText(jogadorExtra5j.getString("apelidoEquipe"));
                            strIMGjogadorExtra5j = goleiroj.getString("foto");

                            JSONObject jogadorExtra6j = jsonArray.getJSONObject(17);
                            jogadorExtra6.setText(jogadorExtra6j.getString("nomeJogador"));
                            jogadorExtra6Posicao.setText(jogadorExtra6j.getString("posicao"));
                            jogadorExtra6Numero.setText(jogadorExtra6j.getString("numeroJogador"));
                            jogadorExtra6Equipes.setText(jogadorExtra6j.getString("apelidoEquipe"));
                            strIMGjogadorExtra6j = goleiroj.getString("foto");

                            JSONObject jogadorExtra7j = jsonArray.getJSONObject(18);
                            jogadorExtra7.setText(jogadorExtra7j.getString("nomeJogador"));
                            jogadorExtra7Posicao.setText(jogadorExtra7j.getString("posicao"));
                            jogadorExtra7Numero.setText(jogadorExtra7j.getString("numeroJogador"));
                            jogadorExtra7Equipes.setText(jogadorExtra7j.getString("apelidoEquipe"));
                            strIMGjogadorExtra7j = goleiroj.getString("foto");

                            JSONObject jogadorExtra8j = jsonArray.getJSONObject(19);
                            jogadorExtra8.setText(jogadorExtra8j.getString("nomeJogador"));
                            jogadorExtra8Posicao.setText(jogadorExtra8j.getString("posicao"));
                            jogadorExtra8Numero.setText(jogadorExtra8j.getString("numeroJogador"));
                            jogadorExtra8Equipes.setText(jogadorExtra8j.getString("apelidoEquipe"));
                            strIMGjogadorExtra8j = goleiroj.getString("foto");

                            JSONObject jogadorExtra9j = jsonArray.getJSONObject(20);
                            jogadorExtra9.setText(jogadorExtra9j.getString("nomeJogador"));
                            jogadorExtra9Posicao.setText(jogadorExtra9j.getString("posicao"));
                            jogadorExtra9Numero.setText(jogadorExtra9j.getString("numeroJogador"));
                            jogadorExtra9Equipes.setText(jogadorExtra9j.getString("apelidoEquipe"));
                            strIMGjogadorExtra9j = goleiroj.getString("foto");

                            JSONObject jogadorExtra10j = jsonArray.getJSONObject(21);
                            jogadorExtra10.setText(jogadorExtra10j.getString("nomeJogador"));
                            jogadorExtra10Posicao.setText(jogadorExtra10j.getString("posicao"));
                            jogadorExtra10Numero.setText(jogadorExtra10j.getString("numeroJogador"));
                            jogadorExtra10Equipes.setText(jogadorExtra10j.getString("apelidoEquipe"));
                            strIMGjogadorExtra10j = goleiroj.getString("foto");

                            JSONObject jogadorExtra11j = jsonArray.getJSONObject(22);
                            jogadorExtra11.setText(jogadorExtra11j.getString("nomeJogador"));
                            jogadorExtra11Posicao.setText(jogadorExtra11j.getString("posicao"));
                            jogadorExtra11Numero.setText(jogadorExtra11j.getString("numeroJogador"));
                            jogadorExtra11Equipes.setText(jogadorExtra11j.getString("apelidoEquipe"));
                            strIMGjogadorExtra11j = goleiroj.getString("foto");

                            JSONObject jogadorExtra12j = jsonArray.getJSONObject(23);
                            jogadorExtra12.setText(jogadorExtra12j.getString("nomeJogador"));
                            jogadorExtra12Posicao.setText(jogadorExtra12j.getString("posicao"));
                            jogadorExtra12Numero.setText(jogadorExtra12j.getString("numeroJogador"));
                            jogadorExtra12Equipes.setText(jogadorExtra12j.getString("apelidoEquipe"));
                            strIMGjogadorExtra12j = goleiroj.getString("foto");

                            JSONObject jogadorExtra13j = jsonArray.getJSONObject(24);
                            jogadorExtra13.setText(jogadorExtra13j.getString("nomeJogador"));
                            jogadorExtra13Posicao.setText(jogadorExtra13j.getString("posicao"));
                            jogadorExtra13Numero.setText(jogadorExtra13j.getString("numeroJogador"));
                            jogadorExtra13Equipes.setText(jogadorExtra13j.getString("apelidoEquipe"));
                            strIMGjogadorExtra13j = goleiroj.getString("foto");

                            JSONObject jogadorExtra14j = jsonArray.getJSONObject(25);
                            jogadorExtra14.setText(jogadorExtra14j.getString("nomeJogador"));
                            jogadorExtra14Posicao.setText(jogadorExtra14j.getString("posicao"));
                            jogadorExtra14Numero.setText(jogadorExtra14j.getString("numeroJogador"));
                            jogadorExtra14Equipes.setText(jogadorExtra14j.getString("apelidoEquipe"));
                            strIMGjogadorExtra14j = goleiroj.getString("foto");

                            JSONObject jogadorExtra15j = jsonArray.getJSONObject(26);
                            jogadorExtra15.setText(jogadorExtra15j.getString("nomeJogador"));
                            jogadorExtra15Posicao.setText(jogadorExtra15j.getString("posicao"));
                            jogadorExtra15Numero.setText(jogadorExtra15j.getString("numeroJogador"));
                            jogadorExtra15Equipes.setText(jogadorExtra15j.getString("apelidoEquipe"));
                            strIMGjogadorExtra15j = goleiroj.getString("foto");

                            JSONObject jogadorExtra16j = jsonArray.getJSONObject(27);
                            jogadorExtra16.setText(jogadorExtra16j.getString("nomeJogador"));
                            jogadorExtra16Posicao.setText(jogadorExtra16j.getString("posicao"));
                            jogadorExtra16Numero.setText(jogadorExtra16j.getString("numeroJogador"));
                            jogadorExtra16Equipes.setText(jogadorExtra16j.getString("apelidoEquipe"));
                            strIMGjogadorExtra16j = goleiroj.getString("foto");

                            JSONObject jogadorExtra17j = jsonArray.getJSONObject(28);
                            jogadorExtra17.setText(jogadorExtra17j.getString("nomeJogador"));
                            jogadorExtra17Posicao.setText(jogadorExtra17j.getString("posicao"));
                            jogadorExtra17Numero.setText(jogadorExtra17j.getString("numeroJogador"));
                            jogadorExtra17Equipes.setText(jogadorExtra17j.getString("apelidoEquipe"));
                            strIMGjogadorExtra17j = goleiroj.getString("foto");

                            JSONObject jogadorExtra18j = jsonArray.getJSONObject(29);
                            jogadorExtra18.setText(jogadorExtra18j.getString("nomeJogador"));
                            jogadorExtra18Posicao.setText(jogadorExtra18j.getString("posicao"));
                            jogadorExtra18Numero.setText(jogadorExtra18j.getString("numeroJogador"));
                            jogadorExtra18Equipes.setText(jogadorExtra18j.getString("apelidoEquipe"));
                            strIMGjogadorExtra18j = goleiroj.getString("foto");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EquipesCadastradas.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    });
            queue.add(stringRequest);
    }

    public void clickBtnSair(View view){
        Intent it;
        it = new Intent(EquipesCadastradas.this, Sistema.class);
        startActivity(it);
    }


    //*******************************ALERT DIALOG**********************************************************************
    //*****************************************************************************************************************
     //*****************************************************************************************************************
    //*******************************ALERT DIALOG**********************************************************************


    String strIMGjogadorExtra14j, strIMGjogadorExtra15j, strIMGjogadorExtra16j, strIMGjogadorExtra17j, strIMGjogadorExtra18j,
            strIMGjogadorExtra10j, strIMGjogadorExtra11j, strIMGjogadorExtra12j, strIMGjogadorExtra13j,
            strIMGjogadorExtra5j, strIMGjogadorExtra6j, strIMGjogadorExtra7j, strIMGjogadorExtra8j, strIMGjogadorExtra9j,
            strIMGjogadorExtra0j, strIMGjogadorExtra1j, strIMGjogadorExtra2j, strIMGjogadorExtra3j, strIMGjogadorExtra4j,
            strIMGgoleiroj, strIMGfixoj, strIMGalaEsqj, strIMGalaDirj, strIMGpivoj, strIMGgoleiroResj, strIMGfixoResj,
            strIMGalaEsqResj, strIMGalaDirResj, strIMGpivoResj, strIMGgoleiroResResj;

    public void decodeImage(String strImage, ImageView img){
        byte[] decodedString = Base64.decode(strImage, Base64.DEFAULT);
        Bitmap imgBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        img.setImageBitmap(imgBitMap);
    }

    PieChart myPieChart;
    String passeErrado, chuteAgol, perdida, interceptacao;


    public void escolherAcaoJogador(final String nomeAcao, final String nomeJogador){
        RequestQueue queue = Volley.newRequestQueue(EquipesCadastradas.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_acao_jogador, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(nomeAcao=="Todos os dados") {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("dados");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jo = jsonArray.getJSONObject(i);
                            passeErrado = jo.getString("passeErrado");
                            if (passeErrado == "") passeErrado = "0";
                            chuteAgol = jo.getString("chuteAgol");
                            if (chuteAgol == "") chuteAgol = "0";
                            perdida = jo.getString("perdida");
                            if (perdida == "") perdida = "0";
                            interceptacao = jo.getString("interceptacao");
                            if (interceptacao == "") interceptacao = "0";
                            myPieChart.addPieSlice(new PieModel("Passes errados", Integer.parseInt(passeErrado), Color.parseColor("#FE6DA8")));
                            myPieChart.addPieSlice(new PieModel("Chutes a gol", Integer.parseInt(chuteAgol), Color.parseColor("#56B7F1")));
                            myPieChart.addPieSlice(new PieModel("Bolas Perdidas", Integer.parseInt(perdida), Color.parseColor("#CDA67F")));
                            myPieChart.addPieSlice(new PieModel("Interceptações", Integer.parseInt(interceptacao), Color.parseColor("#FED70E")));
                            myPieChart.setVisibility(View.VISIBLE);
                            myPieChart.startAnimation();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipesCadastradas.this, "Erro no servidor", Toast.LENGTH_LONG).show();
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

    private void showCustomDialogGol() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(goleiro.getText().toString());

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGgoleiroj, imgView);

        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(goleiroPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(goleiroNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogFixo() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(fixo.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGfixoj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(fixoPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(fixoNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogAlaEsq() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(alaEsq.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGalaEsqj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(alaEsqPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(alaEsqNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogAlaDir() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(alaDir.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGalaDirj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(alaDirPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(alaDirNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogPivo() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(pivo.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGpivoj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(pivoPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(pivoNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogGolRes() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(goleiroRes.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGgoleiroResj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(goleiroResPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(goleiroResNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogFixoRes() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(fixoRes.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGfixoResj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(fixoResPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(fixoResNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogAlaEsqRes() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(alaEsqRes.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGalaEsqResj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(alaEsqResPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(alaEsqResNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogAlaDirRes() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(alaDirRes.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGalaDirResj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(alaDirResPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(alaDirResNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogPivoRes() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(pivoRes.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGpivoResj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(pivoResPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(pivoResNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialogGoleResRes() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(goleiroResRes.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGgoleiroResResj, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(goleiroResResPosicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(goleiroResResNumero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }


    private void showCustomDialog0() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra0.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra0j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra0Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra0Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog1() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra1.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra1j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra1Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra1Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog2() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra2.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra2j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra2Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra2Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog3() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra3.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra3j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra3Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra3Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog4() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra4.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra4j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra4Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra4Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog5() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra5.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra5j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra5Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra5Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog6() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra6.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra6j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra6Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra6Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog7() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra7.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra7j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra7Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra7Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog8() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra8.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra8j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra8Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra8Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog9() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra9.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra9j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra9Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra9Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog10() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra10j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra10.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra10Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra10Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog11() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra11j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra11.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra11Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra11Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog12() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra12j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra12.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra12Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra12Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }


    private void showCustomDialog13() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra13j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra13.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra13Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra13Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog14() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra14j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra14.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra14Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra14Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog15() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra15j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra15.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra15Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra15Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog16() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra16j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra16.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra16Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra16Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog17() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);

        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra17j, imgView);

        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra17.getText().toString());
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra17Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra17Numero.getText().toString());
        myPieChart = view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }

    private void showCustomDialog18() {
        AlertDialog.Builder ad = new AlertDialog.Builder(EquipesCadastradas.this);
        LayoutInflater factory = LayoutInflater.from(EquipesCadastradas.this);
        View view = factory.inflate(R.layout.activity_alert_dialog, null);
        TextView tv10 = view.findViewById(R.id.tv10);
        tv10.setText(jogadorExtra18.getText().toString());
        imgView = view.findViewById(R.id.imgView);
        decodeImage(strIMGjogadorExtra18j, imgView);
        TextView tv20 = view.findViewById(R.id.tv20);
        tv20.setText(jogadorExtra18Posicao.getText().toString());
        TextView tv30 = view.findViewById(R.id.tv30);
        tv30.setText(jogadorExtra18Numero.getText().toString());
        myPieChart = (PieChart) view.findViewById(R.id.myPiechart);
        ad.setView(view);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        ad.show();
        escolherAcaoJogador("Todos os dados", tv10.getText().toString());
    }


    //FIM DO ALERT DIALOG *********************************************************************
    //*******************************FIM DO ALERT DIALOG*********************************************************************
    //*****************************************************************************************************************
    //*****************************************************************************************************************
    //*******************************FIM DO ALERT DIALOG*********************************************************************

}
