package com.example.paulo.apptecnico;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
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

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MeioEventos extends Activity {

    Button btnVerificaEquipe;
    AlertDialog.Builder alertDialog;
    Spinner spinnerTorneio, spinnerAdversario;
    String URLsp = "http://192.168.15.17/busca_torneios.php";
    String URLspcl = "http://192.168.15.17/busca_adversarios.php";
    //String URLtv = "http://192.168.15.17/busca_dados_equipe.php";
    String stNomeTorneio, stIDTorneio;
    String URLtv = "http://192.168.15.17/busca_equipe_por_torneio.php";
    String URLbuscaPorTorneio = "http://192.168.15.17/busca_equipe_por_torneio.php";
    String URLev = "http://192.168.15.17/insere_eventos.php";
    TextView tvGoleiro, tvFixo, tvAlaEsq, tvAlaDir, tvPivo, tvGoleiroRes, tvFixoRes, tvAlaEsqRes, tvAlaDirRes, tvPivoRes, tvGoleiroResRes,
            tvJogadorExtra, tvJogadorExtra1, tvJogadorExtra2, tvJogadorExtra3, tvJogadorExtra4, tvJogadorExtra5, tvJogadorExtra6,
            tvApelidoEquipe;
    Button btnComecar;
    TextView textViewGoleiroAtleta, textAtletaGoleiro, textViewGoleiroPasseErrado, textViewGoleiroChuteAgol, textViewGoleiroPerdida, textViewGoleiroInterceptacao;
    TextView textViewFixoAtleta, textViewFixoPasseErrado, textViewFixoChuteAgol, textViewFixoPerdida, textViewFixoInterceptacao;
    TextView textViewAlaEsqAtleta, textViewAlaEsqPasseErrado, textViewAlaEsqChuteAgol, textViewAlaEsqPerdida, textViewAlaEsqInterceptacao;
    TextView textViewAlaDirAtleta, textViewAlaDirPasseErrado, textViewAlaDirChuteAgol, textViewAlaDirPerdida, textViewAlaDirInterceptacao;
    TextView textViewPivoAtleta, textViewPivoPasseErrado, textViewPivoChuteAgol, textViewPivoPerdida, textViewPivoInterceptacao;
    EditText editTextDate;
    int idTorneio, idAdversario;
    EditText date;
    int total = 12;
    int numberOfCheckboxesChecked = 0;
    DatePickerDialog datePickerDialog;
    CheckBox chkGoleiro, chkFixo, chkAlaEsq, chkAlaDir, chkPivo, chkGoleiroRes, chkFixoRes, chkAlaEsqRes,
            chkAlaDirRes, chkPivoRes, chkGoleiroResRes, chkJogadorExtra, chkJogadorExtra1,
            chkJogadorExtra2, chkJogadorExtra3, chkJogadorExtra4, chkJogadorExtra5, chkJogadorExtra6;
    final ArrayList<String> listaTexts = new ArrayList<>();

    //INÍCIO - AQUI POPULA O SPINNER DE ADVERSARIOS COM DADOS DO BANCO *******************************************************************
    private void loadSpinnerAdversarios(String urlSpin) {
        final ArrayList<String> listaAdversarios = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlSpin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("adversarios");
                    listaAdversarios.add("Selecione o adversário:");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        idAdversario = jsonObject1.getInt("ID_adversario");
                        String nomeAdversario = jsonObject1.getString("nomeAdversario");
                        listaAdversarios.add(nomeAdversario+"-"+idAdversario);
                    }
                    spinnerAdversario.setAdapter(new ArrayAdapter<String>(MeioEventos.this, android.R.layout.simple_spinner_dropdown_item, listaAdversarios));

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
        setContentView(R.layout.activity_meio_eventos);

        date = (EditText) findViewById(R.id.date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MeioEventos.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText(year + "-"+ (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnComecar = findViewById(R.id.começar);
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

        textViewPivoAtleta = findViewById(R.id.posicao);
        textViewPivoPasseErrado = findViewById(R.id.nomeJogador);
        textViewPivoChuteAgol = findViewById(R.id.numeroJogador);
        textViewPivoPerdida = findViewById(R.id.perdida_pivo);
        textViewPivoInterceptacao = findViewById(R.id.intercep_pivo);
        editTextDate = findViewById(R.id.date);
        spinnerTorneio = findViewById(R.id.spTorneio);
        spinnerAdversario = findViewById(R.id.spinnerAdversario);
        btnVerificaEquipe = findViewById(R.id.btnVerificaEquipe);

        tvApelidoEquipe = findViewById(R.id.txtNomeEquipe);
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
        tvJogadorExtra1 = findViewById(R.id.fieldJogadorExtra1);
        tvJogadorExtra2 = findViewById(R.id.fieldJogadorExtra2);
        tvJogadorExtra3 = findViewById(R.id.fieldJogadorExtra3);
        tvJogadorExtra4 = findViewById(R.id.fieldJogadorExtra4);
        tvJogadorExtra5 = findViewById(R.id.fieldJogadorExtra5);
        tvJogadorExtra6 = findViewById(R.id.fieldJogadorExtra6);

        chkGoleiro = findViewById(R.id.chkGoleiro);
        chkFixo = findViewById(R.id.chkFixo);
        chkAlaEsq = findViewById(R.id.chkAlaEsq);
        chkAlaDir = findViewById(R.id.chkAlaDir);
        chkPivo = findViewById(R.id.chkPivo);
        chkGoleiroRes = findViewById(R.id.chkGoleiroRes);
        chkFixoRes = findViewById(R.id.chkFixoRes);
        chkAlaEsqRes = findViewById(R.id.chkAlaEsqRes);
        chkAlaDirRes = findViewById(R.id.chkAlaDirRes);
        chkPivoRes = findViewById(R.id.chkPivoRes);
        chkGoleiroResRes = findViewById(R.id.chkGoleiroResRes);
        chkJogadorExtra = findViewById(R.id.chkJogadorExtra);
        chkJogadorExtra1 = findViewById(R.id.chkJogadorExtra1);
        chkJogadorExtra2 = findViewById(R.id.chkJogadorExtra2);
        chkJogadorExtra3 = findViewById(R.id.chkJogadorExtra3);
        chkJogadorExtra4 = findViewById(R.id.chkJogadorExtra4);
        chkJogadorExtra5 = findViewById(R.id.chkJogadorExtra5);
        chkJogadorExtra6 = findViewById(R.id.chkJogadorExtra6);

        loadSpinnerTorneios(URLsp);
        spinnerTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String torneio = spinnerTorneio.getItemAtPosition(spinnerTorneio.getSelectedItemPosition()).toString();
                buscaEquipePorTorneio(torneio);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        loadSpinnerAdversarios(URLspcl);
        spinnerAdversario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String adversario = spinnerAdversario.getItemAtPosition(spinnerAdversario.getSelectedItemPosition()).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        btnComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String IDNOmeAdv = spinnerAdversario.getSelectedItem().toString();
                String retiraCaracter = removerCaracteresEspeciais(IDNOmeAdv);
                final String IDAdversario = retiraCaracter.replaceAll("[^0-9]", "");
                final String stNomeAdversario = retiraCaracter.replaceAll("[^a-zA-Z\\s]", "");
                final String stNomeAdv = stNomeAdversario.trim();
                if(numberOfCheckboxesChecked<12){
                    Toast.makeText(MeioEventos.this, "Escolha os 12 atletas", Toast.LENGTH_LONG).show();
                }
                else {
                    //PUTstring MANDA O VALOR DO CAMPO COM UM NOME DE CHAVE QUE É RECUPERADO NA INTENT SEGUINTE
                    Intent it;
                    it = new Intent(MeioEventos.this, ClicarEventosFutsal.class);
                    Bundle extras = new Bundle();
                    extras.putString("tvGoleiro", listaTexts.get(0));
                    extras.putString("tvFixo", listaTexts.get(1));
                    extras.putString("tvAlaEsq", listaTexts.get(2));
                    extras.putString("tvAlaDir", listaTexts.get(3));
                    extras.putString("tvPivo", listaTexts.get(4));
                    extras.putString("tvGoleiroRes", listaTexts.get(5));
                    extras.putString("tvFixoRes", listaTexts.get(6));
                    extras.putString("tvAlaEsqRes", listaTexts.get(7));
                    extras.putString("tvAlaDirRes", listaTexts.get(8));
                    extras.putString("tvPivoRes", listaTexts.get(9));
                    extras.putString("tvGoleiroResRes", listaTexts.get(10));
                    extras.putString("tvJogadorExtra", listaTexts.get(11));
                    extras.putString("tvApelidoEquipe", tvApelidoEquipe.getText().toString());
                    extras.putString("nomeAdversario", stNomeAdv);
                    extras.putString("ID_adversario", IDAdversario);
                    extras.putString("ID_torneio", stIDTorneio);
                    extras.putString("nomeTorneio", stNomeTorneio);
                    extras.putString("dataJogo", date.getText().toString());
                    it.putExtras(extras);
                    startActivity(it);
                }
            }
        });

        chkGoleiro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkGoleiro.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvGoleiro.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvGoleiro.getText().toString());
                    }
                }
            }
        });

        chkFixo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkFixo.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvFixo.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvFixo.getText().toString());
                    }
                }
            }
        });

        chkAlaEsq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkAlaEsq.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvAlaEsq.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvAlaEsq.getText().toString());
                    }
                }
            }
        });

        chkAlaDir.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkAlaDir.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvAlaDir.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvAlaDir.getText().toString());
                    }
                }
            }
        });

        chkPivo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkPivo.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvPivo.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvPivo.getText().toString());
                    }
                }
            }
        });

        chkGoleiroRes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkGoleiroRes.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvGoleiroRes.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvGoleiroRes.getText().toString());
                    }
                }
            }
        });

        chkFixoRes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkFixoRes.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvFixoRes.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvFixoRes.getText().toString());
                    }
                }
            }
        });

        chkAlaEsqRes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkAlaEsqRes.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvAlaEsqRes.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvAlaEsqRes.getText().toString());
                    }
                }
            }
        });

        chkAlaDirRes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkAlaDirRes.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvAlaDirRes.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvAlaDirRes.getText().toString());
                    }
                }
            }
        });

        chkPivoRes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkPivoRes.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvPivoRes.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvPivoRes.getText().toString());
                    }
                }
            }
        });

        chkGoleiroResRes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkGoleiroResRes.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvGoleiroResRes.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvGoleiroResRes.getText().toString());
                    }
                }
            }
        });

        chkJogadorExtra.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkJogadorExtra.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvJogadorExtra.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvJogadorExtra.getText().toString());
                    }
                }
            }
        });

        chkJogadorExtra1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkJogadorExtra1.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvJogadorExtra1.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvJogadorExtra1.getText().toString());
                    }
                }
            }
        });chkJogadorExtra2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkJogadorExtra2.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvJogadorExtra2.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvJogadorExtra2.getText().toString());
                    }
                }
            }
        });

        chkJogadorExtra3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkJogadorExtra3.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvJogadorExtra3.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvJogadorExtra3.getText().toString());
                    }
                }
            }
        });

        chkJogadorExtra4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkJogadorExtra4.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvJogadorExtra4.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvJogadorExtra4.getText().toString());
                    }
                }
            }
        });

        chkJogadorExtra5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkJogadorExtra5.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvJogadorExtra5.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvJogadorExtra5.getText().toString());
                    }
                }
            }
        });

        chkJogadorExtra6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    chkJogadorExtra6.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(tvJogadorExtra6.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(tvJogadorExtra6.getText().toString());
                    }
                }
            }
        });
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
                        idTorneio = jsonObject1.getInt("ID_torneio");
                        String nomeTorneio = jsonObject1.getString("nomeTorneio");
                        String var = String.valueOf(idTorneio);
                        listaTorneios.add(var+" "+nomeTorneio);
                    }
                    spinnerTorneio.setAdapter(new ArrayAdapter<String>(MeioEventos.this, android.R.layout.simple_spinner_dropdown_item, listaTorneios));

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


    public void buscaEquipePorTorneio(String torneio){
        String stIDnomeTorneio = torneio;
        String retiraCaracter = removerCaracteresEspeciais(stIDnomeTorneio);
        String stNomeTorneios = retiraCaracter.replaceAll("[^a-zA-Z\\s]", "");
        String IDTorneio = retiraCaracter.replaceAll("[^0-9]", "");
        stIDTorneio = IDTorneio.trim();
        stNomeTorneio = stNomeTorneios.trim();

        RequestQueue queue = Volley.newRequestQueue(MeioEventos.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLbuscaPorTorneio, new Response.Listener<String>() {
            @Override
            public void onResponse(String resposta) {
                try {
                    JSONObject objetoJson = new JSONObject(resposta);
                    JSONArray jsonArray = objetoJson.getJSONArray("dados");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jogadorj = jsonArray.getJSONObject(i);
                        tvApelidoEquipe.setText(jogadorj.getString("apelidoEquipe"));
                        tvGoleiro.setText(jogadorj.getString("goleiro"));
                        tvFixo.setText(jogadorj.getString("fixo"));
                        tvAlaEsq.setText(jogadorj.getString("alaEsq"));
                        tvAlaDir.setText(jogadorj.getString("alaDir"));
                        tvPivo.setText(jogadorj.getString("pivo"));
                        tvGoleiroRes.setText(jogadorj.getString("goleiroRes"));
                        tvFixoRes.setText(jogadorj.getString("fixoRes"));
                        tvAlaEsqRes.setText(jogadorj.getString("alaEsqRes"));
                        tvAlaDirRes.setText(jogadorj.getString("alaDirRes"));
                        tvPivoRes.setText(jogadorj.getString("pivoRes"));
                        tvGoleiroResRes.setText(jogadorj.getString("goleiroResRes"));
                        tvJogadorExtra.setText(jogadorj.getString("jogadorExtra"));
                        tvJogadorExtra1.setText(jogadorj.getString("jogadorExtra1"));
                        tvJogadorExtra2.setText(jogadorj.getString("jogadorExtra2"));
                        tvJogadorExtra3.setText(jogadorj.getString("jogadorExtra3"));
                        tvJogadorExtra4.setText(jogadorj.getString("jogadorExtra4"));
                        tvJogadorExtra5.setText(jogadorj.getString("jogadorExtra5"));
                        tvJogadorExtra6.setText(jogadorj.getString("jogadorExtra6"));
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(MeioEventos.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
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

    public String removerCaracteresEspeciais(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }

     /*
    //METODO QUE SUBSTITUI TODOS OS "checkBox.setOnCheckedChangeListener" ACIMA

    public void testaCheckBoxes(final CheckBox checkBox, final TextView textView){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && numberOfCheckboxesChecked >=12) {
                    checkBox.setChecked(false);
                } else {
                    if (isChecked) {
                        numberOfCheckboxesChecked++;
                        listaTexts.add(textView.getText().toString());
                        int res = total - numberOfCheckboxesChecked;
                        Toast.makeText(MeioEventos.this, "Restam "+String.valueOf(res)+" escolhas", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOfCheckboxesChecked--;
                        listaTexts.remove(textView.getText().toString());
                    }
                }
            }
        });

    }*/

}

