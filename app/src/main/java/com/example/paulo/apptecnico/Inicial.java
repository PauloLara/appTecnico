package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Inicial extends AppCompatActivity {
    Button novoJogo;
    Button logout;
    ViewPager viewPager;
    TextView textView;
    String URL_nomeClube = "http://192.168.15.17/user_clube.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        viewPager = findViewById(R.id.carrossel);
        CarrosselAdaptor carrosselAdaptor = new CarrosselAdaptor(this);
        viewPager.setAdapter(carrosselAdaptor);

        textView = (TextView)findViewById(R.id.TextViewUserEmail);
        String TempHolder = getIntent().getStringExtra("UserEmailTAG");
        textView.setText(textView.getText() + TempHolder);
    }

    /*public void clickBtnIrPartidaConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, EscolheModalidade.class);
        startActivity(it);
    }*/

    public void clickBtnIrPartidaConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, MeioEventos.class);
        startActivity(it);
    }


    /*public void clickBtnIrEstatisticas(View view){
        Intent it;
        it = new Intent(Inicial.this, Estatisticas.class);
        startActivity(it);
    }*/
    public void clickBtnIrEstatisticas(View view){
        Intent it;
        it = new Intent(Inicial.this, CadastrarJogadores.class);
        startActivity(it);
    }


    public void clickBtnIrTestLayouts(View view){
        Intent it;
        it = new Intent(Inicial.this, TestLayouts.class);
        startActivity(it);
    }

    public void clickBtnIrGraficos(View view){
        Intent it;
        it = new Intent(Inicial.this, Graficos.class);
        startActivity(it);
    }

    /*public void clickBtnIrEquipeConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, EquipeConfig.class);
        startActivity(it);
    }*/

    public void clickBtnIrSistema(View view){
        Intent it;
        it = new Intent(Inicial.this, Sistema.class);
        startActivity(it);
    }

    public void clickBtnDeletar(View view){
        Intent it;
        it = new Intent(Inicial.this, DeletarDados.class);
        startActivity(it);
    }

    public  void clickBtnLogout(View view){
        Toast.makeText(Inicial.this, "Desconectado", Toast.LENGTH_LONG).show();
        finish();
        Intent intent;
        intent = new Intent(Inicial.this, Login.class);
        startActivity(intent);
    }
}