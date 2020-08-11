package com.example.edward.carteraclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.edward.carteraclientes.BaseDatos.DatosOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity implements View.OnClickListener {
    Button btnBuscar;
    private ListView lsDatos;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> clientes;

    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnBuscar=(Button)findViewById(R.id.btnBuscar1);
        btnBuscar.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ActMain.this,ActNuevoCliente.class);
                //startActivity(it);
                startActivityForResult(it, 0);
            }
        });

        btnBuscar.setOnClickListener(new  View.OnClickListener(){
            public void onClick(View v){
                Intent i2 = new Intent(ActMain.this, MenuOp.class);
                startActivity(i2);
            }
        });

        actualizar();
    }
    private void actualizar(){
        lsDatos=(ListView) findViewById(R.id.lstDatos);
        clientes=new ArrayList<String>();

        try {
            datosOpenHelper = new DatosOpenHelper(this);
            conexion = datosOpenHelper.getWritableDatabase();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM CLIENTE");
            String sNombre;
            String sTelefono;

            Cursor resultado = conexion.rawQuery(sql.toString(),null);

            if(resultado.getCount() > 0){
                resultado.moveToFirst();
                do{
                    sNombre = resultado.getString(resultado.getColumnIndex("NOMBRE"));
                    sTelefono = resultado.getString(resultado.getColumnIndex("TELEFONO"));
                    clientes.add(sNombre +":"+ sTelefono);
                }
                while (resultado.moveToNext());
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
            lsDatos.setAdapter(adaptador);
        }
        catch (Exception ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        actualizar();
    }

    @Override
    public void onClick(View v) {
      //  switch (v.getId()){
        //    case R.id.btnBuscar:

        //}

    }
}