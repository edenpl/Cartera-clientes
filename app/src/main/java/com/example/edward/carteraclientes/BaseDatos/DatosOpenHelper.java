package com.example.edward.carteraclientes.BaseDatos;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatosOpenHelper extends SQLiteOpenHelper{

   public DatosOpenHelper(Context context){
       super(context,"DATOS", null,1);
   }

    public DatosOpenHelper(Context applicationContext, String datos, Object o, int i) {
        super(applicationContext,"DATOS", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       StringBuilder sql = new StringBuilder();
       sql.append("CREATE TABLE IF NOT EXISTS CLIENTE(");
       sql.append("NOMBRE VARCHAR(250), ");
       sql.append("DIRECCION VARCHAR(250), ");
       sql.append("EMAIL VARCHAR(200), ");
       sql.append("TELEFONO VARCHAR(20)) ");

       sqLiteDatabase.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public String[] buscar_reg(String buscar){
        String[] datos= new String[3];
        SQLiteDatabase database=this.getWritableDatabase();
        String q ="SELECT * FROM CLIENTE WHERE NOMBRE ='"+buscar+"'";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            for(int i=0;i<2;i++){
                datos[i]=registros.getString(i);
            }
            datos[2]="encontrado";
        }else{
            datos[2]="no se encontro a "+buscar;
        }
        return datos;
    }

    public String eliminar(String Nombre){
       String  mensaje="";
       SQLiteDatabase database = this.getWritableDatabase();
       int cantidad = database.delete("CLIENTE","NOMBRE='"+Nombre+"'",null);
       if (cantidad!=0){
           mensaje="USUARIO ELIMINADO";
       }else{
           mensaje="Este usuario no existe";
       }

       return mensaje;
    }
}
