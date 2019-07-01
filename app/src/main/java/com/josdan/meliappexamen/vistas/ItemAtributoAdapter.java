package com.josdan.meliappexamen.vistas;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.josdan.meliappexamen.R;

import java.util.Map;

public class ItemAtributoAdapter extends RecyclerView.Adapter<AtributoViewHolder>{

    Map<String, String> atributos;

    public ItemAtributoAdapter(Map<String, String> atributos){
        this.atributos = atributos;
    }

    @NonNull
    @Override
    public AtributoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_atributo_layout, viewGroup,false);
        return new AtributoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AtributoViewHolder atributoViewHolder, int i) {
        String key = atributos.keySet().toArray()[i].toString();
        atributoViewHolder.tituloAtributoTv.setText(key);

        String valor = atributos.get(key);
        if (!valor.equals("null"))
            atributoViewHolder.valorAtributoTv.setText(valor);
        else
            atributoViewHolder.valorAtributoTv.setText("-");
    }

    @Override
    public int getItemCount() {
        return atributos.size();
    }
}

class AtributoViewHolder extends RecyclerView.ViewHolder{

    TextView tituloAtributoTv;
    TextView valorAtributoTv;

    public AtributoViewHolder(@NonNull View itemView) {
        super(itemView);

        tituloAtributoTv = itemView.findViewById(R.id.tituloAtributo_tv);
        valorAtributoTv = itemView.findViewById(R.id.valorAtributo_tv);
    }
}
