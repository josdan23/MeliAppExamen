package com.josdan.meliappexamen.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.josdan.meliappexamen.R;

public class MainActivity extends AppCompatActivity {

    public static final String CONSULTA_KEY = "com.josdan.meliappexamen.consultakey";

    private EditText buscarEditText;
    private Button buscarBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurarToolbar();

        buscarBoton = findViewById(R.id.buscar_bt);

        buscarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscarEditText = findViewById(R.id.buscar_et);

                if (!buscarEditText.getText().toString().equals(""))
                {
                   Intent intent  = new Intent(getApplicationContext(), PublicacionesActivity.class);
                   intent.putExtra(CONSULTA_KEY, buscarEditText.getText().toString());
                   startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this, "Escribe tu busqueda y oprime BUSCAR", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void configurarToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
