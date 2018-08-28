package com.example.paulo.apptecnico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PartidaConfig extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida_config);

        Spinner spinner = findViewById(R.id.campeonato);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.campeonato, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void clickBtnIrAcoes(View view){
        Intent it;
        it = new Intent(PartidaConfig.this, Acoes.class);
        startActivity(it);
    }




}
