package com.example.paulo.apptecnico;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ExampleViewHolder> {
    private Context contexto;
    private ArrayList<DadosJogadores> lista;

    public AdapterRecycler(Context context, ArrayList<DadosJogadores> esta_lista) {
        contexto = context;
        lista = esta_lista;
    }


    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(contexto).inflate(R.layout.activity_dados_jogadores, parent, false);
        return new ExampleViewHolder(v);
    }

    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        DadosJogadores blocoDados = lista.get(position);

        String nomeJogador = blocoDados.getNomeJ();
        String posicao = blocoDados.getPosJ();
        String numeroJogador = blocoDados.getNumJ();
        String categoriaJogador = blocoDados.getCatJ();

        holder.nomeJogador.setText(nomeJogador);
        holder.posicao.setText(posicao);
        holder.numeroJogador.setText(numeroJogador);
        holder.categoriaJogador.setText(categoriaJogador);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeJogador;
        public TextView posicao;
        public TextView numeroJogador;
        public TextView categoriaJogador;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            nomeJogador = itemView.findViewById(R.id.nomeJog);
            posicao = itemView.findViewById(R.id.posicaoJog);
            numeroJogador = itemView.findViewById(R.id.numeroJog);
            categoriaJogador = itemView.findViewById(R.id.categoriaJog);
        }
    }
}