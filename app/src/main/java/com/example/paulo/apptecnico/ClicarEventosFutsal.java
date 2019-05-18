package com.example.paulo.apptecnico;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class ClicarEventosFutsal extends AppCompatActivity {

    Button btnVerificaEquipe, btnSair;
    AlertDialog.Builder alertDialog;
    Spinner spinnerTorneio, spinnerAdversario;
    String URLev = "http://192.168.15.17/insere_eventos.php";
    //String URLev = "https://appscout.000webhostapp.com/appscout/insere_eventos.php";
    TextView tvGoleiro, tvFixo, tvAlaEsq, tvAlaDir, tvPivo, tvGoleiroRes, tvFixoRes, tvAlaEsqRes, tvAlaDirRes, tvPivoRes, tvGoleiroResRes,
            tvJogadorExtra, tvNomeEquipe;
    Button btnFinalizar;
    TextView tvGoleiroAtleta, tvGoleiroPasseErrado, tvGoleiroChuteAgol, tvGoleiroPerdida, tvGoleiroInterceptacao;
    TextView tvFixoAtleta, tvFixoPasseErrado, tvFixoChuteAgol, tvFixoPerdida, tvFixoInterceptacao;
    TextView tvAlaEsqAtleta, tvAlaEsqPasseErrado, tvAlaEsqChuteAgol, tvAlaEsqPerdida, tvAlaEsqInterceptacao;
    TextView tvAlaDirAtleta, tvAlaDirPasseErrado, tvAlaDirChuteAgol, tvAlaDirPerdida, tvAlaDirInterceptacao;
    TextView tvPivoAtleta, tvPivoPasseErrado, tvPivoChuteAgol, tvPivoPerdida, tvPivoInterceptacao;
    TextView tvGoleiroAtletaRes, tvGoleiroPasseErradoRes, tvGoleiroChuteAgolRes, tvGoleiroPerdidaRes, tvGoleiroInterceptacaoRes;
    TextView tvFixoAtletaRes, tvFixoPasseErradoRes, tvFixoChuteAgolRes, tvFixoPerdidaRes, tvFixoInterceptacaoRes;
    TextView tvAlaEsqAtletaRes, tvAlaEsqPasseErradoRes, tvAlaEsqChuteAgolRes, tvAlaEsqPerdidaRes, tvAlaEsqInterceptacaoRes;
    TextView tvAlaDirAtletaRes, tvAlaDirPasseErradoRes, tvAlaDirChuteAgolRes, tvAlaDirPerdidaRes, tvAlaDirInterceptacaoRes;
    TextView tvPivoAtletaRes, tvPivoPasseErradoRes, tvPivoChuteAgolRes, tvPivoPerdidaRes, tvPivoInterceptacaoRes;
    TextView tvGoleiroAtletaResRes, tvGoleiroPasseErradoResRes, tvGoleiroChuteAgolResRes, tvGoleiroPerdidaResRes, tvGoleiroInterceptacaoResRes;
    TextView tvJogExtraAtleta, tvJogExtraPasseErrado, tvJogExtraChuteAgol, tvJogExtraPerdida, tvJogExtraInterceptacao;

    @SuppressLint("CutPasteId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicar_eventos_futsal);

        btnFinalizar = findViewById(R.id.finalizar);
        btnSair = findViewById(R.id.btnSair);

        tvGoleiroAtleta = findViewById(R.id.atleta_goleiro);
        tvGoleiroPasseErrado = findViewById(R.id.passer_goleiro);
        tvGoleiroChuteAgol = findViewById(R.id.chuteg_goleiro);
        tvGoleiroPerdida = findViewById(R.id.perdida_goleiro);
        tvGoleiroInterceptacao = findViewById(R.id.intercep_goleiro);

        tvFixoAtleta = findViewById(R.id.atleta_fixo);
        tvFixoPasseErrado = findViewById(R.id.passer_fixo);
        tvFixoChuteAgol = findViewById(R.id.chuteg_fixo);
        tvFixoPerdida = findViewById(R.id.perdida_fixo);
        tvFixoInterceptacao = findViewById(R.id.intercep_fixo);

        tvAlaEsqAtleta = findViewById(R.id.atleta_alaesq);
        tvAlaEsqPasseErrado = findViewById(R.id.passer_alaesq);
        tvAlaEsqChuteAgol = findViewById(R.id.chuteg_alaesq);
        tvAlaEsqPerdida = findViewById(R.id.perdida_alaesq);
        tvAlaEsqInterceptacao = findViewById(R.id.intercep_alaesq);

        tvAlaDirAtleta = findViewById(R.id.atleta_aladir);
        tvAlaDirPasseErrado = findViewById(R.id.passer_aladir);
        tvAlaDirChuteAgol = findViewById(R.id.chuteg_aladir);
        tvAlaDirPerdida = findViewById(R.id.perdida_aladir);
        tvAlaDirInterceptacao = findViewById(R.id.intercep_aladir);

        tvPivoAtleta = findViewById(R.id.atleta_pivo);
        tvPivoPasseErrado = findViewById(R.id.passer_pivo);
        tvPivoChuteAgol = findViewById(R.id.chuteg_pivo);
        tvPivoPerdida = findViewById(R.id.perdida_pivo);
        tvPivoInterceptacao = findViewById(R.id.intercep_pivo);

        tvGoleiroAtletaRes = findViewById(R.id.atleta_goleiroRes);
        tvGoleiroPasseErradoRes = findViewById(R.id.passer_goleiroRes);
        tvGoleiroChuteAgolRes = findViewById(R.id.chuteg_goleiroRes);
        tvGoleiroPerdidaRes = findViewById(R.id.perdida_goleiroRes);
        tvGoleiroInterceptacaoRes = findViewById(R.id.intercep_goleiroRes);

        tvFixoAtletaRes = findViewById(R.id.atleta_fixoRes);
        tvFixoPasseErradoRes = findViewById(R.id.passer_fixoRes);
        tvFixoChuteAgolRes = findViewById(R.id.chuteg_fixoRes);
        tvFixoPerdidaRes = findViewById(R.id.perdida_fixoRes);
        tvFixoInterceptacaoRes = findViewById(R.id.intercep_fixoRes);

        tvAlaEsqAtletaRes = findViewById(R.id.atleta_alaesqRes);
        tvAlaEsqPasseErradoRes = findViewById(R.id.passer_alaesqRes);
        tvAlaEsqChuteAgolRes = findViewById(R.id.chuteg_alaesqRes);
        tvAlaEsqPerdidaRes = findViewById(R.id.perdida_alaesqRes);
        tvAlaEsqInterceptacaoRes = findViewById(R.id.intercep_alaesqRes);

        tvAlaDirAtletaRes = findViewById(R.id.atleta_aladirRes);
        tvAlaDirPasseErradoRes = findViewById(R.id.passer_aladirRes);
        tvAlaDirChuteAgolRes = findViewById(R.id.chuteg_aladirRes);
        tvAlaDirPerdidaRes = findViewById(R.id.perdida_aladirRes);
        tvAlaDirInterceptacaoRes = findViewById(R.id.intercep_aladirRes);

        tvPivoAtletaRes = findViewById(R.id.atleta_pivoRes);
        tvPivoPasseErradoRes = findViewById(R.id.passer_pivoRes);
        tvPivoChuteAgolRes = findViewById(R.id.chuteg_pivoRes);
        tvPivoPerdidaRes = findViewById(R.id.perdida_pivoRes);
        tvPivoInterceptacaoRes = findViewById(R.id.intercep_pivoRes);

        tvGoleiroAtletaResRes = findViewById(R.id.atleta_goleiroResRes);
        tvGoleiroPasseErradoResRes = findViewById(R.id.passer_goleiroResRes);
        tvGoleiroChuteAgolResRes = findViewById(R.id.chuteg_goleiroResRes);
        tvGoleiroPerdidaResRes = findViewById(R.id.perdida_goleiroResRes);
        tvGoleiroInterceptacaoResRes = findViewById(R.id.intercep_goleiroResRes);

        tvJogExtraAtleta= findViewById(R.id.atleta_jogadorExtra);
        tvJogExtraPasseErrado = findViewById(R.id.passer_jogadorExtra);
        tvJogExtraChuteAgol = findViewById(R.id.chuteg_jogadorExtra);
        tvJogExtraPerdida = findViewById(R.id.perdida_jogadorExtra);
        tvJogExtraInterceptacao = findViewById(R.id.intercep_jogadorExtra);

        tvGoleiro = findViewById(R.id.fieldsGoleiro);
        tvFixo = findViewById(R.id.fieldsFixo);
        tvAlaEsq = findViewById(R.id.fieldsAlaEsq);
        tvAlaDir = findViewById(R.id.fieldsAlaDir);
        tvPivo = findViewById(R.id.fieldsPivo);
        tvGoleiroRes = findViewById(R.id.fieldsGoleiroRes);
        tvFixoRes = findViewById(R.id.fieldsFixoRes);
        tvAlaEsqRes = findViewById(R.id.fieldsAlaEsqRes);
        tvAlaDirRes = findViewById(R.id.fieldsAlaDirRes);
        tvPivoRes = findViewById(R.id.fieldsPivoRes);
        tvGoleiroResRes = findViewById(R.id.fieldsGoleiroResRes);
        tvJogadorExtra = findViewById(R.id.fieldsJogadorExtra);
        tvNomeEquipe = findViewById(R.id.txtNomeEquipe);

        //PEGA Os CAMPO ENVIADOS DA INTENT MEIO_EVENTOS e preenche
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        tvGoleiro.setText(extras.getString("tvGoleiro"));
        tvFixo.setText(extras.getString("tvFixo"));
        tvAlaEsq.setText(extras.getString("tvAlaEsq"));
        tvAlaDir.setText(extras.getString("tvAlaDir"));
        tvPivo.setText(extras.getString("tvPivo"));
        tvGoleiroRes.setText(extras.getString("tvGoleiroRes"));
        tvFixoRes.setText(extras.getString("tvFixoRes"));
        tvAlaEsqRes.setText(extras.getString("tvAlaEsqRes"));
        tvAlaDirRes.setText(extras.getString("tvAlaDirRes"));
        tvPivoRes.setText(extras.getString("tvPivoRes"));
        tvGoleiroResRes.setText(extras.getString("tvGoleiroResRes"));
        tvJogadorExtra.setText(extras.getString("tvJogadorExtra"));
        tvNomeEquipe.setText(extras.getString("tvApelidoEquipe"));
        final String stNomeAdv = extras.getString("nomeAdversario");
        final String IDAdversario = extras.getString("ID_adversario");
        final String stIDTorneio = extras.getString("ID_torneio");
        final String stNomeTorneio = extras.getString("nomeTorneio");
        final String stDataJogo = extras.getString("dataJogo");

        tvGoleiroAtleta.setText(extras.getString("tvGoleiro"));
        tvFixoAtleta.setText(extras.getString("tvFixo"));
        tvAlaEsqAtleta.setText(extras.getString("tvAlaEsq"));
        tvAlaDirAtleta.setText(extras.getString("tvAlaDir"));
        tvPivoAtleta.setText(extras.getString("tvPivo"));
        tvGoleiroAtletaRes.setText(extras.getString("tvGoleiroRes"));
        tvFixoAtletaRes.setText(extras.getString("tvFixoRes"));
        tvAlaEsqAtletaRes.setText(extras.getString("tvAlaEsqRes"));
        tvAlaDirAtletaRes.setText(extras.getString("tvAlaDirRes"));
        tvPivoAtletaRes.setText(extras.getString("tvPivoRes"));
        tvGoleiroAtletaResRes.setText(extras.getString("tvGoleiroResRes"));
        tvJogExtraAtleta.setText(extras.getString("tvJogadorExtra"));

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String strIDTorneio =  stIDTorneio;
                final String strNomeTorneio = stNomeTorneio;
                final String strIDAdversario =  IDAdversario;
                final String strNomeAdversario = stNomeAdv;
                final String strApelidoEquipe = tvNomeEquipe.getText().toString();
                final String strDataJogo = stDataJogo;

                final String strGoleiroAtleta= (String) tvGoleiro.getText();
                final String strGoleiroPasseErrado = (String) tvGoleiroPasseErrado.getText();
                final String strGoleiroChuteAgol = (String) tvGoleiroChuteAgol.getText();
                final String strGoleiroPerdida = (String) tvGoleiroPerdida.getText();
                final String strGoleiroInterceptacao = (String) tvGoleiroInterceptacao.getText();

                final String strFixoAtleta= (String) tvFixo.getText();
                final String strFixoPasseErrado = (String) tvFixoPasseErrado.getText();
                final String strFixoChuteAgol = (String) tvFixoChuteAgol.getText();
                final String strFixoPerdida = (String) tvFixoPerdida.getText();
                final String strFixoInterceptacao = (String) tvFixoInterceptacao.getText();

                final String strAlaEsqAtleta= (String) tvAlaEsq.getText();
                final String strAlaEsqPasseErrado = (String) tvAlaEsqPasseErrado.getText();
                final String strAlaEsqChuteAgol = (String) tvAlaEsqChuteAgol.getText();
                final String strAlaEsqPerdida = (String) tvAlaEsqPerdida.getText();
                final String strAlaEsqInterceptacao = (String) tvAlaEsqInterceptacao.getText();

                final String strAlaDirAtleta= (String) tvAlaDir.getText();
                final String strAlaDirPasseErrado = (String) tvAlaDirPasseErrado.getText();
                final String strAlaDirChuteAgol = (String) tvAlaDirChuteAgol.getText();
                final String strAlaDirPerdida = (String) tvAlaDirPerdida.getText();
                final String strAlaDirInterceptacao = (String) tvAlaDirInterceptacao.getText();

                final String strPivoAtleta= (String) tvPivo.getText();
                final String strPivoPasseErrado = (String) tvPivoPasseErrado.getText();
                final String strPivoChuteAgol = (String) tvPivoChuteAgol.getText();
                final String strPivoPerdida = (String) tvPivoPerdida.getText();
                final String strPivoInterceptacao = (String) tvPivoInterceptacao.getText();

                final String strGoleiroAtletaRes = (String) tvGoleiroRes.getText();
                final String strGoleiroPasseErradoRes = (String) tvGoleiroPasseErradoRes.getText();
                final String strGoleiroChuteAgolRes = (String) tvGoleiroChuteAgolRes.getText();
                final String strGoleiroPerdidaRes = (String) tvGoleiroPerdidaRes.getText();
                final String strGoleiroInterceptacaoRes = (String) tvGoleiroInterceptacaoRes.getText();

                final String strFixoAtletaRes = (String) tvFixoRes.getText();
                final String strFixoPasseErradoRes = (String) tvFixoPasseErradoRes.getText();
                final String strFixoChuteAgolRes = (String) tvFixoChuteAgolRes.getText();
                final String strFixoPerdidaRes = (String) tvFixoPerdidaRes.getText();
                final String strFixoInterceptacaoRes = (String) tvFixoInterceptacaoRes.getText();

                final String strAlaEsqAtletaRes = (String) tvAlaEsqRes.getText();
                final String strAlaEsqPasseErradoRes = (String) tvAlaEsqPasseErradoRes.getText();
                final String strAlaEsqChuteAgolRes = (String) tvAlaEsqChuteAgolRes.getText();
                final String strAlaEsqPerdidaRes = (String) tvAlaEsqPerdidaRes.getText();
                final String strAlaEsqInterceptacaoRes = (String) tvAlaEsqInterceptacaoRes.getText();

                final String strAlaDirAtletaRes = (String) tvAlaDirRes.getText();
                final String strAlaDirPasseErradoRes = (String) tvAlaDirPasseErradoRes.getText();
                final String strAlaDirChuteAgolRes = (String) tvAlaDirChuteAgolRes.getText();
                final String strAlaDirPerdidaRes = (String) tvAlaDirPerdidaRes.getText();
                final String strAlaDirInterceptacaoRes = (String) tvAlaDirInterceptacaoRes.getText();

                final String strPivoAtletaRes = (String) tvPivoRes.getText();
                final String strPivoPasseErradoRes = (String) tvPivoPasseErradoRes.getText();
                final String strPivoChuteAgolRes = (String) tvPivoChuteAgolRes.getText();
                final String strPivoPerdidaRes = (String) tvPivoPerdidaRes.getText();
                final String strPivoInterceptacaoRes = (String) tvPivoInterceptacaoRes.getText();

                final String strGoleiroAtletaResRes = (String) tvGoleiroResRes.getText();
                final String strGoleiroPasseErradoResRes = (String) tvGoleiroPasseErradoResRes.getText();
                final String strGoleiroChuteAgolResRes = (String) tvGoleiroChuteAgolResRes.getText();
                final String strGoleiroPerdidaResRes = (String) tvGoleiroPerdidaResRes.getText();
                final String strGoleiroInterceptacaoResRes = (String) tvGoleiroInterceptacaoResRes.getText();

                final String strJogExtraAtleta = (String) tvJogadorExtra.getText();
                final String strJogExtraPasseErrado = (String) tvJogExtraPasseErrado.getText();
                final String strJogExtraChuteAgol = (String) tvJogExtraChuteAgol.getText();
                final String strJogExtraPerdida = (String) tvJogExtraPerdida.getText();
                final String strJogExtraInterceptacao = (String) tvJogExtraInterceptacao.getText();

                RequestQueue queue = Volley.newRequestQueue(ClicarEventosFutsal.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLev, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog = new AlertDialog.Builder(ClicarEventosFutsal.this);
                        alertDialog.setMessage(response);
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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
                                Toast.makeText(ClicarEventosFutsal.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("ID_torneio", strIDTorneio);
                        params.put("nomeTorneio", strNomeTorneio);
                        params.put("apelidoEquipe", strApelidoEquipe);
                        params.put("ID_adversario", strIDAdversario);
                        params.put("nomeAdversario", strNomeAdversario);
                        params.put("dataJogo", strDataJogo);

                        //params.put("dataJogo1", strDataJogo1);
                        params.put("atleta1", strGoleiroAtleta);
                        params.put("passeErrado1", strGoleiroPasseErrado);
                        params.put("chuteAgol1", strGoleiroChuteAgol);
                        params.put("perdida1", strGoleiroPerdida);
                        params.put("interceptacao1", strGoleiroInterceptacao);

                        //params.put("dataJogo2", strDataJogo2);
                        params.put("atleta2", strFixoAtleta);
                        params.put("passeErrado2", strFixoPasseErrado);
                        params.put("chuteAgol2", strFixoChuteAgol);
                        params.put("perdida2", strFixoPerdida);
                        params.put("interceptacao2", strFixoInterceptacao);

                        //params.put("dataJogo3", strDataJogo3);
                        params.put("atleta3", strAlaEsqAtleta);
                        params.put("passeErrado3", strAlaEsqPasseErrado);
                        params.put("chuteAgol3", strAlaEsqChuteAgol);
                        params.put("perdida3", strAlaEsqPerdida);
                        params.put("interceptacao3", strAlaEsqInterceptacao);

                        //params.put("dataJogo4", strDataJogo4);
                        params.put("atleta4", strAlaDirAtleta);
                        params.put("passeErrado4", strAlaDirPasseErrado);
                        params.put("chuteAgol4", strAlaDirChuteAgol);
                        params.put("perdida4", strAlaDirPerdida);
                        params.put("interceptacao4", strAlaDirInterceptacao);

                        //params.put("dataJogo5", strDataJogo5);
                        params.put("atleta5", strPivoAtleta);
                        params.put("passeErrado5", strPivoPasseErrado);
                        params.put("chuteAgol5", strPivoChuteAgol);
                        params.put("perdida5", strPivoPerdida);
                        params.put("interceptacao5", strPivoInterceptacao);

                        params.put("atleta6", strGoleiroAtletaRes);
                        params.put("passeErrado6", strGoleiroPasseErradoRes);
                        params.put("chuteAgol6", strGoleiroChuteAgolRes);
                        params.put("perdida6", strGoleiroPerdidaRes);
                        params.put("interceptacao6", strGoleiroInterceptacaoRes);

                        params.put("atleta7", strFixoAtletaRes);
                        params.put("passeErrado7", strFixoPasseErradoRes);
                        params.put("chuteAgol7", strFixoChuteAgolRes);
                        params.put("perdida7", strFixoPerdidaRes);
                        params.put("interceptacao7", strFixoInterceptacaoRes);

                        params.put("atleta8", strAlaEsqAtletaRes);
                        params.put("passeErrado8", strAlaEsqPasseErradoRes);
                        params.put("chuteAgol8", strAlaEsqChuteAgolRes);
                        params.put("perdida8", strAlaEsqPerdidaRes);
                        params.put("interceptacao8", strAlaEsqInterceptacaoRes);

                        params.put("atleta9", strAlaDirAtletaRes);
                        params.put("passeErrado9", strAlaDirPasseErradoRes);
                        params.put("chuteAgol9", strAlaDirChuteAgolRes);
                        params.put("perdida9", strAlaDirPerdidaRes);
                        params.put("interceptacao9", strAlaDirInterceptacaoRes);

                        params.put("atleta10", strPivoAtletaRes);
                        params.put("passeErrado10", strPivoPasseErradoRes);
                        params.put("chuteAgol10", strPivoChuteAgolRes);
                        params.put("perdida10", strPivoPerdidaRes);
                        params.put("interceptacao10", strPivoInterceptacaoRes);

                        params.put("atleta11", strGoleiroAtletaResRes);
                        params.put("passeErrado11", strGoleiroPasseErradoResRes);
                        params.put("chuteAgol11", strGoleiroChuteAgolResRes);
                        params.put("perdida11", strGoleiroPerdidaResRes);
                        params.put("interceptacao11", strGoleiroInterceptacaoResRes);

                        params.put("atleta12", strJogExtraAtleta);
                        params.put("passeErrado12", strJogExtraPasseErrado);
                        params.put("chuteAgol12", strJogExtraChuteAgol);
                        params.put("perdida12", strJogExtraPerdida);
                        params.put("interceptacao12", strJogExtraInterceptacao);


                        return params;
                    }
                };
                queue.add(stringRequest);

                tvGoleiro.setText(null);
                tvFixo.setText(null);
                tvAlaEsq.setText(null);
                tvAlaDir.setText(null);
                tvPivo.setText(null);
                tvGoleiroRes.setText(null);

                tvFixoRes.setText(null);
                tvAlaEsqRes.setText(null);
                tvAlaDirRes.setText(null);
                tvPivoRes.setText(null);
                tvGoleiroResRes.setText(null);
                tvJogadorExtra.setText(null);

                tvGoleiroAtleta.setText(null);
                tvGoleiroPasseErrado.setText(null);
                tvGoleiroChuteAgol.setText(null);
                tvGoleiroPerdida.setText(null);
                tvGoleiroInterceptacao.setText(null);

                tvFixoAtleta.setText(null);
                tvFixoPasseErrado.setText(null);
                tvFixoChuteAgol.setText(null);
                tvFixoPerdida.setText(null);
                tvFixoInterceptacao.setText(null);

                tvAlaEsqAtleta.setText(null);
                tvAlaEsqPasseErrado.setText(null);
                tvAlaEsqChuteAgol.setText(null);
                tvAlaEsqPerdida.setText(null);
                tvAlaEsqInterceptacao.setText(null);

                tvAlaDirAtleta.setText(null);
                tvAlaDirPasseErrado.setText(null);
                tvAlaDirChuteAgol.setText(null);
                tvAlaDirPerdida.setText(null);
                tvAlaDirInterceptacao.setText(null);

                tvPivoAtleta.setText(null);
                tvPivoPasseErrado.setText(null);
                tvPivoChuteAgol.setText(null);
                tvPivoPerdida.setText(null);
                tvPivoInterceptacao.setText(null);

                tvGoleiroAtletaRes.setText(null);
                tvGoleiroPasseErradoRes.setText(null);
                tvGoleiroChuteAgolRes.setText(null);
                tvGoleiroPerdidaRes.setText(null);
                tvGoleiroInterceptacaoRes.setText(null);

                tvFixoAtletaRes.setText(null);
                tvFixoPasseErradoRes.setText(null);
                tvFixoChuteAgolRes.setText(null);
                tvFixoPerdidaRes.setText(null);
                tvFixoInterceptacaoRes.setText(null);

                tvAlaEsqAtletaRes.setText(null);
                tvAlaEsqPasseErradoRes.setText(null);
                tvAlaEsqChuteAgolRes.setText(null);
                tvAlaEsqPerdidaRes.setText(null);
                tvAlaEsqInterceptacaoRes.setText(null);

                tvAlaDirAtletaRes.setText(null);
                tvAlaDirPasseErradoRes.setText(null);
                tvAlaDirChuteAgolRes.setText(null);
                tvAlaDirPerdidaRes.setText(null);
                tvAlaDirInterceptacaoRes.setText(null);

                tvPivoAtletaRes.setText(null);
                tvPivoPasseErradoRes.setText(null);
                tvPivoChuteAgolRes.setText(null);
                tvPivoPerdidaRes.setText(null);
                tvPivoInterceptacaoRes.setText(null);

                tvGoleiroAtletaResRes.setText(null);
                tvGoleiroPasseErradoResRes.setText(null);
                tvGoleiroChuteAgolResRes.setText(null);
                tvGoleiroPerdidaResRes.setText(null);
                tvGoleiroInterceptacaoResRes.setText(null);

                tvJogadorExtra.setText(null);
                tvJogExtraAtleta.setText(null);
                tvJogExtraPasseErrado.setText(null);
                tvJogExtraChuteAgol.setText(null);
                tvJogExtraPerdida.setText(null);
                tvJogExtraInterceptacao.setText(null);
            }
        });
    }

    // Função que separa os números de dentro de uma String
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


    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrGoleiro = 0;
    int chutGoleiro = 0;
    int perdidasGoleiro = 0;
    int interceptGoleiro = 0;

    //*****************************GOLEIRO*******************************************
    //GOLEIRO PASSES ERRADOS
    public void maisPasserGoleiro(View view) {
        passErrGoleiro = passErrGoleiro + 1;
        totalPasserGoleiro(passErrGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: +1 pass err: tot: "+passErrGoleiro, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserGoleiro(View view){
        do{ //faça
            passErrGoleiro = passErrGoleiro - 1; //pede numero
            if(passErrGoleiro < 0){
                passErrGoleiro = 0; //lança exceção
            }
        } while(passErrGoleiro < 0);
        totalPasserGoleiro(passErrGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: - pass err: tot: "+passErrGoleiro, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserGoleiro(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_goleiro);
        displayInteger.setText("" + number);
    }

    //GOLEIRO CHUTE A GOL
    public void maisChutesGoleiro(View view) {
        chutGoleiro = chutGoleiro + 1;
        totalChutesGoleiro(chutGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: +1 chut a gol: tot: "+chutGoleiro, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesGoleiro(View view){
        do{ //faça
            chutGoleiro = chutGoleiro - 1; //pede numero
            if(chutGoleiro < 0){
                chutGoleiro = 0; //lança exceção
            }
        } while(chutGoleiro < 0);
        totalChutesGoleiro(chutGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: -1 chut a gol: tot: "+chutGoleiro, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesGoleiro(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_goleiro);
        displayInteger.setText("" + number);
    }

    //GOLEIRO PERDIDAS
    public void maisPerdidasGoleiro(View view) {
        perdidasGoleiro = perdidasGoleiro + 1;
        totalPerdidasGoleiro(perdidasGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: +1 perdida: tot: "+perdidasGoleiro, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasGoleiro(View view){
        do{
            perdidasGoleiro = perdidasGoleiro - 1;
            if(perdidasGoleiro < 0){
                perdidasGoleiro = 0;
            }
        } while(perdidasGoleiro < 0);
        totalPerdidasGoleiro(perdidasGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: -1 perdida: tot: "+perdidasGoleiro, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasGoleiro(int number) {
        TextView displayInteger = findViewById(R.id.perdida_goleiro);
        displayInteger.setText("" + number);
    }

    //GOLEIRO INTERCEPTACOES
    public void maisInterceptGoleiro(View view) {
        interceptGoleiro = interceptGoleiro + 1;
        totalInterceptGoleiro(interceptGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: +1 interceptada: tot: "+interceptGoleiro, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptGoleiro(View view){
        do{
            interceptGoleiro = interceptGoleiro - 1;
            if(interceptGoleiro < 0){
                interceptGoleiro = 0;
            }
        } while(interceptGoleiro < 0);
        totalInterceptGoleiro(interceptGoleiro);
        Toast.makeText(getApplicationContext(), "Goleiro: -1 interceptada: tot: "+interceptGoleiro, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptGoleiro(int number) {
        TextView displayInteger = findViewById(R.id.intercep_goleiro);
        displayInteger.setText("" + number);
    }


    //*****************************fixo*******************************************
    int passErrFixo = 0;
    int chutFixo = 0;
    int perdidasFixo = 0;
    int interceptFixo = 0;
    //fixo PASSES ERRADOS
    public void maisPasserFixo(View view) {
        passErrFixo = passErrFixo + 1;
        totalPasserFixo(passErrFixo);
        Toast.makeText(getApplicationContext(), "Fixo: +1 pass err: tot: "+passErrFixo, Toast.LENGTH_LONG).show();
    }

    public void menosPasserFixo(View view){
        do{ //faça
            passErrFixo = passErrFixo - 1; //pede numero
            if(passErrFixo < 0){
                passErrFixo = 0; //lança exceção
            }
        } while(passErrFixo < 0);
        totalPasserFixo(passErrFixo);
        Toast.makeText(getApplicationContext(), "Fixo: -1 pass err: tot: "+passErrFixo, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserFixo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_fixo);
        displayInteger.setText("" + number);
    }

    //fixo CHUTE A GOL
    public void maisChutesFixo(View view) {
        chutFixo = chutFixo + 1;
        totalChutesFixo(chutFixo);
        Toast.makeText(getApplicationContext(), "Fixo: +1 chut a gol: tot: "+chutFixo, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesFixo(View view){
        do{ //faça
            chutFixo = chutFixo - 1; //pede numero
            if(chutFixo < 0){
                chutFixo = 0; //lança exceção
            }
        } while(chutFixo < 0);
        totalChutesFixo(chutFixo);
        Toast.makeText(getApplicationContext(), "Fixo: -1 chut a gol: tot: "+chutFixo, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesFixo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_fixo);
        displayInteger.setText("" + number);
    }






    //Fixo PERDIDAS
    public void maisPerdidasFixo(View view) {
        perdidasFixo = perdidasFixo + 1;
        totalPerdidasFixo(perdidasFixo);
        Toast.makeText(getApplicationContext(), "Fixo: +1 perdidas: tot: "+perdidasFixo, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasFixo(View view){
        do{ //faça
            perdidasFixo = perdidasFixo - 1; //pede numero
            if(perdidasFixo < 0){
                perdidasFixo = 0; //lança exceção
            }
        } while(perdidasFixo < 0);
        totalPerdidasFixo(perdidasFixo);
        Toast.makeText(getApplicationContext(), "Fixo: -1 perdidas: tot: "+perdidasFixo, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasFixo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.perdida_fixo);
        displayInteger.setText("" + number);
    }

    //Fixo intercepatadas
    public void maisInterceptFixo(View view) {
        interceptFixo = interceptFixo + 1;
        totalInterceptFixo(interceptFixo);
        Toast.makeText(getApplicationContext(), "Fixo: +1 interceptada: tot: "+interceptFixo, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptFixo(View view){
        do{
            interceptFixo = interceptFixo - 1;
            if(interceptFixo < 0){
                interceptFixo = 0;
            }
        } while(interceptFixo < 0);
        totalInterceptFixo(interceptFixo);
        Toast.makeText(getApplicationContext(), "Fixo: -1 interceptada: tot: "+interceptFixo, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptFixo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.intercep_fixo);
        displayInteger.setText("" + number);
    }



    //*****************************ALA ESQUERDO*******************************************
    int passAlaEsq = 0;
    int chutAlaEsq = 0;
    int perdidasAlaEsq = 0;
    int interceptAlaEsq = 0;
    //fixo PASSES ERRADOS
    public void maisPasserAlaEsq(View view) {
        passAlaEsq = passAlaEsq + 1;
        totalPasserAlaEsq(passAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: +1 pass err: tot: "+passAlaEsq, Toast.LENGTH_LONG).show();
    }

    public void menosPasserAlaEsq(View view){
        do{ //faça
            passAlaEsq = passAlaEsq - 1; //pede numero
            if(passAlaEsq < 0){
                passAlaEsq = 0; //lança exceção
            }
        } while(passAlaEsq < 0);
        totalPasserAlaEsq(passAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: -1 pass err: tot: "+passAlaEsq, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserAlaEsq(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_alaesq);
        displayInteger.setText("" + number);
    }

    //fixo CHUTE A GOL
    public void maisChutesAlaEsq(View view) {
        chutAlaEsq = chutAlaEsq + 1;
        totalChutesAlaEsq(chutAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: +1 chut a gol: tot: "+chutAlaEsq, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesAlaEsq(View view){
        do{ //faça
            chutAlaEsq = chutAlaEsq - 1; //pede numero
            if(chutAlaEsq < 0){
                chutAlaEsq = 0; //lança exceção
            }
        } while(chutAlaEsq < 0);
        totalChutesAlaEsq(chutAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: -1 chut a gol: tot: "+chutAlaEsq, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesAlaEsq(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_alaesq);
        displayInteger.setText("" + number);
    }

    //Fixo PERDIDAS
    public void maisPerdidasAlaEsq(View view) {
        perdidasAlaEsq = perdidasAlaEsq + 1;
        totalPerdidasAlaEsq(perdidasAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: +1 perdidas: tot: "+perdidasAlaEsq, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasAlaEsq(View view){
        do{ //faça
            perdidasAlaEsq = perdidasAlaEsq - 1; //pede numero
            if(perdidasAlaEsq < 0){
                perdidasAlaEsq = 0; //lança exceção
            }
        } while(perdidasFixo < 0);
        totalPerdidasAlaEsq(perdidasAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: -1 perdidas: tot: "+perdidasAlaEsq, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasAlaEsq(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.perdida_alaesq);
        displayInteger.setText("" + number);
    }

    //Fixo intercepatadas
    public void maisInterceptAlaEsq(View view) {
        interceptAlaEsq = interceptAlaEsq + 1;
        totalInterceptAlaEsq(interceptAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: +1 interceptada: tot: "+interceptAlaEsq, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptAlaEsq(View view){
        do{
            interceptAlaEsq = interceptAlaEsq - 1;
            if(interceptAlaEsq < 0){
                interceptAlaEsq = 0;
            }
        } while(interceptAlaEsq < 0);
        totalInterceptAlaEsq(interceptAlaEsq);
        Toast.makeText(getApplicationContext(), "Ala Esq: -1 interceptada: tot: "+interceptAlaEsq, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptAlaEsq(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.intercep_alaesq);
        displayInteger.setText("" + number);
    }




    //*****************************ALA DIREITO*******************************************
    int passAlaDir = 0;
    int chutAlaDir = 0;
    int perdidasAlaDir = 0;
    int interceptAlaDir = 0;
    //fixo PASSES ERRADOS
    public void maisPasserAlaDir(View view) {
        passAlaDir = passAlaDir + 1;
        totalPasserAlaDir(passAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 pass err: tot: "+passAlaDir, Toast.LENGTH_LONG).show();
    }

    public void menosPasserAlaDir(View view){
        do{ //faça
            passAlaDir = passAlaDir - 1; //pede numero
            if(passAlaDir < 0){
                passAlaDir = 0; //lança exceção
            }
        } while(passAlaDir < 0);
        totalPasserAlaDir(passAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 pass err: tot: "+passAlaDir, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserAlaDir(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_aladir);
        displayInteger.setText("" + number);
    }

    //fixo CHUTE A GOL
    public void maisChutesAlaDir(View view) {
        chutAlaDir = chutAlaDir + 1;
        totalChutesAlaDir(chutAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 chut a gol: tot: "+chutAlaDir, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesAlaDir(View view){
        do{ //faça
            chutAlaDir = chutAlaDir - 1; //pede numero
            if(chutAlaDir < 0){
                chutAlaDir = 0; //lança exceção
            }
        } while(chutAlaDir < 0);
        totalChutesAlaDir(chutAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 chut a gol: tot: "+chutAlaDir, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesAlaDir(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_aladir);
        displayInteger.setText("" + number);
    }

    //Fixo PERDIDAS
    public void maisPerdidasAlaDir(View view) {
        perdidasAlaDir = perdidasAlaDir + 1;
        totalPerdidasAlaDir(perdidasAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 perdidas: tot: "+perdidasAlaDir, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasAlaDir(View view){
        do{ //faça
            perdidasAlaDir = perdidasAlaDir - 1; //pede numero
            if(perdidasAlaDir < 0){
                perdidasAlaDir = 0; //lança exceção
            }
        } while(perdidasFixo < 0);
        totalPerdidasAlaDir(perdidasAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 perdidas: tot: "+perdidasAlaDir, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasAlaDir(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.perdida_aladir);
        displayInteger.setText("" + number);
    }

    //Fixo intercepatadas
    public void maisInterceptAlaDir(View view) {
        interceptAlaDir = interceptAlaDir + 1;
        totalInterceptAlaDir(interceptAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 interceptada: tot: "+interceptAlaDir, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptAlaDir(View view){
        do{
            interceptAlaDir = interceptAlaDir - 1;
            if(interceptAlaDir < 0){
                interceptAlaDir = 0;
            }
        } while(interceptAlaDir < 0);
        totalInterceptAlaDir(interceptAlaDir);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 interceptada: tot: "+interceptAlaDir, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptAlaDir(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.intercep_aladir);
        displayInteger.setText("" + number);
    }



    //*****************************PIVO*******************************************
    int passPivo = 0;
    int chutPivo = 0;
    int perdidasPivo = 0;
    int interceptPivo = 0;
    //fixo PASSES ERRADOS
    public void maisPasserPivo(View view) {
        passPivo = passPivo + 1;
        totalPasserPivo(passPivo);
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 pass err: tot: "+passPivo, Toast.LENGTH_LONG).show();
    }

    public void menosPasserPivo(View view){
        do{ //faça
            passPivo = passPivo - 1; //pede numero
            if(passPivo < 0){
                passPivo = 0; //lança exceção
            }
        } while(passPivo < 0);
        totalPasserPivo(passPivo);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 pass err: tot: "+passPivo, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserPivo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_pivo);
        displayInteger.setText("" + number);
    }

    //fixo CHUTE A GOL
    public void maisChutesPivo(View view) {
        chutPivo = chutPivo + 1;
        totalChutesPivo(chutPivo);
        Toast.makeText(getApplicationContext(), "Pivo: +1 chut a gol: tot: "+chutPivo, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesPivo(View view){
        do{ //faça
            chutPivo = chutPivo - 1;
            if(chutPivo < 0){
                chutPivo = 0;
            }
        } while(chutPivo < 0);
        totalChutesPivo(chutPivo);
        Toast.makeText(getApplicationContext(), "Pivo: -1 chut a gol: tot: "+chutPivo, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesPivo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_pivo);
        displayInteger.setText("" + number);
    }

    //Fixo PERDIDAS
    public void maisPerdidasPivo(View view) {
        perdidasPivo = perdidasPivo + 1;
        totalPerdidasPivo(perdidasPivo);
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 perdidas: tot: "+perdidasPivo, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasPivo(View view){
        do{ //faça
            perdidasPivo = perdidasPivo - 1; //pede numero
            if(perdidasPivo < 0){
                perdidasPivo = 0; //lança exceção
            }
        } while(perdidasFixo < 0);
        totalPerdidasPivo(perdidasPivo);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 perdidas: tot: "+perdidasPivo, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasPivo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.perdida_pivo);
        displayInteger.setText("" + number);
    }

    //Fixo intercepatadas
    public void maisInterceptPivo(View view) {
        interceptPivo = interceptPivo + 1;
        totalInterceptPivo(interceptPivo);
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 interceptada: tot: "+interceptPivo, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptPivo(View view){
        do{
            interceptPivo = interceptPivo - 1;
            if(interceptPivo < 0){
                interceptPivo = 0;
            }
        } while(interceptPivo < 0);
        totalInterceptPivo(interceptPivo);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 interceptada: tot: "+interceptPivo, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptPivo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.intercep_pivo);
        displayInteger.setText("" + number);
    }



    public String removerCaracteresEspeciais(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }



    //hoje +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrGoleiroRes = 0;
    int chutGoleiroRes = 0;
    int perdidasGoleiroRes = 0;
    int interceptGoleiroRes = 0;

    //*****************************GOLEIRO*******************************************
    //GOLEIRO PASSES ERRADOS
    public void maisPasserGoleiroRes(View view) {
        passErrGoleiroRes = passErrGoleiroRes + 1;
        totalPasserGoleiroRes(passErrGoleiroRes);
        Toast.makeText(getApplicationContext(), "Goleiro: +1 pass err: tot: "+passErrGoleiroRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserGoleiroRes(View view){
        do{ //faça
            passErrGoleiroRes = passErrGoleiroRes - 1; //pede numero
            if(passErrGoleiroRes < 0){
                passErrGoleiroRes = 0; //lança exceção
            }
        } while(passErrGoleiroRes < 0);
        totalPasserGoleiroRes(passErrGoleiroRes);
        Toast.makeText(getApplicationContext(), "Goleiro: - pass err: tot: "+passErrGoleiro, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserGoleiroRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_goleiroRes);
        displayInteger.setText("" + number);
    }

    //GOLEIRO CHUTE A GOL
    public void maisChutesGoleiroRes(View view) {
        chutGoleiroRes = chutGoleiroRes + 1;
        totalChutesGoleiroRes(chutGoleiroRes);
        Toast.makeText(getApplicationContext(), "Goleiro: +1 chut a gol: tot: "+chutGoleiroRes, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesGoleiroRes(View view){
        do{ //faça
            chutGoleiroRes = chutGoleiroRes - 1; //pede numero
            if(chutGoleiroRes < 0){
                chutGoleiroRes = 0; //lança exceção
            }
        } while(chutGoleiroRes < 0);
        totalChutesGoleiroRes(chutGoleiroRes);
        Toast.makeText(getApplicationContext(), "Goleiro: -1 chut a gol: tot: "+chutGoleiroRes, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesGoleiroRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_goleiroRes);
        displayInteger.setText("" + number);
    }

    //GOLEIRORES PERDIDAS
    public void maisPerdidasGoleiroRes(View view) {
        perdidasGoleiroRes = perdidasGoleiroRes + 1;
        totalPerdidasGoleiroRes(perdidasGoleiroRes);
        Toast.makeText(getApplicationContext(), "GoleiroR: +1 perdida: tot: "+perdidasGoleiroRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasGoleiroRes(View view){
        do{
            perdidasGoleiroRes = perdidasGoleiroRes - 1;
            if(perdidasGoleiroRes < 0){
                perdidasGoleiroRes = 0;
            }
        } while(perdidasGoleiroRes < 0);
        totalPerdidasGoleiroRes(perdidasGoleiroRes);
        Toast.makeText(getApplicationContext(), "GoleiroR: -1 perdida: tot: "+perdidasGoleiroRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasGoleiroRes(int number) {
        TextView displayInteger = findViewById(R.id.perdida_goleiroRes);
        displayInteger.setText("" + number);
    }

    //GOLEIRORES INTERCEPTACOES
    public void maisInterceptGoleiroRes(View view) {
        interceptGoleiroRes = interceptGoleiroRes + 1;
        totalInterceptGoleiroRes(interceptGoleiroRes);
        Toast.makeText(getApplicationContext(), "GoleiroRes: +1 interceptada: tot: "+interceptGoleiroRes, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptGoleiroRes(View view){
        do{
            interceptGoleiroRes = interceptGoleiroRes - 1;
            if(interceptGoleiroRes < 0){
                interceptGoleiroRes = 0;
            }
        } while(interceptGoleiroRes < 0);
        totalInterceptGoleiroRes(interceptGoleiroRes);
        Toast.makeText(getApplicationContext(), "GoleiroRes: -1 interceptada: tot: "+interceptGoleiroRes, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptGoleiroRes(int number) {
        TextView displayInteger = findViewById(R.id.intercep_goleiroRes);
        displayInteger.setText("" + number);
    }





    //hoje +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrFixoRes = 0;
    int chutFixoRes = 0;
    int perdidasFixoRes = 0;
    int interceptFixoRes = 0;

    //*****************************FixoRes*******************************************
    //FixoRes PASSES ERRADOS
    public void maisPasserFixoRes(View view) {
        passErrFixoRes = passErrFixoRes + 1;
        totalPasserFixoRes(passErrFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: +1 pass err: tot: "+passErrFixoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserFixoRes(View view){
        do{ //faça
            passErrFixoRes = passErrFixoRes - 1; //pede numero
            if(passErrFixoRes < 0){
                passErrFixoRes = 0; //lança exceção
            }
        } while(passErrFixoRes < 0);
        totalPasserFixoRes(passErrFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: - pass err: tot: "+passErrFixoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserFixoRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_fixoRes);
        displayInteger.setText("" + number);
    }

    //FixoRes CHUTE A GOL
    public void maisChutesFixoRes(View view) {
        chutFixoRes = chutFixoRes + 1;
        totalChutesFixoRes(chutFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: +1 chut a gol: tot: "+chutFixoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesFixoRes(View view){
        do{ //faça
            chutFixoRes = chutFixoRes - 1; //pede numero
            if(chutFixoRes < 0){
                chutFixoRes = 0; //lança exceção
            }
        } while(chutFixoRes < 0);
        totalChutesFixoRes(chutFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: -1 chut a gol: tot: "+chutFixoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesFixoRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_fixoRes);
        displayInteger.setText("" + number);
    }

    //FixoRes PERDIDAS
    public void maisPerdidasFixoRes(View view) {
        perdidasFixoRes = perdidasFixoRes + 1;
        totalPerdidasFixoRes(perdidasFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: +1 perdida: tot: "+perdidasFixoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasFixoRes(View view){
        do{
            perdidasFixoRes = perdidasFixoRes - 1;
            if(perdidasFixoRes < 0){
                perdidasFixoRes = 0;
            }
        } while(perdidasFixoRes < 0);
        totalPerdidasFixoRes(perdidasFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: -1 perdida: tot: "+perdidasFixoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasFixoRes(int number) {
        TextView displayInteger = findViewById(R.id.perdida_fixoRes);
        displayInteger.setText("" + number);
    }

    //FixoRes INTERCEPTACOES
    public void maisInterceptFixoRes(View view) {
        interceptFixoRes = interceptFixoRes + 1;
        totalInterceptFixoRes(interceptFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: +1 interceptada: tot: "+interceptFixoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptFixoRes(View view){
        do{
            interceptFixoRes = interceptFixoRes - 1;
            if(interceptFixoRes < 0){
                interceptFixoRes = 0;
            }
        } while(interceptFixoRes < 0);
        totalInterceptFixoRes(interceptFixoRes);
        Toast.makeText(getApplicationContext(), "FixoRes: -1 interceptada: tot: "+interceptFixoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptFixoRes(int number) {
        TextView displayInteger = findViewById(R.id.intercep_fixoRes);
        displayInteger.setText("" + number);
    }


    //hoje +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrAlaEsqRes = 0;
    int chutAlaEsqRes = 0;
    int perdidasAlaEsqRes = 0;
    int interceptAlaEsqRes = 0;

    //*****************************AlaEsqRes*******************************************
    //AlaEsqRes PASSES ERRADOS
    public void maisPasserAlaEsqRes(View view) {
        passErrAlaEsqRes = passErrAlaEsqRes + 1;
        totalPasserAlaEsqRes(passErrAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: +1 pass err: tot: "+passErrAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserAlaEsqRes(View view){
        do{ //faça
            passErrAlaEsqRes = passErrAlaEsqRes - 1; //pede numero
            if(passErrAlaEsqRes < 0){
                passErrAlaEsqRes = 0; //lança exceção
            }
        } while(passErrAlaEsqRes < 0);
        totalPasserAlaEsqRes(passErrAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: - pass err: tot: "+passErrAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserAlaEsqRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_alaesqRes);
        displayInteger.setText("" + number);
    }

    //AlaEsqRes CHUTE A GOL
    public void maisChutesAlaEsqRes(View view) {
        chutAlaEsqRes = chutAlaEsqRes + 1;
        totalChutesAlaEsqRes(chutAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: +1 chut a gol: tot: "+chutAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesAlaEsqRes(View view){
        do{ //faça
            chutAlaEsqRes = chutAlaEsqRes - 1; //pede numero
            if(chutAlaEsqRes < 0){
                chutAlaEsqRes = 0; //lança exceção
            }
        } while(chutAlaEsqRes < 0);
        totalChutesAlaEsqRes(chutAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: -1 chut a gol: tot: "+chutAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesAlaEsqRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_alaesqRes);
        displayInteger.setText("" + number);
    }

    //AlaEsqRes PERDIDAS
    public void maisPerdidasAlaEsqRes(View view) {
        perdidasAlaEsqRes = perdidasAlaEsqRes + 1;
        totalPerdidasAlaEsqRes(perdidasAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: +1 perdida: tot: "+perdidasAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasAlaEsqRes(View view){
        do{
            perdidasAlaEsqRes = perdidasAlaEsqRes - 1;
            if(perdidasAlaEsqRes < 0){
                perdidasAlaEsqRes = 0;
            }
        } while(perdidasAlaEsqRes < 0);
        totalPerdidasAlaEsqRes(perdidasAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: -1 perdida: tot: "+perdidasAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasAlaEsqRes(int number) {
        TextView displayInteger = findViewById(R.id.perdida_alaesqRes);
        displayInteger.setText("" + number);
    }

    //AlaEsqRes INTERCEPTACOES
    public void maisInterceptAlaEsqRes(View view) {
        interceptAlaEsqRes = interceptAlaEsqRes + 1;
        totalInterceptAlaEsqRes(interceptAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: +1 interceptada: tot: "+interceptAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptAlaEsqRes(View view){
        do{
            interceptAlaEsqRes = interceptAlaEsqRes - 1;
            if(interceptAlaEsqRes < 0){
                interceptAlaEsqRes = 0;
            }
        } while(interceptAlaEsqRes < 0);
        totalInterceptAlaEsqRes(interceptAlaEsqRes);
        Toast.makeText(getApplicationContext(), "AlaEsqRes: -1 interceptada: tot: "+interceptAlaEsqRes, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptAlaEsqRes(int number) {
        TextView displayInteger = findViewById(R.id.intercep_alaesqRes);
        displayInteger.setText("" + number);
    }


    //hoje +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrAlaDirRes = 0;
    int chutAlaDirRes = 0;
    int perdidasAlaDirRes = 0;
    int interceptAlaDirRes = 0;

    //*****************************AlaDirRes*******************************************
    //AlaDirRes PASSES ERRADOS
    public void maisPasserAlaDirRes(View view) {
        passErrAlaDirRes = passErrAlaDirRes + 1;
        totalPasserAlaDirRes(passErrAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: +1 pass err: tot: "+passErrAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserAlaDirRes(View view){
        do{ //faça
            passErrAlaDirRes = passErrAlaDirRes - 1; //pede numero
            if(passErrAlaDirRes < 0){
                passErrAlaDirRes = 0; //lança exceção
            }
        } while(passErrAlaDirRes < 0);
        totalPasserAlaDirRes(passErrAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: - pass err: tot: "+passErrAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserAlaDirRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_aladirRes);
        displayInteger.setText("" + number);
    }

    //AlaDirRes CHUTE A GOL
    public void maisChutesAlaDirRes(View view) {
        chutAlaDirRes = chutAlaDirRes + 1;
        totalChutesAlaDirRes(chutAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: +1 chut a gol: tot: "+chutAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesAlaDirRes(View view){
        do{ //faça
            chutAlaDirRes = chutAlaDirRes - 1; //pede numero
            if(chutAlaDirRes < 0){
                chutAlaDirRes = 0; //lança exceção
            }
        } while(chutAlaDirRes < 0);
        totalChutesAlaDirRes(chutAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: -1 chut a gol: tot: "+chutAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesAlaDirRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_aladirRes);
        displayInteger.setText("" + number);
    }

    //AlaDirRes PERDIDAS
    public void maisPerdidasAlaDirRes(View view) {
        perdidasAlaDirRes = perdidasAlaDirRes + 1;
        totalPerdidasAlaDirRes(perdidasAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: +1 perdida: tot: "+perdidasAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasAlaDirRes(View view){
        do{
            perdidasAlaDirRes = perdidasAlaDirRes - 1;
            if(perdidasAlaDirRes < 0){
                perdidasAlaDirRes = 0;
            }
        } while(perdidasAlaDirRes < 0);
        totalPerdidasAlaDirRes(perdidasAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: -1 perdida: tot: "+perdidasAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasAlaDirRes(int number) {
        TextView displayInteger = findViewById(R.id.perdida_aladirRes);
        displayInteger.setText("" + number);
    }

    //AlaDirRes INTERCEPTACOES
    public void maisInterceptAlaDirRes(View view) {
        interceptAlaDirRes = interceptAlaDirRes + 1;
        totalInterceptAlaDirRes(interceptAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: +1 interceptada: tot: "+interceptAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptAlaDirRes(View view){
        do{
            interceptAlaDirRes = interceptAlaDirRes - 1;
            if(interceptAlaDirRes < 0){
                interceptAlaDirRes = 0;
            }
        } while(interceptAlaDirRes < 0);
        totalInterceptAlaDirRes(interceptAlaDirRes);
        Toast.makeText(getApplicationContext(), "AlaDirRes: -1 interceptada: tot: "+interceptAlaDirRes, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptAlaDirRes(int number) {
        TextView displayInteger = findViewById(R.id.intercep_aladirRes);
        displayInteger.setText("" + number);
    }


    //hoje +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrPivoRes = 0;
    int chutPivoRes = 0;
    int perdidasPivoRes = 0;
    int interceptPivoRes = 0;

    //*****************************PivoRes*******************************************
    //PivoRes PASSES ERRADOS
    public void maisPasserPivoRes(View view) {
        passErrPivoRes = passErrPivoRes + 1;
        totalPasserPivoRes(passErrPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: +1 pass err: tot: "+passErrPivoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserPivoRes(View view){
        do{ //faça
            passErrPivoRes = passErrPivoRes - 1; //pede numero
            if(passErrPivoRes < 0){
                passErrPivoRes = 0; //lança exceção
            }
        } while(passErrPivoRes < 0);
        totalPasserPivoRes(passErrPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: - pass err: tot: "+passErrPivoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserPivoRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_pivoRes);
        displayInteger.setText("" + number);
    }

    //PivoRes CHUTE A GOL
    public void maisChutesPivoRes(View view) {
        chutPivoRes = chutPivoRes + 1;
        totalChutesPivoRes(chutPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: +1 chut a gol: tot: "+chutPivoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesPivoRes(View view){
        do{ //faça
            chutPivoRes = chutPivoRes - 1; //pede numero
            if(chutPivoRes < 0){
                chutPivoRes = 0; //lança exceção
            }
        } while(chutPivoRes < 0);
        totalChutesPivoRes(chutPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: -1 chut a gol: tot: "+chutPivoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesPivoRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_pivoRes);
        displayInteger.setText("" + number);
    }

    //PivoRes PERDIDAS
    public void maisPerdidasPivoRes(View view) {
        perdidasPivoRes = perdidasPivoRes + 1;
        totalPerdidasPivoRes(perdidasPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: +1 perdida: tot: "+perdidasPivoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasPivoRes(View view){
        do{
            perdidasPivoRes = perdidasPivoRes - 1;
            if(perdidasPivoRes < 0){
                perdidasPivoRes = 0;
            }
        } while(perdidasPivoRes < 0);
        totalPerdidasPivoRes(perdidasPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: -1 perdida: tot: "+perdidasPivoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasPivoRes(int number) {
        TextView displayInteger = findViewById(R.id.perdida_pivoRes);
        displayInteger.setText("" + number);
    }

    //PivoRes INTERCEPTACOES
    public void maisInterceptPivoRes(View view) {
        interceptPivoRes = interceptPivoRes + 1;
        totalInterceptPivoRes(interceptPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: +1 interceptada: tot: "+interceptPivoRes, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptPivoRes(View view){
        do{
            interceptPivoRes = interceptPivoRes - 1;
            if(interceptPivoRes < 0){
                interceptPivoRes = 0;
            }
        } while(interceptPivoRes < 0);
        totalInterceptPivoRes(interceptPivoRes);
        Toast.makeText(getApplicationContext(), "PivoRes: -1 interceptada: tot: "+interceptPivoRes, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptPivoRes(int number) {
        TextView displayInteger = findViewById(R.id.intercep_pivoRes);
        displayInteger.setText("" + number);
    }


    //hoje +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrGoleiroResRes = 0;
    int chutGoleiroResRes = 0;
    int perdidasGoleiroResRes = 0;
    int interceptGoleiroResRes = 0;

    //*****************************GoleiroResRes*******************************************
    //GoleiroResRes PASSES ERRADOS
    public void maisPasserGoleiroResRes(View view) {
        passErrGoleiroResRes = passErrGoleiroResRes + 1;
        totalPasserGoleiroResRes(passErrGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: +1 pass err: tot: "+passErrGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserGoleiroResRes(View view){
        do{ //faça
            passErrGoleiroResRes = passErrGoleiroResRes - 1; //pede numero
            if(passErrGoleiroResRes < 0){
                passErrGoleiroResRes = 0; //lança exceção
            }
        } while(passErrGoleiroResRes < 0);
        totalPasserGoleiroResRes(passErrGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: - pass err: tot: "+passErrGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserGoleiroResRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_goleiroResRes);
        displayInteger.setText("" + number);
    }

    //GoleiroResRes CHUTE A GOL
    public void maisChutesGoleiroResRes(View view) {
        chutGoleiroResRes = chutGoleiroResRes + 1;
        totalChutesGoleiroResRes(chutGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: +1 chut a gol: tot: "+chutGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesGoleiroResRes(View view){
        do{ //faça
            chutGoleiroResRes = chutGoleiroResRes - 1; //pede numero
            if(chutGoleiroResRes < 0){
                chutGoleiroResRes = 0; //lança exceção
            }
        } while(chutGoleiroResRes < 0);
        totalChutesGoleiroResRes(chutGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: -1 chut a gol: tot: "+chutGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesGoleiroResRes(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_goleiroResRes);
        displayInteger.setText("" + number);
    }

    //GoleiroResRes PERDIDAS
    public void maisPerdidasGoleiroResRes(View view) {
        perdidasGoleiroResRes = perdidasGoleiroResRes + 1;
        totalPerdidasGoleiroResRes(perdidasGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: +1 perdida: tot: "+perdidasGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasGoleiroResRes(View view){
        do{
            perdidasGoleiroResRes = perdidasGoleiroResRes - 1;
            if(perdidasGoleiroResRes < 0){
                perdidasGoleiroResRes = 0;
            }
        } while(perdidasGoleiroResRes < 0);
        totalPerdidasGoleiroResRes(perdidasGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: -1 perdida: tot: "+perdidasGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasGoleiroResRes(int number) {
        TextView displayInteger = findViewById(R.id.perdida_goleiroResRes);
        displayInteger.setText("" + number);
    }

    //GoleiroResRes INTERCEPTACOES
    public void maisInterceptGoleiroResRes(View view) {
        interceptGoleiroResRes = interceptGoleiroResRes + 1;
        totalInterceptGoleiroResRes(interceptGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: +1 interceptada: tot: "+interceptGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptGoleiroResRes(View view){
        do{
            interceptGoleiroResRes = interceptGoleiroResRes - 1;
            if(interceptGoleiroResRes < 0){
                interceptGoleiroResRes = 0;
            }
        } while(interceptGoleiroResRes < 0);
        totalInterceptGoleiroResRes(interceptGoleiroResRes);
        Toast.makeText(getApplicationContext(), "GoleiroResRes: -1 interceptada: tot: "+interceptGoleiroResRes, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptGoleiroResRes(int number) {
        TextView displayInteger = findViewById(R.id.intercep_goleiroResRes);
        displayInteger.setText("" + number);
    }


    //hoje +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passErrJogadorExtra = 0;
    int chutJogadorExtra = 0;
    int perdidasJogadorExtra = 0;
    int interceptJogadorExtra = 0;

    //*****************************JogadorExtra*******************************************
    //JogadorExtra PASSES ERRADOS
    public void maisPasserJogadorExtra(View view) {
        passErrJogadorExtra = passErrJogadorExtra + 1;
        totalPasserJogadorExtra(passErrJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: +1 pass err: tot: "+passErrJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    public void menosPasserJogadorExtra(View view){
        do{ //faça
            passErrJogadorExtra = passErrJogadorExtra - 1; //pede numero
            if(passErrJogadorExtra < 0){
                passErrJogadorExtra = 0; //lança exceção
            }
        } while(passErrJogadorExtra < 0);
        totalPasserJogadorExtra(passErrJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: - pass err: tot: "+passErrJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    private void totalPasserJogadorExtra(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_jogadorExtra);
        displayInteger.setText("" + number);
    }

    //JogadorExtra CHUTE A GOL
    public void maisChutesJogadorExtra(View view) {
        chutJogadorExtra = chutJogadorExtra + 1;
        totalChutesJogadorExtra(chutJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: +1 chut a gol: tot: "+chutJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesJogadorExtra(View view){
        do{ //faça
            chutJogadorExtra = chutJogadorExtra - 1; //pede numero
            if(chutJogadorExtra < 0){
                chutJogadorExtra = 0; //lança exceção
            }
        } while(chutJogadorExtra < 0);
        totalChutesJogadorExtra(chutJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: -1 chut a gol: tot: "+chutJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    private void totalChutesJogadorExtra(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.chuteg_jogadorExtra);
        displayInteger.setText("" + number);
    }

    //JogadorExtra PERDIDAS
    public void maisPerdidasJogadorExtra(View view) {
        perdidasJogadorExtra = perdidasJogadorExtra + 1;
        totalPerdidasJogadorExtra(perdidasJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: +1 perdida: tot: "+perdidasJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    public void menosPerdidasJogadorExtra(View view){
        do{
            perdidasJogadorExtra = perdidasJogadorExtra - 1;
            if(perdidasJogadorExtra < 0){
                perdidasJogadorExtra = 0;
            }
        } while(perdidasJogadorExtra < 0);
        totalPerdidasJogadorExtra(perdidasJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: -1 perdida: tot: "+perdidasJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    private void totalPerdidasJogadorExtra(int number) {
        TextView displayInteger = findViewById(R.id.perdida_jogadorExtra);
        displayInteger.setText("" + number);
    }

    //JogadorExtra INTERCEPTACOES
    public void maisInterceptJogadorExtra(View view) {
        interceptJogadorExtra = interceptJogadorExtra + 1;
        totalInterceptJogadorExtra(interceptJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: +1 interceptada: tot: "+interceptJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    public void menosInterceptJogadorExtra(View view){
        do{
            interceptJogadorExtra = interceptJogadorExtra - 1;
            if(interceptJogadorExtra < 0){
                interceptJogadorExtra = 0;
            }
        } while(interceptJogadorExtra < 0);
        totalInterceptJogadorExtra(interceptJogadorExtra);
        Toast.makeText(getApplicationContext(), "JogadorExtra: -1 interceptada: tot: "+interceptJogadorExtra, Toast.LENGTH_SHORT).show();
    }

    private void totalInterceptJogadorExtra(int number) {
        TextView displayInteger = findViewById(R.id.intercep_jogadorExtra);
        displayInteger.setText("" + number);
    }



    //FIM CONTAGEM DE CLICKS TERMINA AQUI! *******************************************************************

    public void clickBtnVoltar(View view){
        Intent it;
        it = new Intent(ClicarEventosFutsal.this, Inicial.class);
        startActivity(it);
    }
}
