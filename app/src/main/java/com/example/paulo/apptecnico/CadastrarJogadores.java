package com.example.paulo.apptecnico;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CadastrarJogadores extends AppCompatActivity{
    private Spinner spinnerPosicoes;
    private List<String> nomesPosicoes = new ArrayList<String>();
    private String posicao;
    AdapterRecycler adapterRecycler;
    EditText editTextNomeJogador;  //EditText editTextNomeTorneio;
    EditText editTextNumeroJogador;
    Button buttonCadastrarJogador;  //Button buttonCadastrarTorneio;
    AlertDialog.Builder alertDialog;
    String nomeJogador_text;  //String nomeTorneio_text;
    String numeroJogador;
    Boolean CheckEditText;
    ArrayList<DadosJogadores> listaJogadores;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    ImageView img;
    Button upload;
    private Bitmap bitmap;
    ByteArrayOutputStream saida;
    String encodedImage;
    private RequestQueue mRequestQueue;
    RecyclerView recyclerView;
    String url = "http://192.168.15.17/cadastro_jogador.php";
    String url_busca = "http://192.168.15.17/busca_jogadores.php";

    @SuppressLint("WrongThread")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_jogadores);
        img = findViewById(R.id.imgFoto);


        upload = findViewById(R.id.btnUpload);

        editTextNomeJogador = findViewById(R.id.txtNomeJogador);
        editTextNumeroJogador = findViewById(R.id.numJogador);
        buttonCadastrarJogador = findViewById(R.id.buttonCadastrarEquipe);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaJogadores = new ArrayList<DadosJogadores>();
        mRequestQueue = Volley.newRequestQueue(this);

        nomesPosicoes.add("Selecione a posição:");
        nomesPosicoes.add("goleiro");
        nomesPosicoes.add("fixo");
        nomesPosicoes.add("ala");
        nomesPosicoes.add("pivo");
        nomesPosicoes.add("fixo / ala");
        nomesPosicoes.add("ala / pivo");


        buttonCadastrarJogador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaCamposVazios();
                if (CheckEditText) {
                    CadastraJogador();
                    carregaCadastrados();
                } else {
                    Toast.makeText(CadastrarJogadores.this, "Preencha todos os campos.", Toast.LENGTH_LONG).show();
                }
            }
        });



        spinnerPosicoes = (Spinner) findViewById(R.id.spinnerPosicao);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomesPosicoes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerPosicoes.setAdapter(spinnerArrayAdapter);

        spinnerPosicoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                posicao = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        upload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectImage();
            }
        });


    }//AQUI TERMINA O ONCREATE()



    public void carregaCadastrados(){
        RequestQueue queue = Volley.newRequestQueue(CadastrarJogadores.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_busca, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alertDialog = new AlertDialog.Builder(CadastrarJogadores.this);
                alertDialog.setMessage("Resposta: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("jogadores");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjPosicoes = jsonArray.getJSONObject(i);
                        String posicaoJ = jsonObjPosicoes.getString("posicao");
                        String nomeJ = jsonObjPosicoes.getString("nomeJogador");
                        String numeroJ = jsonObjPosicoes.getString("numeroJogador");
                        //listaJogadores.add(posicaoJ+" "+nomeJ+" "+numeroJ);
                        listaJogadores.add(new DadosJogadores("Posição: "+posicaoJ, "Nome: "+nomeJ, "Número: "+numeroJ));
                    }
                    adapterRecycler = new AdapterRecycler(CadastrarJogadores.this, listaJogadores);
                    recyclerView.setAdapter(adapterRecycler);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastrarJogadores.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });
        queue.add(stringRequest);
    }

    public void CadastraJogador(){
        final String nomeJogador, numeroJogador, stPosicao, imgFoto;

        stPosicao = (String) spinnerPosicoes.getSelectedItem();
        nomeJogador = editTextNomeJogador.getText().toString();
        numeroJogador = editTextNumeroJogador.getText().toString();

        //Código….
        bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
        saida = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
        byte[] imageBytes = saida.toByteArray();
        encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        //Mais código..

        RequestQueue queue = Volley.newRequestQueue(CadastrarJogadores.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                alertDialog = new AlertDialog.Builder(CadastrarJogadores.this);
                //alertDialog.setTitle("Resposta do servidor:");
                alertDialog.setMessage("Resposta: " + response);

                if (response.equalsIgnoreCase("Cadastrado com sucesso")) {
                    editTextNomeJogador.setText("");
                    editTextNumeroJogador.setText("");
                }

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog alertDialog2 = alertDialog.create();
                alertDialog2.show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastrarJogadores.this, "Erro no servidor", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("posicao", stPosicao);
                params.put("nomeJogador", nomeJogador);
                params.put("numeroJogador", numeroJogador);
                params.put("foto", encodedImage);
                return params;
            }
        };
        queue.add(stringRequest);
    }




    private File savebitmap(Bitmap bmp) {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        // String temp = null;
        File file = new File(extStorageDirectory, "temp.png");
        if (file.exists()) {
            file.delete();
            file = new File(extStorageDirectory, "temp.png");

        }
        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }


    private void selectImage() {
        final CharSequence[] options = { "Tirar foto", "Escolha do arquivo","Cancelar" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CadastrarJogadores.this);
        builder.setTitle("Adicionar uma foto:");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Tirar foto"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");


                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Escolha do arquivo"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }



    @SuppressLint("LongLogTag")
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        File photo = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),bitmapOptions);
                    img.setImageBitmap(bitmap);

                    String path = Environment.getExternalStorageDirectory()+ File.separator+ "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("caminho para o arquivo...", picturePath + "");
                img.setImageBitmap(thumbnail);

            }
        }
    }

    public void VerificaCamposVazios() {
        nomeJogador_text = editTextNomeJogador.getText().toString().trim();
        numeroJogador = editTextNumeroJogador.getText().toString().trim();
        CheckEditText = !TextUtils.isEmpty(nomeJogador_text)&& !TextUtils.isEmpty(numeroJogador);
    }
}//AQUI TERMINA A CLASSE

