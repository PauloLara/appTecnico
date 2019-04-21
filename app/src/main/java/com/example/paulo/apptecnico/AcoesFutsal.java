package com.example.paulo.apptecnico;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.Map;

public class AcoesFutsal extends Activity {

    Button btnVerificaEquipe;
    AlertDialog.Builder alertDialog;
    Spinner spinner;
    String URLsp = "http://192.168.15.17/busca_torneios.php";
    String URLtv = "http://192.168.15.17/busca_dados_equipe.php";
    String URLev = "http://192.168.15.17/insere_eventos.php";
    TextView tvGoleiro, tvFixo, tvAlaEsq, tvAlaDir, tvPivo, tvGoleiroRes, tvFixoRes, tvAlaEsqRes, tvAlaDirRes, tvPivoRes, tvGoleiroResRes, tvJogadorExtra;
    Button btnFinalizar;
    TextView textViewGoleiroAtleta, textAtletaGoleiro, textViewGoleiroPasseErrado, textViewGoleiroChuteAgol, textViewGoleiroPerdida, textViewGoleiroInterceptacao;
    TextView textViewFixoAtleta, textViewFixoPasseErrado, textViewFixoChuteAgol, textViewFixoPerdida, textViewFixoInterceptacao;
    TextView textViewAlaEsqAtleta, textViewAlaEsqPasseErrado, textViewAlaEsqChuteAgol, textViewAlaEsqPerdida, textViewAlaEsqInterceptacao;
    TextView textViewAlaDirAtleta, textViewAlaDirPasseErrado, textViewAlaDirChuteAgol, textViewAlaDirPerdida, textViewAlaDirInterceptacao;
    TextView textViewPivoAtleta, textViewPivoPasseErrado, textViewPivoChuteAgol, textViewPivoPerdida, textViewPivoInterceptacao;
    EditText editTextDate;
    int idTorneio;
    EditText date;
    DatePickerDialog datePickerDialog;



