package com.example.evaluacin_02;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// Esta clase admistra la base de datos
public class SQLITE extends SQLiteOpenHelper {

    // constructor
    public SQLITE(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "prueba", factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
    BaseDeDatos.execSQL("create table articulos(nombre text primary key, precio real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList llenar_lv(){
        ArrayList<String> lista =new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        String  q = "SELECT * FROM articulos";
        Cursor registros =database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(0));
            }while (registros.moveToNext());
        }
        return lista;
    }
}
