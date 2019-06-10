package com.example.paulo.apptecnico;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class AcaoQuadra extends AppCompatActivity {
    Button btnaaa, btnbbb, btnccc, btnddd, btneee, btnfff, btnpasseErrado, btninterceptacao, btnchuteAgol, btnchuteFora, btnperdida, btnfaltaCometida;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao_quadra);
        btnaaa = findViewById(R.id.btnaaa);
        btnbbb = findViewById(R.id.btnbbb);
        btnccc = findViewById(R.id.btnccc);
        btnddd = findViewById(R.id.btnddd);
        btneee = findViewById(R.id.btneee);
        btnfff = findViewById(R.id.btnfff);
        btnpasseErrado = findViewById(R.id.btnPasseErrado);
        btninterceptacao = findViewById(R.id.btnInterceptacao);
        btnchuteAgol = findViewById(R.id.btnChuteGol);
        btnchuteFora = findViewById(R.id.btnChuteFora);
        btnfaltaCometida = findViewById(R.id.btnFaltaCometida);
        btnperdida = findViewById(R.id.btnBolaPerdida);

    }

}
