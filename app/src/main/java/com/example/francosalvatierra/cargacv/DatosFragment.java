package com.example.francosalvatierra.cargacv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.francosalvatierra.cargacv.DataModel.CVitaesDbHelper;
import com.example.francosalvatierra.cargacv.DataModel.CredencialContract;
import com.example.francosalvatierra.cargacv.DataModel.DatosContract;
import com.example.francosalvatierra.cargacv.Entities.DatosPersonales;

import java.util.ArrayList;


public class DatosFragment extends Fragment {

    DatosPersonales auxDatos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_datos, container, false);

        Bundle bundle = this.getActivity().getIntent().getExtras();

        final Integer idCred = ((Integer)bundle.get("Datos")).intValue();

        if(comprobar(idCred, v))
        {
            ((Button)v.findViewById(R.id.guardar_btndatos)).setVisibility(View.INVISIBLE);
        }

        ((Button)v.findViewById(R.id.guardar_btndatos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(idCred);
            }
        });

        return v;
    }

    public Boolean comprobar(Integer idCred, View v)
    {
        Boolean result = false;

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Datos WHERE IdCred = " + idCred, null);

        if(c.moveToFirst())
        {
            do{
                auxDatos = new DatosPersonales(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7));
                result = true;
            }while(c.moveToNext());
        }

        EditText nombre_et = (EditText)v.findViewById(R.id.nombre_etdatos);

        EditText apellido_et = (EditText)v.findViewById(R.id.apellido_etdatos);

        EditText edad_et = (EditText)v.findViewById(R.id.edad_etdatos);

        EditText nacio_et = (EditText)v.findViewById(R.id.nacio_etdatos);

        EditText pcia_et = (EditText)v.findViewById(R.id.pcia_etdatos);

        EditText domi_et = (EditText)v.findViewById(R.id.domi_etdatos);

        EditText ecivil_et = (EditText)v.findViewById(R.id.ecivil_etdatos);

        nombre_et.setText(auxDatos.getNombre().toString());
        apellido_et.setText(auxDatos.getApellido().toString());
        edad_et.setText(auxDatos.getEdad().toString());
        nacio_et.setText(auxDatos.getNacionalidad().toString());
        pcia_et.setText(auxDatos.getProvincia().toString());
        domi_et.setText(auxDatos.getDomicilio().toString());
        ecivil_et.setText(auxDatos.getEcivil().toString());

        return result;
    }

    public void guardar(Integer idCred)
    {
        EditText nombre_et = (EditText) getView().findViewById(R.id.nombre_etdatos);

        EditText apellido_et = (EditText)getView().findViewById(R.id.apellido_etdatos);

        EditText edad_et = (EditText)getView().findViewById(R.id.edad_etdatos);

        EditText nacio_et = (EditText)getView().findViewById(R.id.nacio_etdatos);

        EditText pcia_et = (EditText)getView().findViewById(R.id.pcia_etdatos);

        EditText domi_et = (EditText)getView().findViewById(R.id.domi_etdatos);

        EditText ecivil_et = (EditText)getView().findViewById(R.id.ecivil_etdatos);

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(DatosContract.DatosEntry.CAMPO_NOMBRE, nombre_et.getText().toString());
        values.put(DatosContract.DatosEntry.CAMPO_APELLIDO, apellido_et.getText().toString());
        values.put(DatosContract.DatosEntry.CAMPO_EDAD, edad_et.getText().toString());
        values.put(DatosContract.DatosEntry.CAMPO_NACION, nacio_et.getText().toString());
        values.put(DatosContract.DatosEntry.CAMPO_PCIA, pcia_et.getText().toString());
        values.put(DatosContract.DatosEntry.CAMPO_DOMI, domi_et.getText().toString());
        values.put(DatosContract.DatosEntry.CAMPO_ECIVIL, ecivil_et.getText().toString());
        values.put(DatosContract.DatosEntry.CAMPO_IDCRED, idCred);

        Long idResultante = db.insert(DatosContract.DatosEntry.TABLE_NAME, DatosContract.DatosEntry._ID, values);

        Toast.makeText(this.getActivity().getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
    }
}
