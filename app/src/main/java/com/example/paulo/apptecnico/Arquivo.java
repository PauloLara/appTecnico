package com.example.paulo.apptecnico;
import android.os.Environment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

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
