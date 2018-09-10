package com.example.paulo.apptecnico;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GSONFile {
    Arquivo arquivo = new Arquivo();

    public JsonObject convertFileToJSON (String fileName){
        String st = arquivo.getCaminho()+""+arquivo.getNomeArquivo();
        // Read from File to String
        JsonObject jsonObject = new JsonObject();
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(st));
            jsonObject = jsonElement.getAsJsonObject();
        } catch (FileNotFoundException e) {

        } catch (IOException ioe){

        }
        return jsonObject;
    }

}