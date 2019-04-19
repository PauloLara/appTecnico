package com.example.paulo.apptecnico;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    Button buttonCadastrarEquipe;
    AlertDialog.Builder alertDialog;

    Spinner spinner;
    String URL = "http://192.168.15.17/busca_torneios.php";
    ArrayList<String> TorneioName;
    ArrayList<String> TorneioID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe_futsal_config);
        editTextGoleiro = findViewById(R.id.txtGoleiro);
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
        buttonCadastrarEquipe = findViewById(R.id.btnCadEquipeFutsal);

        TorneioName = new ArrayList<>();
        TorneioID = new ArrayList<String>();
        spinner = findViewById(R.id.spinnerTorneio);
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

                RequestQueue queue = Volley.newRequestQueue(EquipeFutsalConfig.this);
                String url = "http://192.168.15.17/cadastroEquipe.php";
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
                queue.add(stringRequest);
            }
        });
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
}
