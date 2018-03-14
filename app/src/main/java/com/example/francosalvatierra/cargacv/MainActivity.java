package com.example.francosalvatierra.cargacv;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.francosalvatierra.cargacv.DataModel.CVitaesDbHelper;
import com.example.francosalvatierra.cargacv.DataModel.CredencialContract;
import com.example.francosalvatierra.cargacv.Entities.Credencial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    String is;
    String pass;
    Integer auxId;

    ArrayList<Credencial> listaCred = new ArrayList<Credencial>();
    Credencial auxCred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.Login_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        ((Button)findViewById(R.id.Registro_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro();
            }
        });
    }

    public void login()
    {
        Boolean logged = false;
        EditText is_et = (EditText)findViewById(R.id.is_et);
        EditText pass_et = (EditText)findViewById(R.id.pass_et);

        is = is_et.getText().toString();
        pass = pass_et.getText().toString();

        if(!(is.equals("")) && !(pass.equals(""))) {

            CVitaesDbHelper conn = new CVitaesDbHelper(this, "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

            SQLiteDatabase db = conn.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM Credencial", null);

            if (c.moveToFirst()) {
                do {
                    auxCred = new Credencial(c.getInt(0), c.getString(1), c.getString(2));
                    listaCred.add(auxCred);
                } while (c.moveToNext());
            }

            for (int i = 0; i < listaCred.size(); i++) {
                if (is.equals(listaCred.get(i).getIs()) && pass.equals(listaCred.get(i).getPass())) {
                    logged = true;
                    auxId = i;
                    break;
                }
            }

            if(logged == false) {
                Toast.makeText(this.getApplicationContext(), "Usuario no encontrado.", Toast.LENGTH_SHORT).show();
                is_et.setText("");
                pass_et.setText("");
            }
            else {
                Toast.makeText(this.getApplicationContext(), "BIENVENIDO " + is, Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, Main2Activity.class);
                myIntent.putExtra("Datos", listaCred.get(auxId).getId());
                startActivity(myIntent);
                is_et.setText("");
                pass_et.setText("");
            }
        }else{
            Toast.makeText(this.getApplicationContext(), "Ningún campo debe estar incompleto.", Toast.LENGTH_LONG).show();
        }
    }

    public void registro()
    {
        EditText is_et = (EditText)findViewById(R.id.is_et);
        EditText pass_et = (EditText)findViewById(R.id.pass_et);

        is = is_et.getText().toString();
        pass = pass_et.getText().toString();

        if(!(is.equals("")) && !(pass.equals(""))) {

            CVitaesDbHelper conn = new CVitaesDbHelper(this, "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

            SQLiteDatabase db = conn.getWritableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM Credencial WHERE name = '" + is + "'", null);

            if(c.moveToFirst()) {
                Toast.makeText(this.getApplicationContext(), "El usuario ya existe", Toast.LENGTH_LONG).show();
            }
            else{
                ContentValues values = new ContentValues();

                values.put(CredencialContract.CredencialEntry.CAMPO_IS, is);
                values.put(CredencialContract.CredencialEntry.CAMPO_PASS, pass);

                Long idResultante = db.insert(CredencialContract.CredencialEntry.TABLE_NAME, CredencialContract.CredencialEntry._ID, values);

                Toast.makeText(this.getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this.getApplicationContext(), "Ningún campo debe estar incompleto.", Toast.LENGTH_SHORT).show();
        }
    }
}
