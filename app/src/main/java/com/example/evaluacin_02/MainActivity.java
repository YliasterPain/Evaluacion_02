package com.example.evaluacin_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre, et_precio;
    ListView lv;
    ArrayList<String> lista;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //relacion parte logica y grafica
        et_nombre = (EditText) findViewById(R.id.nombre);
        et_precio = (EditText) findViewById(R.id.precio);

        //relacion listview
        lv=(ListView) findViewById(R.id.lista);

        SQLITE db  = new SQLITE(getApplicationContext(),null,null,1);
        lista = db.llenar_lv();
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lista);
        lv.setAdapter(adapter);

    }

    //Metodo para crear los productos
    public void crear(View view) {
        SQLITE admin = new SQLITE(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String precio = et_precio.getText().toString();

        //validar campo
        if (!nombre.isEmpty() && !precio.isEmpty()) {
            ContentValues registro = new ContentValues();
            registro.put(nombre, nombre);
            registro.put(precio, precio);

            BaseDeDatos.insert("articulos", null, registro);
            // cerramos la base de datos y limpiamos campos de texto
            BaseDeDatos.close();
            et_nombre.setText("");
            et_precio.setText("");
            Toast.makeText(this, "registro completo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "debe llenar todos las casillas ", Toast.LENGTH_SHORT).show();
        }
    }

}