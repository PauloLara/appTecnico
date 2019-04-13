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

public class TesteAcoesFutsal extends Activity {
    int minteger = 0;
    Button btnFinalizar;
    AlertDialog.Builder alertDialog;
    String URLev = "http://192.168.15.17/insere_eventos.php";
    int contador;
    TextView textViewGoleiroAtleta;
    TextView textViewGoleiroPasseErrado;
    TextView textViewGoleiroChuteAgol;
    TextView textViewGoleiroPerdida;
    TextView textViewGoleiroInterceptacao;

    public void increaseInteger(View view) {
        minteger = minteger + 1;
        display(minteger);
    }

    public void decreaseInteger(View view){
        do{ //faça
            minteger = minteger - 1; //pede numero
            if(minteger < 0){
                minteger = 0; //lança exceção
            }
        } while(minteger < 0);
        display(minteger);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(R.id.integer_number);
        displayInteger.setText("" + number);
        contador = number;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_acoes_futsal);
        btnFinalizar = findViewById(R.id.finalizar);

        textViewGoleiroAtleta = findViewById(R.id.fieldGoleiro);
        textViewGoleiroPasseErrado = findViewById(R.id.integer_number);
        textViewGoleiroChuteAgol = findViewById(R.id.integer_number);
        textViewGoleiroPerdida = findViewById(R.id.integer_number);
        textViewGoleiroInterceptacao = findViewById(R.id.integer_number);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String strGoleiroAtleta= (String) textViewGoleiroAtleta.getText();
                final String strGoleiroPasseErrado = (String) textViewGoleiroPasseErrado.getText();
                final String strGoleiroChuteAgol = (String) textViewGoleiroChuteAgol.getText();
                final String strGoleiroPerdida = (String) textViewGoleiroPerdida.getText();
                final String strGoleiroInterceptacao = (String) textViewGoleiroInterceptacao.getText();

                Toast.makeText(getApplicationContext(), strGoleiroPasseErrado, Toast.LENGTH_LONG).show();
                RequestQueue queue = Volley.newRequestQueue(TesteAcoesFutsal.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLev, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        alertDialog = new AlertDialog.Builder(TesteAcoesFutsal.this);
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
                                Toast.makeText(TesteAcoesFutsal.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
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
    }
}