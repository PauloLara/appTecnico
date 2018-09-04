package com.example.paulo.apptecnico;
import android.os.Environment;
import android.widget.Toast;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            bfr.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fecharArquivo() throws IOException {
        bfr.flush();
        bfr.close();
    }
}
