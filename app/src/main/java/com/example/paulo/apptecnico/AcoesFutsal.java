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
import java.util.HashMap;
import java.util.Map;

public class AcoesFutsal extends Activity {

    Button btnVerificaEquipe;
    AlertDialog.Builder alertDialog;
    EditText editTextGoleiro;
    Spinner spinner;
    String URLsp = "http://192.168.15.17/busca_torneios.php";
    String URLtv = "http://192.168.15.17/busca_dados_equipe.php";
    String URLev = "http://192.168.15.17/insere_eventos.php";
    TextView tvGoleiro, tvFixo, tvAlaEsq, tvAlaDir, tvPivo, tvGoleiroRes, tvFixoRes, tvAlaEsqRes, tvAlaDirRes, tvPivoRes, tvGoleiroResRes, tvJogadorExtra;
    Button btnFinalizar;
    int contador;
    TextView textViewGoleiroAtleta, textAtletaGoleiro, textViewGoleiroPasseErrado, textViewGoleiroChuteAgol, textViewGoleiroPerdida, textViewGoleiroInterceptacao;
    TextView textViewFixoAtleta, textViewFixoPasseErrado, textViewFixoChuteAgol, textViewFixoPerdida, textViewFixoInterceptacao;
    TextView textViewAlaEsqAtleta, textViewAlaEsqPasseErrado, textViewAlaEsqChuteAgol, textViewAlaEsqPerdida, textViewAlaEsqInterceptacao;
    TextView textViewAlaDirAtleta, textViewAlaDirPasseErrado, textViewAlaDirChuteAgol, textViewAlaDirPerdida, textViewAlaDirInterceptacao;
    TextView textViewPivoAtleta, textViewPivoPasseErrado, textViewPivoChuteAgol, textViewPivoPerdida, textViewPivoInterceptacao;
    ArrayList<String> torneioName;




