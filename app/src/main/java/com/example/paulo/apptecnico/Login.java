package com.example.paulo.apptecnico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    // Creating EditText.
    EditText Email, Password;

    // Creating button;
    Button LoginButton;

    // Creating Volley RequestQueue.
    RequestQueue requestQueue;

    // Create string variable to hold the EditText Value.
    String email_Text, senha_Text;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Storing server url into String variable.
    String HttpUrl = "http://192.168.15.17/user_login.php";
    //String HttpUrl = "http://192.168.15.17/busca.php";

    Boolean CheckEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Assigning ID's to EditText.
        Email = (EditText) findViewById(R.id.email);

        Password = (EditText) findViewById(R.id.senha);

        // Assigning ID's to Button.
        LoginButton = (Button) findViewById(R.id.entrar);

        // Creating Volley newRequestQueue .
        requestQueue = Volley.newRequestQueue(Login.this);

        // Assigning Activity this to progress dialog.
        progressDialog = new ProgressDialog(Login.this);

        // Adding click listener to button.
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if (CheckEditText) {

                    UserLogin();

                } else {

                    Toast.makeText(Login.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    // Creating user login function.
    public void UserLogin() {

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        // Creating string request with post method.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Matching server responce message to our text.
                        if(ServerResponse.equalsIgnoreCase("Dados encontrados")) {

                            // If response matched then show the toast.
                            Toast.makeText(Login.this, "Logado com sucesso!", Toast.LENGTH_LONG).show();

                            // Finish the current Login activity.
                            finish();

                            // Opening the user profile activity using intent.
                            Intent intent;
                            intent = new Intent(Login.this, Inicial.class);

                            // Sending User Email to another activity using intent.
                            intent.putExtra("UserEmailTAG", email_Text);

                            startActivity(intent);
                        }
                        else {

                            // Showing Echo Response Message Coming From Server.
                            Toast.makeText(Login.this, ServerResponse, Toast.LENGTH_LONG).show();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        // Hiding the progress dialog after all task complete.
                        progressDialog.dismiss();

                        // Showing error message if something goes wrong.
                        Toast.makeText(Login.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("email", email_Text);
                params.put("senha", senha_Text);

                return params;
            }

        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

    }


    public void CheckEditTextIsEmptyOrNot() {

        // Getting values from EditText.
        email_Text = Email.getText().toString().trim();
        senha_Text = Password.getText().toString().trim();

        // Checking whether EditText value is empty or not.
        if (TextUtils.isEmpty(email_Text) || TextUtils.isEmpty(senha_Text)) {

            // If any of EditText is empty then set variable value as False.
            CheckEditText = false;

        } else {

            // If any of EditText is filled then set variable value as True.
            CheckEditText = true;
        }
    }

    public void clickBtnIrCadastrar(View view){
        Intent it;
        it = new Intent(Login.this, Cadastro.class);
        startActivity(it);
    }
}