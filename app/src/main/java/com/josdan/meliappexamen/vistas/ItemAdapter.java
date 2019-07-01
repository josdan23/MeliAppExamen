package com.josdan.meliappexamen.vistas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.josdan.meliappexamen.R;
import com.josdan.meliappexamen.dominio.Condicion;
import com.josdan.meliappexamen.dominio.Publicacion;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    private List<Publicacion> publicaciones = new ArrayList<>();
    private Context context;

    public ItemAdapter(Context context){
        this.context = context;
    }

    public void setPublicaciones(List<Publicacion> publicaciones){
        this.publicaciones.addAll(publicaciones);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_publicacion_layout, viewGroup, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.tituloTv.setText(publicaciones.get(i).getTitulo());
        viewHolder.precioTv.setText("$" + publicaciones.get(i).getPrecioBase());
        viewHolder.condicionTv.setText(publicaciones.get(i).getCondicion().name());

        if (publicaciones.get(i).getCondicion() == Condicion.NUEVO)
            viewHolder.condicionTv.setTextColor(context.getColor(R.color.colorNuevo));
        else
            viewHolder.condicionTv.setTextColor(context.getColor(R.color.colorUsado));

        Picasso.with(context).load(publicaciones.get(i).getImagen()).into(viewHolder.imagenIv);
    }

    @Override
    public int getItemCount() {
        return publicaciones.size();
    }

    public String getId(int position){
        return publicaciones.get(position).getIdPublicacion();
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(v);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
}


class ViewHolder extends RecyclerView.ViewHolder{

    public TextView tituloTv;
    public TextView precioTv;
    public TextView condicionTv;
    public ImageView imagenIv;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        tituloTv = itemView.findViewById(R.id.titulo_item_tv);
        precioTv = itemView.findViewById(R.id.precio_item_tv);
        condicionTv = itemView.findViewById(R.id.condicion_item_tv);
        imagenIv = itemView.findViewById(R.id.imagen_item_iv);
    }
}