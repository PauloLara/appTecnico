package com.example.paulo.apptecnico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TestLayouts extends AppCompatActivity {
    Button btnDrDr, btnDelLst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_layouts);
        btnDrDr = findViewById(R.id.btnDragDrop);
        btnDelLst = findViewById(R.id.btnDelList);

    }

    //TESTE PARA OS DRAG AND DROP

   public void btnIrDragAndDrop(View view){
        Intent it;
        it = new Intent(TestLayouts.this, GridActivity.class);
        startActivity(it);
    }
    //TESTE PARA OS DRAG AND DROP

    public void btnIrDeleteList(View view){
        Intent it;
        it = new Intent(TestLayouts.this, UserListActivity.class);
        startActivity(it);
    }

    public void btnIrDesenhoQuadra(View view){
        Intent it;
        it = new Intent(TestLayouts.this, DesenharQuadra.class);
        startActivity(it);
    }

}
