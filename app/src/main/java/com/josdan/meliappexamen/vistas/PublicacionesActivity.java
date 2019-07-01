package com.josdan.meliappexamen.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.josdan.meliappexamen.R;
import com.josdan.meliappexamen.controladores.BuscarProductoController;
import com.josdan.meliappexamen.controladores.IBuscarProductoView;
import com.josdan.meliappexamen.dominio.Publicacion;
import com.josdan.meliappexamen.servicios.ApiMercadoLibre;

import java.util.List;

public class PublicacionesActivity extends AppCompatActivity implements IBuscarProductoView {

    public static final String ID_ITEM_KEY = "com.josdan.meliappexamen.id_item";

    //COMPONENTES UI
    private ProgressBar progressBar, progressBar2;
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private GridLayoutManager layoutManager;

    //CONTROLADOR
    private BuscarProductoController controller;


    private Boolean estaDesplazando = false;
    private int itemsActuales, totalItems, itemsFueraDelScroll;
    private String consulta;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicaciones);

        configurarToolbar();
        configurarRecyclerView();

        controller = new BuscarProductoController(this);
        controller.setServicioExterno(new ApiMercadoLibre(this));

        if (savedInstanceState != null) {
            consulta = savedInstanceState.getString("CONSULTA_ESTADO");
            Log.d("Saveinstance", "!null");

        } else {
            consulta = getIntent().getStringExtra(MainActivity.CONSULTA_KEY);
            Log.d("SaveInstance", "null");
        }

        controller.buscarProducto(consulta);

        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
    }

    private void configurarToolbar(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Resultados");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void configurarRecyclerView(){
        recyclerView = findViewById(R.id.publicaciones_recycler_view);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ItemAdapter(this);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPublicacion = adapter.getId(layoutManager.getPosition(v));
                Log.d("ITEM", idPublicacion);

                Intent intent = new Intent(getApplicationContext(), DetalleActivity.class);
                intent.putExtra(ID_ITEM_KEY, idPublicacion);
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    estaDesplazando = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                itemsActuales = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                itemsFueraDelScroll = layoutManager.findFirstVisibleItemPosition();

                if(estaDesplazando && (itemsActuales + itemsFueraDelScroll == totalItems)){

                    progressBar.setVisibility(View.VISIBLE);
                    controller.buscarProducto(consulta);
                    Log.d("ON_SCROLLED", "Se realizo scroll");
                    estaDesplazando = false;
                }
            }
        });
    }

    @Override
    public void mostrarResultadosDeBusqueda(List<Publicacion> publicaciones) {

        progressBar2.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        if (publicaciones != null && publicaciones.size()!=0){

            adapter.setPublicaciones(publicaciones);
            adapter.notifyDataSetChanged();

        }
        else
        {
            Toast.makeText(this, "No hubo resultados", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("CONSULTA_ESTADO", consulta);

        Log.d("ONSAVEINSTANCE", "guardado");
    }

    @Override
    public void mostrarError(String mensaje) {
        Log.d("ERROR", mensaje);

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        finish();
    }

}
