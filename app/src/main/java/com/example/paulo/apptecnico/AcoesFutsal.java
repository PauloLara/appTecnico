package com.example.paulo.apptecnico;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class AcoesFutsal extends Activity {
    String[] atletaArray = {"NOME","Bagé","M. Tobias","Fininho",
            "Ortiz","Choco", "Bela", "Danilo", "Vander", "Vaguinho", "Lenísio", "Schumacher", "Falcão"};
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes_futsal);

        textView = (TextView)findViewById(R.id.TextViewUserLogin);
        String TempHolder = getIntent().getStringExtra("UserEmailTAG");
        textView.setText(textView.getText() + TempHolder);

    }
}
