package com.example.paulo.apptecnico;

import android.os.Environment;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

    BufferedWriter bfrTxt = null;
    BufferedWriter bfrJson = null;
    FileWriter file = null;

    public String getNomeDiretorio() {
        return "arquivoDroid";
    }

    public String getCaminho() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+getNomeDiretorio()+"/";
    }

    File diretorio = new File(getCaminho());
    public void setDiretorio(File diretorio) {
        this.diretorio = diretorio;
        diretorio.mkdir();
    }

    //ARQUIVO JSON *************ARQUIVO JSON *************ARQUIVO JSON *************ARQUIVO JSON *************ARQUIVO JSON *************

    public String getJson(){
        return getCaminho()+"jsonCreate.json";
    }

   public void criarJson(){
        try {
            bfrJson = new BufferedWriter(new FileWriter(getJson()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JSONObject obj = null;

    public void criarObjJson(){
        obj = new JSONObject();
    }

    public void escreverJson(String st) throws JSONException {
        obj.put("Nome: ",st);
        try {
            bfrJson.write(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flushJson() throws IOException {
        bfrJson.flush();
    }

    public  void novaLinhaJson() throws IOException {
        bfrJson.newLine();
    }

    public  void limparJson() throws IOException {
        bfrJson = new BufferedWriter(new FileWriter(getJson()));
    }

    public void fecharJson() throws IOException {
        bfrJson.close();
    }



    //ARQUIVO TXT *************ARQUIVO TXT *************ARQUIVO TXT *************ARQUIVO TXT *************ARQUIVO TXT *************

    public void escreverTxt(String st){
        try {
            bfrTxt.write(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flushTxt() throws IOException {
        bfrTxt.flush();
    }

    public void fecharTxt() throws IOException {
        bfrTxt.close();
    }

    public  void novaLinhaTxt() throws IOException {
        bfrTxt.newLine();
    }

    public  void limparTxt() throws IOException {
        bfrTxt = new BufferedWriter(new FileWriter(getTxt()));
    }

    public File getTxt() {
        return new File(getCaminho(), getNomeTxt());
    }

    public void criarTxt(){
        this.setDiretorio(diretorio);
        try {
            bfrTxt = new BufferedWriter(new FileWriter(getTxt()));
            bfrTxt.write("{\n");
            bfrTxt.write("\"Jogadas\":[\n{\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedReader buffReader;
    public void removeUltimaLinha() throws IOException {
        {
            try {
                buffReader = new BufferedReader(new FileReader(getTxt()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        String lineFromUserFile = null;
        boolean needAddNewLine = false;
        while ((lineFromUserFile = buffReader.readLine()) != null) {
            String lineToRemoveFromFile = "{";
            if (!lineFromUserFile.trim().equals(lineToRemoveFromFile)) {
                if (needAddNewLine) {
                    bfrTxt.newLine();
                }
                needAddNewLine = true;
                bfrTxt.write(lineFromUserFile);
            }
        }
    }

    public String getNomeTxt() {
        return "temp.txt";
    }

}
