package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Inicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
    }

    public void clickBtnIrPartidaConfig(View view){
        Intent it;
        it = new Intent(Inicial.this, PartidaConfig.class);
        startActivity(it);
    }
}
