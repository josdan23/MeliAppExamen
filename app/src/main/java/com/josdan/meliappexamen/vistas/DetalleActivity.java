package com.josdan.meliappexamen.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.josdan.meliappexamen.R;
import com.josdan.meliappexamen.controladores.DetalleDeProductoController;
import com.josdan.meliappexamen.controladores.IDetalleDeProductoView;
import com.josdan.meliappexamen.dominio.Condicion;
import com.josdan.meliappexamen.servicios.ApiMercadoLibre;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

public class DetalleActivity extends AppCompatActivity implements IDetalleDeProductoView {


    //COMPONENTES UI
    ImageView imageView;
    TextView tituloTv;
    TextView precioBaseTv;
    TextView fechaTv;
    TextView nombreTv;
    TextView descripcionTv;
    TextView cantidadDeOpinionesTv;
    TextView cantidadVendidaTv;
    TextView condicionTv;
    TextView reputacionTv;
    RatingBar ratingBar;
    RecyclerView recyclerView;
    TextView descuentoTv;
    ProgressBar progressBar;

    String idPublicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        configurarToolbar();
        inicializarComponentesUI();

        Intent intent = getIntent();
        idPublicacion = intent.getStringExtra(PublicacionesActivity.ID_ITEM_KEY);

        DetalleDeProductoController controller = new DetalleDeProductoController(this);
        controller.setServicioExterno(new ApiMercadoLibre(this));
        controller.verDetalleDePublicacion(idPublicacion);

    }


    private void inicializarComponentesUI(){
        imageView = findViewById(R.id.detalle_imageview);
        tituloTv = findViewById(R.id.titulo_tv);
        precioBaseTv = findViewById(R.id.preciobase_tv);
        fechaTv = findViewById(R.id.fecha_tv);
        nombreTv = findViewById(R.id.vendedor_tv);
        descripcionTv = findViewById(R.id.descripcion_tv);
        cantidadDeOpinionesTv = findViewById(R.id.opiniones_detalle_tv);
        cantidadVendidaTv = findViewById(R.id.cantvendidos_tv);
        recyclerView = findViewById(R.id.recyclerView);
        condicionTv = findViewById(R.id.condicion_tv);
        reputacionTv = findViewById(R.id.reputacion_tv);
        ratingBar = findViewById(R.id.ratingBar);
        descuentoTv = findViewById(R.id.descuento_tv);

        progressBar = findViewById(R.id.progressBar3);

    }

    private void configurarToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detalle");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void mostrarImagenes(List<String> urlsImagenes) {
        if (urlsImagenes != null){
            imageView.setVisibility(View.VISIBLE);
            Picasso.with(this).load(urlsImagenes.get(0)).into(imageView);

            progressBar.setVisibility(View.GONE);
            View view = findViewById(R.id.layout);
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostrarTitulo(String titulo) {
        if (titulo != null)
        {
            tituloTv.setText(titulo);
            tituloTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostrarPrecio(float precio) {
        if(precio > 0.00f){
            String precioString = String.format("$%.2f", precio);
            precioBaseTv.setText(precioString);
        }
    }

    @Override
    public void mostrarFechaDePublicacion(String date) {
        if(date != null){

            fechaTv.setText(date);
            fechaTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostrarNombreDeVendedor(String nombre) {
        if (nombre != null)
        {

            nombreTv.setText(nombre);
            nombreTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostrarDescripcion(String descripcion) {
        if (descripcion != null){

            descripcionTv.setText(descripcion);
            descripcionTv.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void mostrarCantidadDeOpiniones(int cantidad) {

        if(cantidad != 0 && cantidad >= 0){

            String cadena = String.format("(%d opiniones)", cantidad);
            cantidadDeOpinionesTv.setText(cadena);
            cantidadDeOpinionesTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostrarCantidadVendida(int cantidad) {
        if (cantidad != 0 && cantidad >= 0) {
            cantidadVendidaTv.setText(String.valueOf(cantidad).concat(" vendidos"));
            cantidadVendidaTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostrarAtributos(Map<String, String> atributos) {


        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        ItemAtributoAdapter adapter = new ItemAtributoAdapter(atributos);
        recyclerView.setAdapter(adapter);

        TextView atritv = findViewById(R.id.titulosAtributos_tv);
        atritv.setVisibility(View.VISIBLE);

        recyclerView.setVisibility(View.VISIBLE);

        View linea = findViewById(R.id.view3);
        linea.setVisibility(View.VISIBLE);
    }

    @Override
    public void mostrarCondicionDeLaPublicacion(String publicacion) {
        if (publicacion != null){
            condicionTv.setText(publicacion);
            condicionTv.setVisibility(View.VISIBLE);

            Condicion condicion = Condicion.valueOf(publicacion);
            switch (condicion){
                case NUEVO:
                    condicionTv.setBackground(getDrawable(R.drawable.fondo_cuadrado_nuevo_shape));
                    break;
                case USADO:
                    condicionTv.setBackground(getDrawable(R.drawable.fondo_cuadrado_usado_shape));
                    break;
                case NO_ESPECIFICADO:
                    condicionTv.setBackground(getDrawable(R.drawable.fondo_cuadrado_usado_shape));
            }

        }
    }

    @Override
    public void mostrarReputacion(float reputacion) {
        if (reputacion > 0) {
            reputacionTv.setText(String.valueOf(reputacion));
            reputacionTv.setVisibility(View.VISIBLE);
            ratingBar.setRating(reputacion);
            ratingBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostraDescuento(int descuento) {
        if (descuento > 0){

            descuentoTv.setText(String.format("%d%% de descuento", descuento));
            descuentoTv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("ID_TITULO", idPublicacion);

        outState.putString("TITULO", tituloTv.getText().toString());
        outState.putString("PRECIO_BASE", precioBaseTv.getText().toString());
        outState.putString("FECHA", fechaTv.getText().toString());
        outState.putString("NOMBRE", nombreTv.getText().toString());
        outState.putString("DESCRIPCION", descripcionTv.getText().toString());
        outState.putString("CANTIDAD_DE_OPINIONES", cantidadDeOpinionesTv.getText().toString());
        outState.putString("CANTIDAD_VENDIDA", cantidadVendidaTv.getText().toString());
        outState.putString("CONDICION", condicionTv.getText().toString());
        outState.putString("REPUTACION", reputacionTv.getText().toString());
        outState.putFloat("RATING", ratingBar.getRating());
        outState.putString("DECUENTO", descuentoTv.getText().toString());

        super.onSaveInstanceState(outState);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }

}
