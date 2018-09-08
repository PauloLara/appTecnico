package com.example.paulo.apptecnico;
import android.os.Environment;
import android.support.v4.app.LoaderManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore;
import java.util.Scanner;

public class Arquivo {
    //NOME DA PASTA DENTRO DE DOWNLOADS
    public String getNomeDiretorio() {
        return "arquivoDroid";
    }
    //CAMINHO ATÉ A PASTA
    public String getCaminho() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/"+getNomeDiretorio()+"/";
    }

    File diretorio = new File(getCaminho());

    public void setDiretorio(File diretorio) {
        this.diretorio = diretorio;
        diretorio.mkdir();
    }

    public String getNomeArquivo() {
        return "temporario.txt";
    }

    public File getArquivo() {
        return new File(getCaminho(), getNomeArquivo());
    }

    BufferedWriter bfr = null;

    public void criarArquivo(){
        this.setDiretorio(diretorio);
        try {
            bfr = new BufferedWriter(new FileWriter(getArquivo()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escreverArquivo(String st){
        try {
            bfr.write(st);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flushArquivo() throws IOException {
        bfr.flush();
    }

    public void fecharArquivo() throws IOException {
        bfr.close();
    }

    public  void novaLinha() throws IOException {
        bfr.newLine();
    }

    public  void limparArquivo() throws IOException {
        bfr = new BufferedWriter(new FileWriter(getArquivo()));
    }


}
