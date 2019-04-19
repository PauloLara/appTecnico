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

    public void clickBtnIrPartidaConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, EscolheModalidade.class);
        startActivity(it);
    }
    //cadastra um novo torneio
    public void clickBtnIrTorneioCadastro(View view){
        Intent it;
        it = new Intent(Inicial.this, TorneioCadastro.class);
        startActivity(it);
    }

    public  void clickBtnLogout(View view){
        Toast.makeText(Inicial.this, "Desconectado", Toast.LENGTH_LONG).show();
        finish();
        Intent intent;
        intent = new Intent(Inicial.this, Login.class);
        startActivity(intent);
    }

    public void clickBtnIrEquipeConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, EquipeConfig.class);
        startActivity(it);
    }
}
