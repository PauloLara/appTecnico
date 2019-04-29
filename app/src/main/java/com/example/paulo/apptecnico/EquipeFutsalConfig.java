package com.example.paulo.apptecnico;

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

    EditText editTextGoleiro, editTextFixo, editTextAlaEsq, editTextAlaDir, editTextPivo, editTextGoleiroRes,
             editTextFixoRes, editTextAlaEsqRes, editTextAlaDirRes, editTextPivoRes, editTextGoleiroResRes, editTextJogadorExtra;

    /*EditText numGoleiro, numFixo, numAlaEsq, numAlaDir, numPivo, numGoleiroRes,
            numFixoRes, numAlaEsqRes, numAlaDirRes, numPivoRes, numGoleiroResRes, numJogadorExtra;*/

    String stNomeGoleiro, stNomeFixo, stNomeAlaEsq, stNomeAlaDir, stNomePivo, stNomeGoleiroRes, stNomeFixoRes, stNomeAlaEsqRes, stNomeAlaDirRes,
            stNomePivoRes, stNomeGoleiroResRes, stNomeJogadorExtra;

    /*String stNumGoleiro, stNumFixo, stNumAlaEsq, stNumAlaDir, stNumPivo, stNumGoleiroRes,
            stNumFixoRes, stNumAlaEsqRes, stNumAlaDirRes, stNumPivoRes, stNumGoleiroResRes, stNumJogadorExtra;*/

    Boolean CheckEditText;
    Button buttonCadastrarEquipe;
    AlertDialog.Builder alertDialog;
    //ProgressDialog progressDialog;
    RequestQueue requestQueue;


    Spinner spinner;
    String URL = "http://192.168.15.17/busca_torneios.php";
    String url = "http://192.168.15.17/cadastro_equipe.php";
    ArrayList<String> TorneioName;
    ArrayList<String> TorneioID;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe_futsal_config);

        editTextGoleiro = findViewById(R.id.txtNomeJogador);
        editTextFixo = findViewById(R.id.txtFixo);
        editTextAlaEsq = findViewById(R.id.txtAlaEsq);
        editTextAlaDir = findViewById(R.id.txtAlaDir);
        editTextPivo = findViewById(R.id.txtPivo);
        editTextGoleiroRes = findViewById(R.id.txtGoleiroRes);
        editTextFixoRes = findViewById(R.id.txtFixoRes);
        editTextAlaEsqRes = findViewById(R.id.txtAlaEsqRes);
        editTextAlaDirRes = findViewById(R.id.txtAlaDirRes);
        editTextPivoRes = findViewById(R.id.txtPivoRes);
        editTextGoleiroResRes = findViewById(R.id.txtGoleiroResRes);
        editTextJogadorExtra = findViewById(R.id.txtJogadorExtra);
        buttonCadastrarEquipe = findViewById(R.id.btnCadJogador);

        /*numGoleiro = findViewById(R.id.numGoleiro);
        numFixo = findViewById(R.id.numFixo);
        stNomeAlaEsq = findViewById(R.id.);
        stNomeAlaDir = findViewById(R.id.);
        stNomePivo = findViewById(R.id.);
        stNomeGoleiroRes = findViewById(R.id.);
        stNomeFixoRes = findViewById(R.id.);
        stNomeAlaEsqRes = findViewById(R.id.);
        stNomeAlaDirRes = findViewById(R.id.);
        stNomePivoRes = findViewById(R.id.);
        stNomeGoleiroResRes = findViewById(R.id.);
        stNomeJogadorExtra = findViewById(R.id.);*/

        requestQueue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        //progressDialog = new ProgressDialog(EquipeFutsalConfig.this);
        TorneioName = new ArrayList<>();
        TorneioID = new ArrayList<String>();
        spinner = findViewById(R.id.spinnerData);
        loadSpinnerData(URL);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String torneio = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                //Toast.makeText(getApplicationContext(), torneio, Toast.LENGTH_LONG).show();
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
                    UserLogin();
                } else {
                    Toast.makeText(EquipeFutsalConfig.this, "Favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });

           /*
            buttonCadastrarEquipe.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                final String stIDnomeTorneio = (String) spinner.getSelectedItem();
                final String[] separaNomeTorneio = stIDnomeTorneio.split("-");
                final String stNomeTorneio = separaNomeTorneio[1];
                final String stIDTorneio =  String.valueOf(somenteDigitos(stIDnomeTorneio));
                Toast.makeText(getApplicationContext(), "Cadastrado: "+stNomeTorneio, Toast.LENGTH_LONG).show();
                final String stnomeGoleiro, stnomeFixo, stnomeAlaEsq, stnomeAlaDir, stnomePivo, stnomeGoleiroRes,
                      stnomeFixoRes, stnomeAlaEsqRes, stnomeAlaDirRes, stnomePivoRes, stnomeGoleiroResRes, stnomeJogadorExtra;

                stnomeGoleiro = editTextGoleiro.getText().toString();
                stnomeFixo = editTextFixo.getText().toString();
                stnomeAlaEsq = editTextAlaEsq.getText().toString();
                stnomeAlaDir = editTextAlaDir.getText().toString();
                stnomePivo  = editTextPivo.getText().toString();
                stnomeGoleiroRes  = editTextGoleiroRes.getText().toString();
                stnomeFixoRes  = editTextFixoRes.getText().toString();
                stnomeAlaEsqRes  = editTextAlaEsqRes.getText().toString();
                stnomeAlaDirRes  = editTextAlaDirRes.getText().toString();
                stnomePivoRes  = editTextPivoRes.getText().toString();
                stnomeGoleiroResRes  = editTextGoleiroResRes.getText().toString();
                stnomeJogadorExtra  = editTextJogadorExtra.getText().toString();

                VerificaCamposVazios();

                if (!CheckEditText) {
                    UserLogin();
                } else {
                    Toast.makeText(Login.this, "Favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }

                RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog = new AlertDialog.Builder(EquipeFutsalConfig.this);
                        //alertDialog.setTitle("Resposta do servidor:");
                        alertDialog.setMessage("Resposta: " + response);
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editTextGoleiro.setText("");
                                editTextFixo.setText("");
                                editTextAlaEsq.setText("");
                                editTextAlaDir.setText("");
                                editTextPivo.setText("");
                                editTextGoleiroRes.setText("");
                                editTextFixoRes.setText("");
                                editTextAlaEsqRes.setText("");
                                editTextAlaDirRes.setText("");
                                editTextPivoRes.setText("");
                                editTextGoleiroResRes.setText("");
                                editTextJogadorExtra.setText("");
                            }
                        });
                        AlertDialog alertDialog2 = alertDialog.create();
                        alertDialog2.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EquipeFutsalConfig.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nomeTorneio", stNomeTorneio);
                        params.put("IDtorneio", stIDTorneio);
                        params.put("nomeGoleiro", stnomeGoleiro);
                        params.put("nomeFixo", stnomeFixo);
                        params.put("nomeAlaEsq", stnomeAlaEsq);
                        params.put("nomeAlaDir", stnomeAlaDir);
                        params.put("nomePivo", stnomePivo);
                        params.put("nomeGoleiroRes", stnomeGoleiroRes);
                        params.put("nomeFixoRes", stnomeFixoRes);
                        params.put("nomeAlaEsqRes", stnomeAlaEsqRes);
                        params.put("nomeAlaDirRes", stnomeAlaDirRes);
                        params.put("nomePivoRes", stnomePivoRes);
                        params.put("nomeGoleiroResRes", stnomeGoleiroResRes);
                        params.put("nomeJogadorExtra", stnomeJogadorExtra);
                        return params;
                    }
                };
                queue.add(stringRequest);*/
         //   }
        //});
    }
    String stNomeTorneio;
    String stIDTorneio;
    public void UserLogin() {
        String stIDnomeTorneio = (String) spinner.getSelectedItem();
        String[] separaNomeTorneio = stIDnomeTorneio.split("-");
        stNomeTorneio = separaNomeTorneio[1];
        stIDTorneio =  String.valueOf(somenteDigitos(stIDnomeTorneio));
        //progressDialog.setMessage("Please Wait");
        //progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String ServerResponse) {
                //progressDialog.dismiss();
                if(ServerResponse.equalsIgnoreCase("ok")) {
                    Toast.makeText(EquipeFutsalConfig.this, "Equipe cadastrada com sucesso!", Toast.LENGTH_LONG).show();
                    editTextGoleiro.setText(null);
                    editTextFixo.setText(null);
                    editTextAlaEsq.setText(null);
                    editTextAlaDir.setText(null);
                    editTextPivo.setText(null);
                    editTextGoleiroRes.setText(null);
                    editTextFixoRes.setText(null);
                    editTextAlaEsqRes.setText(null);
                    editTextAlaDirRes.setText(null);
                    editTextPivoRes.setText(null);
                    editTextGoleiroResRes.setText(null);
                    editTextJogadorExtra.setText(null);
                    buttonCadastrarEquipe.setText(null);

                }
                else {
                    Toast.makeText(EquipeFutsalConfig.this, ServerResponse, Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //progressDialog.dismiss();
                        Toast.makeText(EquipeFutsalConfig.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("nomeTorneio", stNomeTorneio);
                params.put("IDtorneio", stIDTorneio);
                params.put("nomeGoleiro", stNomeGoleiro);
                params.put("nomeFixo", stNomeFixo);
                params.put("nomeAlaEsq", stNomeAlaEsq);
                params.put("nomeAlaDir", stNomeAlaDir);
                params.put("nomePivo", stNomePivo);
                params.put("nomeGoleiroRes", stNomeGoleiroRes);
                params.put("nomeFixoRes", stNomeFixoRes);
                params.put("nomeAlaEsqRes", stNomeAlaEsqRes);
                params.put("nomeAlaDirRes", stNomeAlaDirRes);
                params.put("nomePivoRes", stNomePivoRes);
                params.put("nomeGoleiroResRes", stNomeGoleiroResRes);
                params.put("nomeJogadorExtra", stNomeJogadorExtra);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EquipeFutsalConfig.this);
        requestQueue.add(stringRequest);
    }


    int idTorneio;
    String nomeTorneio;
    private void loadSpinnerData(String url) {
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
                        //TorneioName.add(nomeTorneio);
                        String var = String.valueOf(idTorneio);
                        listaTorneios.add(var+"-"+nomeTorneio);
                        //TorneioID.add();
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(EquipeFutsalConfig.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));
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

    public void VerificaCamposVazios() {
        stNomeGoleiro  = editTextGoleiro.getText().toString().trim();
        stNomeFixo  = editTextFixo.getText().toString().trim();
        stNomeAlaEsq  = editTextAlaEsq.getText().toString().trim();
        stNomeAlaDir  = editTextAlaDir.getText().toString().trim();
        stNomePivo  = editTextPivo.getText().toString().trim();
        stNomeGoleiroRes  = editTextGoleiroRes.getText().toString().trim();
        stNomeFixoRes  = editTextFixoRes.getText().toString().trim();
        stNomeAlaEsqRes  = editTextAlaEsqRes.getText().toString().trim();
        stNomeAlaDirRes  = editTextAlaDirRes.getText().toString().trim();
        stNomePivoRes  = editTextPivoRes.getText().toString().trim();
        stNomeGoleiroResRes  = editTextGoleiroResRes.getText().toString().trim();
        stNomeJogadorExtra = editTextJogadorExtra.getText().toString().trim();

        CheckEditText = !TextUtils.isEmpty(stNomeGoleiro) && !TextUtils.isEmpty(stNomeFixo) && !TextUtils.isEmpty(stNomeAlaEsq)
                && !TextUtils.isEmpty(stNomeAlaDir) && !TextUtils.isEmpty(stNomePivo) && !TextUtils.isEmpty(stNomeGoleiroRes)
                && !TextUtils.isEmpty(stNomeFixoRes) && !TextUtils.isEmpty(stNomeAlaEsqRes) && !TextUtils.isEmpty(stNomeAlaDirRes)
                && !TextUtils.isEmpty(stNomePivoRes) && !TextUtils.isEmpty(stNomeGoleiroResRes) && !TextUtils.isEmpty(stNomeJogadorExtra);
    }
}
