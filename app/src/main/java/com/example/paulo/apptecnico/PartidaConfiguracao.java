package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

public class PartidaConfiguracao extends AppCompatActivity {
    Spinner spinner;
    String URL = "http://192.168.15.17/busca_torneios.php";
    ArrayList<String> TorneioName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_configuracao);
        TorneioName=new ArrayList<>();
        spinner=(Spinner)findViewById(R.id.spinnerTorneio);
        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String torneio = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                Toast.makeText(getApplicationContext(),torneio,Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void loadSpinnerData(String url) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    //if(jsonObject.getInt("success")==1){
                        JSONArray jsonArray=jsonObject.getJSONArray("");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String torneio = jsonObject1.getString("nomeTorneio");
                            TorneioName.add(torneio);
                        }
                   // }
                    spinner.setAdapter(new ArrayAdapter<String>(PartidaConfiguracao.this, android.R.layout.simple_spinner_dropdown_item, TorneioName));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
}

/*public class PartidaConfiguracao extends AppCompatActivity {
    public static final String NomeTorneioarray = "TorneioNome";
    public static final String JSON_ARRAY = "resultado";
    private JSONArray result;
    Spinner spinnerTorneio;
    private ArrayList<String> arrayList;
    TextView nomeTorneio;
    String url = "http://192.168.15.17/busca_torneios.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_configuracao);
        spinnerTorneio = findViewById(R.id.spinnerTorneio);
        arrayList = new ArrayList<>();
        getdata();
        spinnerTorneio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                nomeTorneio.setText("");
            }
        });
    }

    private void getdata() {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Dados encontrados")) {
                    Toast.makeText(PartidaConfiguracao.this, "Logado com sucesso!", Toast.LENGTH_LONG).show();
                }
                try {
                    JSONObject j = new JSONObject(response);
                    result = j.getJSONArray(JSON_ARRAY);
                    empdetails(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(PartidaConfiguracao.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(PartidaConfiguracao.this);
        requestQueue.add(stringRequest);
    }

    private void empdetails(JSONArray j) {
        for (int i = 0; i < j.length(); i++) {
            try {
                JSONObject json = j.getJSONObject(i);
                arrayList.add(json.getString(NomeTorneioarray));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinnerTorneio.setAdapter(new ArrayAdapter<>(PartidaConfiguracao.this, android.R.layout.simple_spinner_dropdown_item, arrayList));
    }
}*/

    /*private String getemployeeName(int position) {
        String name = "";
        try {
            JSONObject json = result.getJSONObject(position);
            name = json.getString(EmployeeName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name;
    }*/

   /* private String getmailid(int position) {
        String mailid = "";
        try {
            JSONObject json = result.getJSONObject(position);
            mailid = json.getString(EmployeeMailid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mailid;
    }
}*/

/*public class PartidaConfiguracao extends AppCompatActivity {
    ArrayList<String> listItems=new ArrayList<>();
    ArrayAdapter<String> adapter;
    Spinner sp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_configuracao);
        sp=(Spinner)findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listItems);
        sp.setAdapter(adapter);
    }

    private class BackTask extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        protected void onPreExecute(){
            super.onPreExecute();
            list=new ArrayList<>();
        }
        protected Void doInBackground(Void...params){
            InputStream imputStream=null;
            String result="";
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://192.168.15.17/busca_torneios.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                imputStream = entity.getContent();
            }catch(IOException e){
                e.printStackTrace();
            }

            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(imputStream,"utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result+=line;
                }
                imputStream.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            try{
                JSONArray jArray =new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    list.add(jsonObject.getString("iname"));
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result){
            listItems.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }
}*/
