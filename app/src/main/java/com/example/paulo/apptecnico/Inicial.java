package com.example.paulo.apptecnico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicial extends AppCompatActivity {
    Button novoJogo;
    Button logout;
  //  ViewPager viewPager;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
//      //  viewPager = findViewById(R.id.carrossel);
        //CarrosselAdaptor carrosselAdaptor = new CarrosselAdaptor(this);
 //     //  viewPager.setAdapter(carrosselAdaptor);

        textView = findViewById(R.id.TextViewUserEmail);
        String email = getIntent().getStringExtra("email");
        textView.setText(email);
    }


    public  void clickBtnLogout(View view){
        Toast.makeText(Inicial.this, "Desconectado", Toast.LENGTH_LONG).show();
        finish();
        Intent intent;
        intent = new Intent(Inicial.this, Login.class);
        startActivity(intent);
    }

    public void clickBtnIrPartidaConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, MeioEventos.class);
        startActivity(it);
    }

    public void clickBtnIrEstatisticas(View view){
        Intent it;
        it = new Intent(Inicial.this, Estatisticas.class);
        startActivity(it);
    }

    public void clickBtnIrSistema(View view){
        Intent it;
        it = new Intent(Inicial.this, Sistema.class);
        startActivity(it);
    }

    public void clickBtnIrGraficos(View view){
        Intent it;
        it = new Intent(Inicial.this, Graficos.class);
        startActivity(it);
    }

    public void clickBtnDeletar(View view){
        Intent it;
        it = new Intent(Inicial.this, DeletarDados.class);
        startActivity(it);
    }

    public void clickBtnIrTestLayouts(View view){
        Intent it;
        it = new Intent(Inicial.this, TestLayouts.class);
        startActivity(it);
    }

    public void clickBtnCronometro(View view){
        Intent it;
        it = new Intent(Inicial.this, Cronometro.class);
        startActivity(it);
    }

    public void irAcaoQuadra(View view){
        Intent it;
        it = new Intent(Inicial.this, AcaoQuadra.class);
        startActivity(it);
    }

    public void irDesenharQuadra(View view){
        Intent it;
        it = new Intent(Inicial.this, DesenharQuadra.class);
        startActivity(it);
    }

}