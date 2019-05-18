package com.example.paulo.apptecnico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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

public class DeletarDados extends AppCompatActivity {
    Spinner spinnerTorneio, spinnerEquipe, spinnerJogador, spinnerEquJog;
    int idTorneio;

    String URLspT = "http://192.168.15.17/busca_torneios.php";
    String URLspE = "http://192.168.15.17/busca_apelido_equipe.php";
    String URLspJ = "http://192.168.15.17/busca_jogadores_spinner.php";
    String URLdel = "http://192.168.15.17/deletar_dados.php";

    //String URLspT = "https://appscout.000webhostapp.com/appscout/busca_torneios.php";
    //String URLspE = "https://appscout.000webhostapp.com/appscout/busca_apelido_equipe.php";
    //String URLspJ = "https://appscout.000webhostapp.com/appscout/busca_jogadores_spinner.php";
    //String URLdel = "https://appscout.000webhostapp.com/appscout/deletar_dados.php";

    Button btnEscolheTorneio, btnEscolheEquipe, btnEscolheJogador, btnDeletar, btnSair;
    ImageButton imgbtnExcluirSelecao1, imgbtnExcluirSelecao2, imgbtnExcluirSelecao3;
    TextView selecionado1, selecionado2, selecionado3, selecionado4, txtitens;
    AlertDialog.Builder alertDialog;
    ArrayAdapter adptTorneios, adptEquipes, adptJogadores;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_dados);

        spinnerTorneio = findViewById(R.id.spinnerTorneio);
        spinnerEquipe = findViewById(R.id.spinnerEquipe);
        spinnerJogador = findViewById(R.id.spinnerJogador);
        spinnerEquJog = findViewById(R.id.spinnerEquJog);

        btnEscolheTorneio = findViewById(R.id.btnEscolheTorneio);
        btnEscolheEquipe = findViewById(R.id.btnEscolheEquipe);
        btnEscolheJogador = findViewById(R.id.btnEscolheJogador);
        btnDeletar = findViewById(R.id.btnDeletar);
        btnSair = findViewById(R.id.btnSair);

        imgbtnExcluirSelecao1 = findViewById(R.id.imgBtExcluirSelect1);
        imgbtnExcluirSelecao1.setVisibility(View.INVISIBLE);
        imgbtnExcluirSelecao2 = findViewById(R.id.imgBtExcluirSelect2);
        imgbtnExcluirSelecao2.setVisibility(View.INVISIBLE);
        imgbtnExcluirSelecao3 = findViewById(R.id.imgBtExcluirSelect3);
        imgbtnExcluirSelecao3.setVisibility(View.INVISIBLE);

        selecionado1 = findViewById(R.id.selecionado1);
        selecionado2 = findViewById(R.id.selecionado2);
        selecionado3 = findViewById(R.id.selecionado3);
        selecionado4 = findViewById(R.id.selecionado4);
        txtitens = findViewById(R.id.txtItens);
        txtitens.setVisibility(View.INVISIBLE);

        btnEscolheTorneio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strNomeTorneio = (String) spinnerTorneio.getSelectedItem();
                selecionado1.setText(strNomeTorneio);
                imgbtnExcluirSelecao1.setVisibility(View.VISIBLE);
            }
        });


        btnEscolheEquipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strNomeEquipe = (String) spinnerEquipe.getSelectedItem();
                selecionado2.setText(strNomeEquipe);
                txtitens.setVisibility(View.VISIBLE);
                imgbtnExcluirSelecao2.setVisibility(View.VISIBLE);
            }
        });

        btnEscolheJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strNomeEquJog = (String) spinnerEquJog.getSelectedItem();
                final String strNomejogador = (String) spinnerJogador.getSelectedItem();
                selecionado3.setText(strNomejogador);
                if(selecionado4.getText().equals(null)){
                    Toast.makeText(DeletarDados.this, "Selecione o jogador:", Toast.LENGTH_SHORT).show();
                }
                selecionado4.setText(strNomeEquJog);
                txtitens.setVisibility(View.VISIBLE);
                imgbtnExcluirSelecao3.setVisibility(View.VISIBLE);
            }
        });

        imgbtnExcluirSelecao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionado1.setText(null);
                imgbtnExcluirSelecao1.setVisibility(View.INVISIBLE);
            }
        });

        imgbtnExcluirSelecao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionado2.setText(null);
                imgbtnExcluirSelecao2.setVisibility(View.INVISIBLE);
            }
        });

        imgbtnExcluirSelecao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionado3.setText(null);
                selecionado4.setText(null);
                imgbtnExcluirSelecao3.setVisibility(View.INVISIBLE);

            }
        });
        carregarSpinners();
        btnDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(selecionado1 ==null)||!(selecionado2 ==null)||!(selecionado4 ==null)){
                    deletarDados();
                }
                txtitens.setVisibility(View.INVISIBLE);
                carregarSpinners();
            }
        });
    }

    public void carregarSpinners(){
        loadSpinnerTorneios(URLspT);
        spinnerTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String torneio = spinnerTorneio.getItemAtPosition(spinnerTorneio.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        loadSpinnerEquipes(URLspE);
        spinnerEquipe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String equipe = spinnerEquipe.getItemAtPosition(spinnerEquipe.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        loadSpinnerJogadores(URLspE);
        spinnerEquJog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String equipeJogador = spinnerEquJog.getItemAtPosition(spinnerEquJog.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        loadSpinnerJogadores(URLspJ);
        spinnerJogador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String jogador = spinnerJogador.getItemAtPosition(spinnerJogador.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }


    public void deletarDados(){
            final  String selecionada1, selecionada2, selecionada3, selecionada4;
            selecionada1 = String.valueOf(selecionado1.getText());
            selecionada2 = String.valueOf(selecionado2.getText());
            selecionada3 = String.valueOf(selecionado3.getText());
            selecionada4 = String.valueOf(selecionado4.getText());

            RequestQueue queue = Volley.newRequestQueue(DeletarDados.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLdel, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response) {
                    alertDialog = new AlertDialog.Builder(DeletarDados.this);
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
                            Toast.makeText(DeletarDados.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("selecionada1", selecionada1);
                    params.put("selecionada2", selecionada2);
                    params.put("selecionada3", selecionada3);
                    params.put("selecionada4", selecionada4);
                    return params;
                }
            };
            queue.add(stringRequest);
            selecionado1.setText(null);
            imgbtnExcluirSelecao1.setVisibility(View.INVISIBLE);
            selecionado2.setText(null);
            imgbtnExcluirSelecao2.setVisibility(View.INVISIBLE);
            selecionado3.setText(null);
            imgbtnExcluirSelecao3.setVisibility(View.INVISIBLE);
            selecionado4.setText(null);
        }


    //INÍCIO - AQUI POPULA O SPINNER DE TORNEIOS COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerTorneios(String urlSpin) {
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
                        String nomeTorneio = jsonObject1.getString("nomeTorneio");
                        listaTorneios.add(nomeTorneio);
                    }
                    adptTorneios = new ArrayAdapter<>(DeletarDados.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios);
                    spinnerTorneio.setAdapter(adptTorneios);
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


    //INÍCIO - AQUI POPULA O SPINNER DE EQUIPES COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerEquipes(String urlSpin) {
        final ArrayList<String> listaEquipes = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("equipes");
                    listaEquipes.add("Selecione a equipe:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String nomeEquipe = jsonObject1.getString("apelidoEquipe");
                        listaEquipes.add(nomeEquipe);
                    }
                    adptEquipes = new ArrayAdapter<String>(DeletarDados.this, android.R.layout.simple_spinner_dropdown_item, listaEquipes);
                    spinnerEquipe.setAdapter(adptEquipes);
                    spinnerEquJog.setAdapter(adptEquipes);

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


    //INÍCIO - AQUI POPULA O SPINNER DE JOGADORES COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerJogadores(String urlSpin) {
        final ArrayList<String> listaJogadores = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("jogadores");
                    listaJogadores.add("Selecione o jogador:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String nomeJogador = jsonObject1.getString("nomeJogador");
                        listaJogadores.add(nomeJogador);
                    }
                    adptJogadores = new ArrayAdapter<String>(DeletarDados.this, android.R.layout.simple_spinner_dropdown_item, listaJogadores);
                    spinnerJogador.setAdapter(adptJogadores);

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
    public void clickBtnSair(View view){
        Intent it;
        it = new Intent(DeletarDados.this, Inicial.class);
        startActivity(it);
    }

}
