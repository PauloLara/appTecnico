package com.example.paulo.apptecnico;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Acoes extends AppCompatActivity implements View.OnClickListener{
    final Arquivo arquivo = new Arquivo();


    public Button a1, a2, a3, a4, a5, a6, a7, a8, b1, b2, b3, b4, b5, b6, b7, b8, c1, c2, c3,
                  c4, c5, c6, c7, c8, d1, d2, d3, d4, d5, d6, d7, d8, e1, e2, e3, e4, e5, e6,
                  e7, e8, f1, f2, f3, f4, f5, f6, f7, f8, goleiro1, lateral2, zagueiro3, zagueiro4,
                  cabArea5, lateral6, atacante7, volante8, centroavante9, camisa10, atacante11,
                  salvar, finalizar, limpar, passa, passeerrado, impedido, chutegol, chutefora,
                  cabeceio, golnosso, goldeles, escanteiocontra, escanteio, rebote, desarma, intercepta,
                  defende, fazfalta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acoes);

        arquivo.criarTxt();
        arquivo.criarJson();
        arquivo.criarObjJson();

        goleiro1 = findViewById(R.id.goleiro1);
        goleiro1.setOnClickListener(this);
        lateral2 = findViewById(R.id.lat_direito2);
        lateral2.setOnClickListener(this);
        zagueiro3 = findViewById(R.id.zagueiro3);
        zagueiro3.setOnClickListener(this);
        zagueiro4 = findViewById(R.id.zagueiro4);
        zagueiro4.setOnClickListener(this);
        cabArea5 = findViewById(R.id.cabeca_area5);
        cabArea5.setOnClickListener(this);
        lateral6 = findViewById(R.id.lat_esquerdo6);
        lateral6.setOnClickListener(this);
        atacante7 = findViewById(R.id.atacante7);
        atacante7.setOnClickListener(this);
        volante8 = findViewById(R.id.volante8);
        volante8.setOnClickListener(this);
        centroavante9 = findViewById(R.id.centroavante9);
        centroavante9.setOnClickListener(this);
        camisa10 = findViewById(R.id.camisa10);
        camisa10.setOnClickListener(this);
        atacante11 = findViewById(R.id.atacante11);
        atacante11.setOnClickListener(this);

        passa  = findViewById(R.id.passa);
        passa.setOnClickListener(this);
        passeerrado = findViewById(R.id.passeerrado);
        impedido = findViewById(R.id.impedimento);
        impedido.setOnClickListener(this);
        chutegol = findViewById(R.id.chutegol);
        chutegol.setOnClickListener(this);
        chutefora = findViewById(R.id.chutefora);
        chutefora.setOnClickListener(this);
        cabeceio = findViewById(R.id.cabeceio);
        cabeceio.setOnClickListener(this);
        golnosso = findViewById(R.id.golnosso);
        golnosso.setOnClickListener(this);
        goldeles = findViewById(R.id.goldeles);
        goldeles.setOnClickListener(this);
        escanteiocontra = findViewById(R.id.escanteiocontra);
        escanteiocontra.setOnClickListener(this);
        escanteio = findViewById(R.id.escanteio);
        escanteio.setOnClickListener(this);
        rebote = findViewById(R.id.rebote);
        rebote.setOnClickListener(this);
        desarma = findViewById(R.id.desarma);
        desarma.setOnClickListener(this);
        intercepta = findViewById(R.id.intercepta);
        intercepta.setOnClickListener(this);
        defende = findViewById(R.id.defende);
        defende.setOnClickListener(this);
        fazfalta = findViewById(R.id.fazfalta);
        fazfalta.setOnClickListener(this);

        a1 = findViewById(R.id.a1);
        a1.setOnClickListener(this);
        a2 = findViewById(R.id.a2);
        a2.setOnClickListener(this);
        a3 = findViewById(R.id.a3);
        a3.setOnClickListener(this);
        a4 = findViewById(R.id.a4);
        a4.setOnClickListener(this);
        a5 = findViewById(R.id.a5);
        a5.setOnClickListener(this);
        a6 = findViewById(R.id.a6);
        a6.setOnClickListener(this);
        a7 = findViewById(R.id.a7);
        a7.setOnClickListener(this);
        a8 = findViewById(R.id.a8);
        a8.setOnClickListener(this);
        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.b3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.b4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.b5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.b6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.b7);
        b7.setOnClickListener(this);
        b8 = findViewById(R.id.b8);
        b8.setOnClickListener(this);
        c1 = findViewById(R.id.c1);
        c1.setOnClickListener(this);
        c2 = findViewById(R.id.c2);
        c2.setOnClickListener(this);
        c3 = findViewById(R.id.c3);
        c3.setOnClickListener(this);
        c4 = findViewById(R.id.c4);
        c4.setOnClickListener(this);
        c5 = findViewById(R.id.c5);
        c5.setOnClickListener(this);
        c6 = findViewById(R.id.c6);
        c6.setOnClickListener(this);
        c7 = findViewById(R.id.c7);
        c7.setOnClickListener(this);
        c8 = findViewById(R.id.c8);
        c8.setOnClickListener(this);
        d1 = findViewById(R.id.d1);
        d1.setOnClickListener(this);
        d2 = findViewById(R.id.d2);
        d2.setOnClickListener(this);
        d3 = findViewById(R.id.d3);
        d3.setOnClickListener(this);
        d4 = findViewById(R.id.d4);
        d4.setOnClickListener(this);
        d5 = findViewById(R.id.d5);
        d5.setOnClickListener(this);
        d6 = findViewById(R.id.d6);
        d6.setOnClickListener(this);
        d7 = findViewById(R.id.d7);
        d7.setOnClickListener(this);
        d8 = findViewById(R.id.d8);
        d8.setOnClickListener(this);
        e1 = findViewById(R.id.e1);
        e1.setOnClickListener(this);
        e2 = findViewById(R.id.e2);
        e2.setOnClickListener(this);
        e3 = findViewById(R.id.e3);
        e3.setOnClickListener(this);
        e4 = findViewById(R.id.e4);
        e4.setOnClickListener(this);
        e5 = findViewById(R.id.e5);
        e5.setOnClickListener(this);
        e6 = findViewById(R.id.e6);
        e6.setOnClickListener(this);
        e7 = findViewById(R.id.e7);
        e7.setOnClickListener(this);
        e8 = findViewById(R.id.e8);
        e8.setOnClickListener(this);
        f1 = findViewById(R.id.f1);
        f1.setOnClickListener(this);
        f2 = findViewById(R.id.f2);
        f2.setOnClickListener(this);
        f3 = findViewById(R.id.f3);
        f3.setOnClickListener(this);
        f4 = findViewById(R.id.f4);
        f4.setOnClickListener(this);
        f5 = findViewById(R.id.f5);
        f5.setOnClickListener(this);
        f6 = findViewById(R.id.f6);
        f6.setOnClickListener(this);
        f7 = findViewById(R.id.f7);
        f7.setOnClickListener(this);
        f8 = findViewById(R.id.f8);
        f8.setOnClickListener(this);

        salvar = findViewById(R.id.salvar);
        finalizar = findViewById(R.id.finalizar);
        limpar = findViewById(R.id.limpar);
    }

    public void clickBtnVoltarInicial(){
        Intent it;
        it = new Intent(Acoes.this, Inicial.class);
        startActivity(it);
    }

    public void limpar(View v) throws IOException {
        arquivo.limparTxt();
        arquivo.limparJson();
    }

    public void salvar(View v) throws IOException {
        arquivo.escreverTxt("}");
        arquivo.novaLinhaTxt();
        arquivo.escreverTxt("{\n");
    }

    public void finalizar(View v) throws IOException {
        arquivo.escreverTxt("]\n}");
        arquivo.fecharTxt();
        arquivo.fecharJson();
        clickBtnVoltarInicial();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onClick(View v) {
        int idd = v.getId();
        String st = v.getResources().getResourceEntryName(idd);

        if(st.equalsIgnoreCase("a1")||st.equalsIgnoreCase("a2")||
                st.equalsIgnoreCase("a3")||st.equalsIgnoreCase("a4")||
                st.equalsIgnoreCase("a5")||st.equalsIgnoreCase("a6")||
                st.equalsIgnoreCase("a7")||st.equalsIgnoreCase("a8")||
                st.equalsIgnoreCase("a1")||st.equalsIgnoreCase("a2")||
                st.equalsIgnoreCase("a3")||st.equalsIgnoreCase("a4")||
                st.equalsIgnoreCase("a5")||st.equalsIgnoreCase("a6")||
                st.equalsIgnoreCase("a7")||st.equalsIgnoreCase("a8")||
                st.equalsIgnoreCase("b1")||st.equalsIgnoreCase("b2")||
                st.equalsIgnoreCase("b3")||st.equalsIgnoreCase("b4")||
                st.equalsIgnoreCase("b5")||st.equalsIgnoreCase("b6")||
                st.equalsIgnoreCase("b7")||st.equalsIgnoreCase("b8")||
                st.equalsIgnoreCase("c1")||st.equalsIgnoreCase("c2")||
                st.equalsIgnoreCase("c3")||st.equalsIgnoreCase("c4")||
                st.equalsIgnoreCase("c5")||st.equalsIgnoreCase("c6")||
                st.equalsIgnoreCase("c7")||st.equalsIgnoreCase("c8")||
                st.equalsIgnoreCase("d1")||st.equalsIgnoreCase("d2")||
                st.equalsIgnoreCase("d3")||st.equalsIgnoreCase("d4")||
                st.equalsIgnoreCase("d5")||st.equalsIgnoreCase("d6")||
                st.equalsIgnoreCase("d7")||st.equalsIgnoreCase("d8")||
                st.equalsIgnoreCase("e1")||st.equalsIgnoreCase("e2")||
                st.equalsIgnoreCase("e3")||st.equalsIgnoreCase("e4")||
                st.equalsIgnoreCase("e5")||st.equalsIgnoreCase("e6")||
                st.equalsIgnoreCase("e7")||st.equalsIgnoreCase("e8")||
                st.equalsIgnoreCase("f1")||st.equalsIgnoreCase("f2")||
                st.equalsIgnoreCase("f3")||st.equalsIgnoreCase("f4")||
                st.equalsIgnoreCase("f5")||st.equalsIgnoreCase("f6")||
                st.equalsIgnoreCase("f7")||st.equalsIgnoreCase("f8"))
        {
            arquivo.escreverTxt("\"Área\": ");
            arquivo.escreverTxt('"'+st+'"');
            arquivo.escreverTxt(",");
            try {
                arquivo.novaLinhaTxt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(st.equalsIgnoreCase("goleiro1")||st.equalsIgnoreCase("lateral2")||
                st.equalsIgnoreCase("zagueiro3")||st.equalsIgnoreCase("cabArea5")||
                st.equalsIgnoreCase("zagueiro4")||st.equalsIgnoreCase("lateral6")||
                st.equalsIgnoreCase("atacante7")||st.equalsIgnoreCase("volante8")||
                st.equalsIgnoreCase("centroavante9")||st.equalsIgnoreCase("camisa10")||
                st.equalsIgnoreCase("atacante11"))
        {
            arquivo.escreverTxt("\"Jogador\": ");
            arquivo.escreverTxt('"'+st+'"');
            arquivo.escreverTxt(",");
            try {
                arquivo.novaLinhaTxt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(
                st.equalsIgnoreCase("escanteiocontra")||
                st.equalsIgnoreCase("impedido")||st.equalsIgnoreCase("escanteio")||
                st.equalsIgnoreCase("chutegol")||st.equalsIgnoreCase("desarma")||
                st.equalsIgnoreCase("rebote")||st.equalsIgnoreCase("golnosso")||
                st.equalsIgnoreCase("rebotefora")||st.equalsIgnoreCase("goldeles")||
                st.equalsIgnoreCase("intercepta")||
                st.equalsIgnoreCase("defende")||st.equalsIgnoreCase("camarelo")||
                st.equalsIgnoreCase("cvermelho")||st.equalsIgnoreCase("fazfalta"))
        {
            arquivo.escreverTxt("\"Ação\": ");
            arquivo.escreverTxt('"'+st+'"');
            arquivo.escreverTxt(",");
            try {
                arquivo.novaLinhaTxt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //CRIA JSON
        try {
            arquivo.escreverJson(st);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            arquivo.novaLinhaJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            arquivo.flushJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), st, Toast.LENGTH_SHORT).show();
        try {
            arquivo.flushTxt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerArquivo(View v) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Conteúdo:");
            FileReader fr = new FileReader(arquivo.getCaminho()+arquivo.getNomeTxt());
            BufferedReader br;
            br = new BufferedReader(fr);
            String digitado = br.readLine();
            while (digitado != null) {
                builder.setMessage(digitado).show();
                digitado = br.readLine();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}




