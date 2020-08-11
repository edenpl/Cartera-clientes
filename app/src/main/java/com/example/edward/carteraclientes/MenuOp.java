package com.example.edward.carteraclientes;

import android.content.Intent;
import android.os.Bundle;

import com.example.edward.carteraclientes.BaseDatos.DatosOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuOp extends AppCompatActivity implements View.OnClickListener {
    Button Buscar, Eliminar;
    EditText Bnombre;
    TextView BuscarNombre, BuscarDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuop);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bnombre=(EditText)findViewById(R.id.Bnombre);
        BuscarNombre =(TextView)findViewById(R.id.Busnombre);
        BuscarDireccion =(TextView)findViewById(R.id.Busdireccion);
        Buscar =(Button)findViewById(R.id.btnBuscar2);
        Eliminar =(Button)findViewById(R.id.btnEliminar);
        Buscar.setOnClickListener(this);
        Eliminar.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Buscar.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                DatosOpenHelper db = new DatosOpenHelper(getApplicationContext(),null,null,1);
                String  buscar = Bnombre.getText().toString();
                String[] datos;
                datos=db.buscar_reg(buscar);
                BuscarNombre.setText(datos[0]);
                BuscarDireccion.setText(datos[1]);
                Toast.makeText(getApplicationContext(),datos[2],Toast.LENGTH_SHORT).show();
            }
        });

        Eliminar.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                DatosOpenHelper db = new DatosOpenHelper(getApplicationContext(),null,null,1);
               String Nombre = BuscarNombre.getText().toString();
               String mensaje = db.eliminar(Nombre);
                Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
            Intent onClick = new Intent(this,ActMain.class);
            startActivity(onClick);

    }



}