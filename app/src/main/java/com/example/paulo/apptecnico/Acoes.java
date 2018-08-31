package com.example.paulo.apptecnico;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Acoes extends AppCompatActivity {
    public Button gravar;
    private File diretorio;
    private String nomeDiretorio = "arquivoDroid";
    private String caminho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes);
        caminho = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+nomeDiretorio+"/";
        diretorio = new File(caminho);
        diretorio.mkdirs();
        String nomeArquivo = "temporario.txt";
        final File arquivo = new File(caminho, nomeArquivo);
        arquivo.getParentFile().mkdirs();

        gravar = (Button) findViewById(R.id.a1);
        gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileOutputStream arquivoSaida = null;
                try {
                    arquivoSaida = new FileOutputStream(arquivo);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String name = "eu";
                int st = gravar.getId();
                Integer.toString(st);
                try {
                    arquivoSaida.write(st);
                    Toast.makeText(getApplicationContext(), "gravado no arquivo!", Toast.LENGTH_SHORT).show();
                    arquivoSaida.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
