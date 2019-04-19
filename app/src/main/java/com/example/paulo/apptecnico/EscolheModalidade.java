package com.example.paulo.apptecnico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EscolheModalidade extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolhe_modalidade);
    }

    public void clickBtnFutsal(View view) {
        Intent it;
        it = new Intent(EscolheModalidade.this, AcoesFutsal.class);
        startActivity(it);
    }

    public void clickBtnFutebol(View view) {
        Intent it;
        it = new Intent(EscolheModalidade.this, Acoes.class);
        startActivity(it);
    }


}