    //INÍCIO - AQUI POPULA O SPINNER COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerData(String urlSpin) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
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
    // FIM - AQUI POPULA O SPINNER COM DADOS DO BANCO    *******************************************************************

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_futsal);

        btnFinalizar = findViewById(R.id.finalizar);
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


        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strGoleiroAtleta= (String) textViewGoleiroAtleta.getText();
                final String strGoleiroPasseErrado = (String) textViewGoleiroPasseErrado.getText();
                final String strGoleiroChuteAgol = (String) textViewGoleiroChuteAgol.getText();
                final String strGoleiroPerdida = (String) textViewGoleiroPerdida.getText();
                final String strGoleiroInterceptacao = (String) textViewGoleiroInterceptacao.getText();

                /*final String strFixoAtleta= (String) textViewFixoAtleta.getText();
                final String strFixoPasseErrado = (String) textViewFixoPasseErrado.getText();
                final String strFixoChuteAgol = (String) textViewFixoChuteAgol.getText();
                final String strFixoPerdida = (String) textViewFixoPerdida.getText();
                final String strFixoInterceptacao = (String) textViewFixoInterceptacao.getText();

                final String strAlaEsqAtleta= (String) textViewAlaEsqAtleta.getText();
                final String strAlaEsqPasseErrado = (String) textViewAlaEsqPasseErrado.getText();
                final String strAlaEsqChuteAgol = (String) textViewAlaEsqChuteAgol.getText();
                final String strAlaEsqPerdida = (String) textViewAlaEsqPerdida.getText();
                final String strAlaEsqInterceptacao = (String) textViewAlaEsqInterceptacao.getText();

                final String strAlaDirAtleta= (String) textViewAlaDirAtleta.getText();
                final String strAlaDirPasseErrado = (String) textViewAlaDirPasseErrado.getText();
                final String strAlaDirChuteAgol = (String) textViewAlaDirChuteAgol.getText();
                final String strAlaDirPerdida = (String) textViewAlaDirPerdida.getText();
                final String strAlaDirInterceptacao = (String) textViewAlaDirInterceptacao.getText();

                final String strPivoAtleta= (String) textViewPivoAtleta.getText();
                final String strPivoPasseErrado = (String) textViewPivoPasseErrado.getText();
                final String strPivoChuteAgol = (String) textViewPivoChuteAgol.getText();
                final String strPivoPerdida = (String) textViewPivoPerdida.getText();
                final String strPivoInterceptacao = (String) textViewPivoInterceptacao.getText();*/

                Toast.makeText(getApplicationContext(), strGoleiroPasseErrado, Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(AcoesFutsal.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLev, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog = new AlertDialog.Builder(AcoesFutsal.this);
                        alertDialog.setMessage("Resposta: " + response);
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
                                Toast.makeText(AcoesFutsal.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("atleta", strGoleiroAtleta);
                        params.put("passeErrado", strGoleiroPasseErrado);
                        params.put("chuteAgol", strGoleiroChuteAgol);
                        params.put("perdida", strGoleiroPerdida);
                        params.put("interceptacao", strGoleiroInterceptacao);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });


        editTextGoleiro = findViewById(R.id.txtGoleiro);
        torneioName = new ArrayList<>();
        spinner = findViewById(R.id.spinnerTorneio);
        btnVerificaEquipe = findViewById(R.id.btnVerificaEquipe);

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

        loadSpinnerData(URLsp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String torneio = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
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
                RequestQueue queue = Volley.newRequestQueue(AcoesFutsal.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLtv, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog = new AlertDialog.Builder(AcoesFutsal.this);
                        alertDialog.setMessage("Resposta: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("dados");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObjPosicoes = jsonArray.getJSONObject(i);
                                tvGoleiro.setText(jsonObjPosicoes.getString("goleiro"));
                                tvFixo.setText(jsonObjPosicoes.getString("fixo"));
                                tvAlaEsq.setText(jsonObjPosicoes.getString("alaEsq"));
                                tvAlaDir.setText(jsonObjPosicoes.getString("alaDir"));
                                tvPivo.setText(jsonObjPosicoes.getString("pivo"));
                                tvGoleiroRes.setText(jsonObjPosicoes.getString("goleiroRes"));
                                tvFixoRes.setText(jsonObjPosicoes.getString("fixoRes"));
                                tvAlaEsqRes.setText(jsonObjPosicoes.getString("alaEsqRes"));
                                tvAlaDirRes.setText(jsonObjPosicoes.getString("alaDirRes"));
                                tvPivoRes.setText(jsonObjPosicoes.getString("pivoRes"));
                                tvGoleiroResRes.setText(jsonObjPosicoes.getString("goleiroResRes"));
                                tvJogadorExtra.setText(jsonObjPosicoes.getString("jogadorExtra"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        textAtletaGoleiro.setText(tvGoleiro.getText());
                        textViewFixoAtleta.setText(tvFixo.getText());
                        textViewAlaEsqAtleta.setText(tvAlaEsq.getText());
                        textViewAlaDirAtleta.setText(tvAlaDir.getText());
                        textViewPivoAtleta.setText(tvPivo.getText());
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

    // INÍCIO CONTAGEM DE CLICKS  COMEÇA AQUI   *******************************************************************
    int passesErr = 0;
    int chutesAgol = 0;
    int perdidas = 0;
    int intercepts = 0;

    public void maisPasserGoleiro(View view) {
        passesErr = passesErr + 1;
        totalPasserGoleiro(passesErr);
    }

    public void menosPasserGoleiro(View view){
        do{ //faça
            passesErr = passesErr - 1; //pede numero
            if(passesErr < 0){
                passesErr = 0; //lança exceção
            }
        } while(passesErr < 0);
        totalPasserGoleiro(passesErr);
    }

    private void totalPasserGoleiro(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_goleiro);
        displayInteger.setText("" + number);
        contador = number;
    }

    public void maisPasserFixo(View view) {
        passesErr = passesErr + 1;
        totalPasserFixo(passesErr);
    }

    public void menosPasserFixo(View view){
        do{ //faça
            passesErr = passesErr - 1; //pede numero
            if(passesErr < 0){
                passesErr = 0; //lança exceção
            }
        } while(passesErr < 0);
        totalPasserFixo(passesErr);
    }

    private void totalPasserFixo(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.passer_fixo);
        displayInteger.setText("" + number);
        contador = number;
    }
    //FIM CONTAGEM DE CLICKS TERMINA AQUI! *******************************************************************
}

