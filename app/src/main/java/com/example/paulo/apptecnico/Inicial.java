package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Inicial extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        viewPager = (ViewPager) findViewById(R.id.carrossel);

        CarrosselAdaptor carrosselAdaptor = new CarrosselAdaptor(this);

        viewPager.setAdapter(carrosselAdaptor);

    }

    public void clickBtnIrPartidaConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, PartidaConfig.class);
        startActivity(it);
    }
}
