package com.example.paulo.apptecnico;

//import android.app.ProgressDialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Login extends AppCompatActivity {
    EditText Email, Password;
    Button LoginButton;
    RequestQueue requestQueue;
    String email_Text, senha_Text;

//    ProgressDialog progressDialog;
    String HttpUrl = "http://192.168.15.17/user_login.php";
    //String HttpUrl = "https://appscout.000webhostapp.com/user_login.php";

    Boolean CheckEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.senha);
        LoginButton = findViewById(R.id.entrar);

        handleSSLHandshake();

        requestQueue = Volley.newRequestQueue(Login.this);
        //progressDialog = new ProgressDialog(Login.this);

        Email.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Email.setHint("");
                    Password.setHint("Senha");
                }
                return false;
            }
        });

        Password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Password.setHint("");
                    Email.setHint("E-mail");
                }
                return false;
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerificaCamposVazios();
                if (CheckEditText) {
                    UserLogin();
                } else {
                    Toast.makeText(Login.this, "Favor, preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    public void UserLogin() {
       /// progressDialog.setMessage("Please Wait");
       /// progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        //progressDialog.dismiss();
                        if(ServerResponse.equalsIgnoreCase("ok")) {
                            //Toast.makeText(Login.this, "Logado com sucesso!", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent;
                            intent = new Intent(Login.this, Inicial.class);
                            intent.putExtra("UserEmailTAG", email_Text);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Login.this, ServerResponse, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //progressDialog.dismiss();
                        Toast.makeText(Login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email_Text);
                params.put("senha", senha_Text);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(stringRequest);
    }

    public void VerificaCamposVazios() {
        email_Text = Email.getText().toString().trim();
        senha_Text = Password.getText().toString().trim();
        CheckEditText = !TextUtils.isEmpty(email_Text) && !TextUtils.isEmpty(senha_Text);
    }

    public void clickBtnIrCadastrar(View view){
        Intent it;
        it = new Intent(Login.this, Cadastro.class);
        startActivity(it);
    }
}