    //INÍCIO - AQUI POPULA O SPINNER COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerData(String urlSpin) {
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("torneios");
                    listaTorneios.add("Selecione o torneio:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        idTorneio = jsonObject1.getInt("ID_torneio");
                        String nomeTorneio = jsonObject1.getString("nomeTorneio");
                        String var = String.valueOf(idTorneio);
                        listaTorneios.add(var+" "+nomeTorneio);
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(AcoesFutsal.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));

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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_futsal);

        date = (EditText) findViewById(R.id.date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AcoesFutsal.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                     public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                              date.setText(year + "-"+ (monthOfYear + 1) + "-" + dayOfMonth);
                     }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

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
        editTextDate = findViewById(R.id.date);
        spinner = findViewById(R.id.spinnerJogo);
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
                final String stIDnomeTorneio = (String) spinner.getSelectedItem();
                final String stIDTorneio =  String.valueOf(somenteDigitos(stIDnomeTorneio));
                Toast.makeText(getApplicationContext(), stIDnomeTorneio, Toast.LENGTH_LONG).show();
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
                        params.put("IDtorneio", stIDTorneio);
                        return params;
                    }
                };
                queue.add(stringRequest);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strDataJogo = String.valueOf(editTextDate.getText());
                final String  strDataJogo1 = strDataJogo;
                final String  strDataJogo2 = strDataJogo;
                final String  strDataJogo3 = strDataJogo;
                final String  strDataJogo4 = strDataJogo;
                final String  strDataJogo5 = strDataJogo;

                final String strGoleiroAtleta= (String) textViewGoleiroAtleta.getText();
                final String strGoleiroPasseErrado = (String) textViewGoleiroPasseErrado.getText();
                final String strGoleiroChuteAgol = (String) textViewGoleiroChuteAgol.getText();
                final String strGoleiroPerdida = (String) textViewGoleiroPerdida.getText();
                final String strGoleiroInterceptacao = (String) textViewGoleiroInterceptacao.getText();

                final String strFixoAtleta= (String) textViewFixoAtleta.getText();
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
                final String strPivoInterceptacao = (String) textViewPivoInterceptacao.getText();

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
                        params.put("dataJogo1", strDataJogo1);
                        params.put("atleta1", strGoleiroAtleta);
                        params.put("passeErrado1", strGoleiroPasseErrado);
                        params.put("chuteAgol1", strGoleiroChuteAgol);
                        params.put("perdida1", strGoleiroPerdida);
                        params.put("interceptacao1", strGoleiroInterceptacao);

                        params.put("dataJogo2", strDataJogo2);
                        params.put("atleta2", strFixoAtleta);
                        params.put("passeErrado2", strFixoPasseErrado);
                        params.put("chuteAgol2", strFixoChuteAgol);
                        params.put("perdida2", strFixoPerdida);
                        params.put("interceptacao2", strFixoInterceptacao);

                        params.put("dataJogo3", strDataJogo3);
                        params.put("atleta3", strAlaEsqAtleta);
                        params.put("passeErrado3", strAlaEsqPasseErrado);
                        params.put("chuteAgol3", strAlaEsqChuteAgol);
                        params.put("perdida3", strAlaEsqPerdida);
                        params.put("interceptacao3", strAlaEsqInterceptacao);

                        params.put("dataJogo4", strDataJogo4);
                        params.put("atleta4", strAlaDirAtleta);
                        params.put("passeErrado4", strAlaDirPasseErrado);
                        params.put("chuteAgol4", strAlaDirChuteAgol);
                        params.put("perdida4", strAlaDirPerdida);
                        params.put("interceptacao4", strAlaDirInterceptacao);

                        params.put("dataJogo5", strDataJogo5);
                        params.put("atleta5", strPivoAtleta);
                        params.put("passeErrado5", strPivoPasseErrado);
                        params.put("chuteAgol5", strPivoChuteAgol);
                        params.put("perdida5", strPivoPerdida);
                        params.put("interceptacao5", strPivoInterceptacao);
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

                textAtletaGoleiro.setText(null);
                textViewGoleiroPasseErrado.setText(null);
                textViewGoleiroChuteAgol.setText(null);
                textViewGoleiroPerdida.setText(null);
                textViewGoleiroInterceptacao.setText(null);

                textViewFixoAtleta.setText(null);
                textViewFixoPasseErrado.setText(null);
                textViewFixoChuteAgol.setText(null);
                textViewFixoPerdida.setText(null);
                textViewFixoInterceptacao.setText(null);

                textViewAlaEsqAtleta.setText(null);
                textViewAlaEsqPasseErrado.setText(null);
                textViewAlaEsqChuteAgol.setText(null);
                textViewAlaEsqPerdida.setText(null);
                textViewAlaEsqInterceptacao.setText(null);

                textViewAlaDirAtleta.setText(null);
                textViewAlaDirPasseErrado.setText(null);
                textViewAlaDirChuteAgol.setText(null);
                textViewAlaDirPerdida.setText(null);
                textViewAlaDirInterceptacao.setText(null);

                textViewPivoAtleta.setText(null);
                textViewPivoPasseErrado.setText(null);
                textViewPivoChuteAgol.setText(null);
                textViewPivoPerdida.setText(null);
                textViewPivoInterceptacao.setText(null);
                editTextDate.setText(null);
            }
        });



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
        totalPasserGoleiro(chutGoleiro);
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
        TextView displayInteger = (TextView) findViewById(R.id.intercep_goleiro);
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
        totalPasserFixo(chutFixo);
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
        Toast.makeText(getApplicationContext(), "Ala Dir: +1 chut a gol: tot: "+chutPivo, Toast.LENGTH_SHORT).show();
    }

    public void menosChutesPivo(View view){
        do{ //faça
            chutPivo = chutPivo - 1; //pede numero
            if(chutPivo < 0){
                chutPivo = 0; //lança exceção
            }
        } while(chutPivo < 0);
        totalChutesPivo(chutPivo);
        Toast.makeText(getApplicationContext(), "Ala Dir: -1 chut a gol: tot: "+chutPivo, Toast.LENGTH_SHORT).show();
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

    //FIM CONTAGEM DE CLICKS TERMINA AQUI! *******************************************************************

   /* public void clear(ViewGroup group) {

        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = group.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView)view).setText("");
            }
        }
     }

     clear((ViewGroup)findViewById(R.id.parent));
    */
}

