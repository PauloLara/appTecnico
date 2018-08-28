package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicial extends AppCompatActivity {
    Button novoJogo;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        viewPager = findViewById(R.id.carrossel);
        CarrosselAdaptor carrosselAdaptor = new CarrosselAdaptor(this);
        viewPager.setAdapter(carrosselAdaptor);



    }



    public void clickBtnIrPartidaConfig(View view){

        novoJogo = (Button) findViewById(R.id.btnnovojogo);
        novoJogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it;
                it = new Intent(Inicial.this, PartidaConfig.class);
                startActivity(it);
            }
        });
    }
}
