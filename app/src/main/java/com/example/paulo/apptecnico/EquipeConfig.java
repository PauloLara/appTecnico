package com.example.paulo.apptecnico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EquipeConfig extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe_config);

    }

    public void clickBtnIrEquipeConfig(View view){
        Intent it;
        it = new Intent(EquipeConfig.this, EquipeFutsalConfig.class);
        startActivity(it);
    }

    public void btnIrCadastradas(View view){
        Intent it;
        it = new Intent(EquipeConfig.this, EquipesCadastradas.class);
        startActivity(it);
    }
}
