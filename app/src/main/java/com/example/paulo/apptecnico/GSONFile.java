package com.example.paulo.apptecnico;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class GSONFile {
    Arquivo arquivo = new Arquivo();

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void jsonCreate() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("Nome: ","Paulo");
        obj.put("Endereço: ","B. Goncalves");
        obj.put("Fone: ","51 985826397");

        JSONArray listaDeContatos = new JSONArray();
        listaDeContatos.put("Antonio");
        listaDeContatos.put("Arnaldo");
        listaDeContatos.put("Adroaldo");
        obj.put("Contatos: ", listaDeContatos);
        String st = arquivo.getCaminho()+"jsonCreate.json";
        try(FileWriter file = new FileWriter(st)){
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}