package com.example.paulo.apptecnico;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
//import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class EquipeFutsalConfig extends AppCompatActivity {

    Boolean CheckEditText;
    Button buttonCadastrarEquipe;
    int idTorneio;
    String nomeTorneio;
    RequestQueue requestQueue;

    Spinner spTorneio, spJog1, spJog2, spJog3, spJog4, spJog5, spJog6, spJog7, spJog8, spJog9,
            spJog10, spJog11, spJog12, spJog13, spJog14, spJog15, spJog16, spJog17, spJog18;

    String stNomeTorneio, stApelidoEquipe, stJog1, stJog2, stJog3, stJog4, stJog5, stJog6, stJog7,
           stJog8, stJog9, stJog10, stJog11, stJog12, stJog13, stJog14, stJog15, stJog16,
           stJog17, stJog18;

    TextView posJog1, numJog1, posJog2, numJog2, posJog3, numJog3, posJog4, numJog4, posJog5, numJog5, posJog6, numJog6,
             posJog7, numJog7, posJog8, numJog8, posJog9, numJog9, posJog10, numJog10, posJog11, numJog11, posJog12, numJog12,
             posJog13, numJog13, posJog14, numJog14, posJog15, numJog15, posJog16, numJog16, posJog17, numJog17, posJog18, numJog18;
    EditText eTapelidoEquipe;

    String URL = "http://192.168.15.17/busca_torneios.php";
    String url = "http://192.168.15.17/cadastro_equipe.php";
    ArrayList<String> TorneioName;
    ArrayList<String> TorneioID;
    ArrayAdapter<String> adapter;
    String URLbusca = "http://192.168.15.17/busca_dados_jogador.php";
    String URLbuscaPorNome = "http://192.168.15.17/busca_dados_por_nome_jogador.php";


    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_equipe_futsal_config);
         buttonCadastrarEquipe = findViewById(R.id.buttonCadastrarEquipe);
         spTorneio = findViewById(R.id.spTorneio);
         eTapelidoEquipe = findViewById(R.id.apelidoEquipe);
         spJog1 = findViewById(R.id.spJog1);
         spJog2 = findViewById(R.id.spJog2);
         spJog3 = findViewById(R.id.spJog3);
         spJog4 = findViewById(R.id.spJog4);
         spJog5 = findViewById(R.id.spJog5);
         spJog6 = findViewById(R.id.spJog6);
         spJog7 = findViewById(R.id.spJog7);
         spJog8 = findViewById(R.id.spJog8);
         spJog9 = findViewById(R.id.spJog9);
         spJog10 = findViewById(R.id.spJog10);
         spJog11 = findViewById(R.id.spJog11);
         spJog12 = findViewById(R.id.spJog12);
         spJog13 = findViewById(R.id.spJog13);
         spJog14 = findViewById(R.id.spJog14);
         spJog15 = findViewById(R.id.spJog15);
         spJog16 = findViewById(R.id.spJog16);
         spJog17 = findViewById(R.id.spJog17);
         spJog18 = findViewById(R.id.spJog18);
         posJog1 = findViewById(R.id.posJog1);
         numJog1 = findViewById(R.id.numJog1);
         posJog2 = findViewById(R.id.posJog2);
         numJog2 = findViewById(R.id.numJog2);
         posJog3 = findViewById(R.id.posJog3);
         numJog3 = findViewById(R.id.numJog3);
         posJog4 = findViewById(R.id.posJog4);
         numJog4 = findViewById(R.id.numJog4);
         posJog5 = findViewById(R.id.posJog5);
         numJog5 = findViewById(R.id.numJog5);
         posJog6 = findViewById(R.id.posJog6);
         numJog6 = findViewById(R.id.numJog6);
         posJog7 = findViewById(R.id.posJog7);
         numJog7 = findViewById(R.id.numJog7);
         posJog8 = findViewById(R.id.posJog8);
         numJog8 = findViewById(R.id.numJog8);
         posJog9 = findViewById(R.id.posJog9);
         numJog9 = findViewById(R.id.numJog9);
         posJog10 = findViewById(R.id.posJog10);
         numJog10 = findViewById(R.id.numJog10);
         posJog11 = findViewById(R.id.posJog11);
         numJog11 = findViewById(R.id.numJog11);
         posJog12 = findViewById(R.id.posJog12);
         numJog12 = findViewById(R.id.numJog12);
         posJog13 = findViewById(R.id.posJog13);
         numJog13 = findViewById(R.id.numJog13);
         posJog14 = findViewById(R.id.posJog14);
         numJog14 = findViewById(R.id.numJog14);
         posJog15 = findViewById(R.id.posJog15);
         numJog15 = findViewById(R.id.numJog15);
         posJog16 = findViewById(R.id.posJog16);
         numJog16 = findViewById(R.id.numJog16);
         posJog17 = findViewById(R.id.posJog17);
         numJog17 = findViewById(R.id.numJog17);
         posJog18 = findViewById(R.id.posJog18);
         numJog18 = findViewById(R.id.numJog18);


        requestQueue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        TorneioName = new ArrayList<>();
        TorneioID = new ArrayList<String>();

        loadSpinnerTorneios(URL);
        spTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String torneio = spTorneio.getItemAtPosition(spTorneio.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //AQUI POPULA AS SPINNERS DOS JOGADORES
        loadSpinnerJogadores(URLbusca);

        //AQUI COMEÇA A POPULAR CADA TEXT VIEW DE POSIÇÃO E NUMERO DE ACORDO COM O JOGADOR ESCOLHIDO, USANDO O MÉTODO loadPosicoes(x)()
        spJog1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString();
                verificarDuplicadas1(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString();
                verificarDuplicadas2(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString();
                verificarDuplicadas3(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString();
                verificarDuplicadas4(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString();
                verificarDuplicadas5(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString();
                verificarDuplicadas6(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString();
                verificarDuplicadas7(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString();
                verificarDuplicadas8(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString();
                verificarDuplicadas9(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString();
                verificarDuplicadas10(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString();
                verificarDuplicadas11(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString();
                verificarDuplicadas12(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString();
                verificarDuplicadas13(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString();
                verificarDuplicadas14(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString();
                verificarDuplicadas15(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString();
                verificarDuplicadas16(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString();
                verificarDuplicadas17(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spJog18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString();
                verificarDuplicadas18(jogador);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        buttonCadastrarEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaCamposVazios();
                if (CheckEditText) {
                    cadastrarEquipe();
                } else {
                    Toast.makeText(EquipeFutsalConfig.this, "Favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    String stIDTorneio;
    public void cadastrarEquipe() {
        String stIDnomeTorneio = (String) spTorneio.getSelectedItem();
        String[] separaNomeTorneio = stIDnomeTorneio.split("-");
        stNomeTorneio = separaNomeTorneio[1];
        stIDTorneio =  String.valueOf(somenteDigitos(stIDnomeTorneio));
        final String strApelidoEquipe, strJog1, strJog2, strJog3, strJog4, strJog5, strJog6, strJog7,
                strJog8, strJog9, strJog10, strJog11, strJog12, strJog13, strJog14, strJog15, strJog16,
                strJog17, strJog18;

        strApelidoEquipe = eTapelidoEquipe.getText().toString();
        strJog1 = spJog1.getSelectedItem().toString();
        strJog2 = spJog2.getSelectedItem().toString();
        strJog3 = spJog3.getSelectedItem().toString();
        strJog4 = spJog4.getSelectedItem().toString();
        strJog5 = spJog5.getSelectedItem().toString();
        strJog6 = spJog6.getSelectedItem().toString();
        strJog7 = spJog7.getSelectedItem().toString();
        strJog8 = spJog8.getSelectedItem().toString();
        strJog9 = spJog9.getSelectedItem().toString();
        strJog10 = spJog10.getSelectedItem().toString();
        strJog11 = spJog11.getSelectedItem().toString();
        strJog12 = spJog12.getSelectedItem().toString();
        strJog13 = spJog13.getSelectedItem().toString();
        strJog14 = spJog14.getSelectedItem().toString();
        strJog15 = spJog15.getSelectedItem().toString();
        strJog16 = spJog16.getSelectedItem().toString();
        strJog17 = spJog17.getSelectedItem().toString();
        strJog18 = spJog18.getSelectedItem().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String ServerResponse) {
                if(ServerResponse.equalsIgnoreCase("ok")) {
                    Toast.makeText(EquipeFutsalConfig.this, "Equipe cadastrada com sucesso!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(EquipeFutsalConfig.this, ServerResponse, Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(EquipeFutsalConfig.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("ID_torneio", stIDTorneio);
                params.put("nomeTorneio", stNomeTorneio);
                params.put("apelidoEquipe", strApelidoEquipe);
                params.put("nomeGoleiro", strJog1);
                params.put("nomeFixo", strJog2);
                params.put("nomeAlaEsq", strJog3);
                params.put("nomeAlaDir", strJog4);
                params.put("nomePivo", strJog5);
                params.put("nomeGoleiroRes", strJog6);
                params.put("nomeFixoRes", strJog7);
                params.put("nomeAlaEsqRes", strJog8);
                params.put("nomeAlaDirRes", strJog9);
                params.put("nomePivoRes", strJog10);
                params.put("nomeGoleiroResRes", strJog11);
                params.put("nomeJogadorExtra", strJog12);
                params.put("nomeJogadorExtra1", strJog13);
                params.put("nomeJogadorExtra2", strJog14);
                params.put("nomeJogadorExtra3", strJog15);
                params.put("nomeJogadorExtra4", strJog16);
                params.put("nomeJogadorExtra5", strJog17);
                params.put("nomeJogadorExtra6", strJog18);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        requestQueue.add(stringRequest);
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
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        idTorneio = jsonObject1.getInt("ID_torneio");
                        nomeTorneio = jsonObject1.getString("nomeTorneio");
                        String var = String.valueOf(idTorneio);
                        listaTorneios.add(var+"-"+nomeTorneio);
                    }
                    spTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
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

    //INÍCIO - AQUI POPULA O SPINNER DE JOGADORES COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerJogadores(String urlSpin) {
        final ArrayList<String> listaJogadores = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    listaJogadores.add("Selecione o jogador:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject goleiroj = jsonArray.getJSONObject(i);
                        String nomeJogador = goleiroj.getString("nomeJogador");
                        listaJogadores.add(nomeJogador);
                    }
                    adapter = new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaJogadores);
                    spJog1.setAdapter(adapter);
                    spJog2.setAdapter(adapter);
                    spJog3.setAdapter(adapter);
                    spJog4.setAdapter(adapter);
                    spJog5.setAdapter(adapter);
                    spJog6.setAdapter(adapter);
                    spJog7.setAdapter(adapter);
                    spJog8.setAdapter(adapter);
                    spJog9.setAdapter(adapter);
                    spJog10.setAdapter(adapter);
                    spJog11.setAdapter(adapter);
                    spJog12.setAdapter(adapter);
                    spJog13.setAdapter(adapter);
                    spJog14.setAdapter(adapter);
                    spJog15.setAdapter(adapter);
                    spJog16.setAdapter(adapter);
                    spJog17.setAdapter(adapter);
                    spJog18.setAdapter(adapter);
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
    //INÍCIO - AQUI POPULA O SPINNER DE JOGADORES COM DADOS DO BANCO *******************************************************************


   public void VerificaCamposVazios() {
        stNomeTorneio = spTorneio.toString().trim();
        stApelidoEquipe = eTapelidoEquipe.getText().toString().trim();
        stJog1 = spJog1.toString().trim();
        stJog2 = spJog2.toString().trim();
        stJog3 = spJog3.toString().trim();
        stJog4 = spJog4.toString().trim();
        stJog5 = spJog5.toString().trim();
        stJog6 = spJog6.toString().trim();
        stJog7 = spJog7.toString().trim();
        stJog8 = spJog8.toString().trim();
        stJog9 = spJog9.toString().trim();
        stJog10 = spJog10.toString().trim();
        stJog11 = spJog11.toString().trim();
        stJog12 = spJog12.toString().trim();
        stJog13 = spJog13.toString().trim();
        stJog14 = spJog14.toString().trim();
        stJog15 = spJog15.toString().trim();
        stJog16 = spJog16.toString().trim();
        stJog17 = spJog17.toString().trim();
        stJog18 = spJog18.toString().trim();

        CheckEditText = !TextUtils.isEmpty(stNomeTorneio) && !TextUtils.isEmpty(stApelidoEquipe) && !TextUtils.isEmpty(stJog1)
                && !TextUtils.isEmpty(stJog2) && !TextUtils.isEmpty(stJog3) && !TextUtils.isEmpty(stJog4)
                && !TextUtils.isEmpty(stJog5) && !TextUtils.isEmpty(stJog6) && !TextUtils.isEmpty(stJog7)
                && !TextUtils.isEmpty(stJog8) && !TextUtils.isEmpty(stJog9) && !TextUtils.isEmpty(stJog10)
                && !TextUtils.isEmpty(stJog11)&& !TextUtils.isEmpty(stJog12) && !TextUtils.isEmpty(stJog13)
                && !TextUtils.isEmpty(stJog14)&& !TextUtils.isEmpty(stJog15)&& !TextUtils.isEmpty(stJog16)
                && !TextUtils.isEmpty(stJog17) && !TextUtils.isEmpty(stJog18);
    }

    /*AQUI CARREGA TODOS AS POSIÇÕES E NUMEROS, BUSCADOS PELO NOME DO JOGADOR SELECIONADO*/
    public void loadPosicoes1 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog1.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog1.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void loadPosicoes2(final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog2.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog2.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void loadPosicoes3 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog3.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog3.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }


    public void loadPosicoes4 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog4.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog4.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes5 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog5.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog5.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes6 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog6.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog6.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes7 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog7.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog7.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes8 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog8.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog8.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes9 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog9.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog9.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }


    public void loadPosicoes10 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog10.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog10.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes11 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog11.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog11.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes12 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog12.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog12.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes13 (final String strJogador){
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog13.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog13.setText(numero);
                    }
                 } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes14 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog14.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog14.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes15 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog15.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog15.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes16 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog16.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog16.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }



    public void loadPosicoes17 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog17.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog17.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        })

        {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void loadPosicoes18 (final String strJogador){
        final ArrayList<String> listaTorneios = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorNome, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        String posicao = jogadorj.getString("posicao");
                        posJog18.setText(posicao);
                        String numero = jogadorj.getString("numeroJogador");
                        numJog18.setText(numero);
                    }
                    //spinnerTorneio.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
                } catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nomeJogador", strJogador);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void verificarDuplicadas1(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if (jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ) {
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista1", Toast.LENGTH_LONG).show();
                spJog1.setSelection(0);
            } else {
                loadPosicoes1(jogador);
            }
        }
        else Toast.makeText(EquipeFutsalConfig.this, "preencha os campos", Toast.LENGTH_LONG).show();
    }

    public void verificarDuplicadas2(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
        if(        jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
        ){
            Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
            spJog2.setSelection(0);
        }else {
            loadPosicoes2(jogador);
        }
        }
        else {}
    }

    public void verificarDuplicadas3(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog3.setSelection(0);
            }else {
                loadPosicoes3(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas4(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog4.setSelection(0);
            }else {
                loadPosicoes4(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas5(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog5.setSelection(0);
            }else {
                loadPosicoes5(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas6(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog6.setSelection(0);
            }else {
                loadPosicoes6(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas7(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog7.setSelection(0);
            }else {
                loadPosicoes7(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas8(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog8.setSelection(0);
            }else {
                loadPosicoes8(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas9(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog9.setSelection(0);
            }else {
                loadPosicoes9(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas10(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog10.setSelection(0);
            }else {
                loadPosicoes10(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas11(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog11.setSelection(0);
            }else {
                loadPosicoes11(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas12(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog12.setSelection(0);
            }else {
                loadPosicoes12(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas13(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog13.setSelection(0);
            }else {
                loadPosicoes13(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas14(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog14.setSelection(0);
            }else {
                loadPosicoes14(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas15(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog15.setSelection(0);
            }else {
                loadPosicoes15(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas16(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog16.setSelection(0);
            }else {
                loadPosicoes16(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas17(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog18.getItemAtPosition(spJog18.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog17.setSelection(0);
            }else {
                loadPosicoes17(jogador);
            }
        }
        else {}
    }

    public void verificarDuplicadas18(String jogador){
        if(!(spJog1.getSelectedItemPosition()==0)) {
            if(        jogador.equalsIgnoreCase(spJog2.getItemAtPosition(spJog2.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog1.getItemAtPosition(spJog1.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog4.getItemAtPosition(spJog4.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog5.getItemAtPosition(spJog5.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog6.getItemAtPosition(spJog6.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog7.getItemAtPosition(spJog7.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog8.getItemAtPosition(spJog8.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog9.getItemAtPosition(spJog9.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog10.getItemAtPosition(spJog10.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog11.getItemAtPosition(spJog11.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog12.getItemAtPosition(spJog12.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog13.getItemAtPosition(spJog13.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog14.getItemAtPosition(spJog14.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog15.getItemAtPosition(spJog15.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog16.getItemAtPosition(spJog16.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog17.getItemAtPosition(spJog17.getSelectedItemPosition()).toString())
                    || jogador.equalsIgnoreCase(spJog3.getItemAtPosition(spJog3.getSelectedItemPosition()).toString())
            ){
                Toast.makeText(EquipeFutsalConfig.this, "Não pode cadastrar o mesmo jogador: verifique a lista", Toast.LENGTH_LONG).show();
                spJog18.setSelection(0);
            }else {
                loadPosicoes18(jogador);
            }
        }
        else {}
    }
}