package com.example.francosalvatierra.cargacv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.VectorEnabledTintResources;
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
    String queHacer = "guardar";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_datos, container, false);

        Bundle bundle = this.getActivity().getIntent().getExtras();

        final Integer idCred = ((Integer)bundle.get("Datos")).intValue();

        ((Button)v.findViewById(R.id.modificar_btndatos)).setVisibility(View.INVISIBLE);

        if(comprobar(idCred, v))
        {
            ((Button)v.findViewById(R.id.guardar_btndatos)).setVisibility(View.INVISIBLE);
            ((Button)v.findViewById(R.id.modificar_btndatos)).setVisibility(View.VISIBLE);
        }

        ((Button)v.findViewById(R.id.guardar_btndatos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(idCred);
            }
        });

        ((Button)v.findViewById(R.id.modificar_btndatos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificar(idCred, v);
            }
        });

        return v;
    }

    public void modificar(Integer idCred, View v)
    {
        queHacer = "modificar";

        EditText nombre_et = (EditText)getView().findViewById(R.id.nombre_etdatos);
        nombre_et.setEnabled(true);

        EditText apellido_et = (EditText)getView().findViewById(R.id.apellido_etdatos);
        apellido_et.setEnabled(true);

        EditText edad_et = (EditText)getView().findViewById(R.id.edad_etdatos);
        edad_et.setEnabled(true);

        EditText nacio_et = (EditText)getView().findViewById(R.id.nacio_etdatos);
        nacio_et.setEnabled(true);

        EditText pcia_et = (EditText)getView().findViewById(R.id.pcia_etdatos);
        pcia_et.setEnabled(true);

        EditText domi_et = (EditText)getView().findViewById(R.id.domi_etdatos);
        domi_et.setEnabled(true);

        EditText ecivil_et = (EditText)getView().findViewById(R.id.ecivil_etdatos);
        ecivil_et.setEnabled(true);

        ((Button)getView().findViewById(R.id.guardar_btndatos)).setVisibility(View.VISIBLE);
        ((Button)getView().findViewById(R.id.modificar_btndatos)).setVisibility(View.INVISIBLE);
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

        if(result) {

            bloquear(v);
        }
        return result;
    }

    public void bloquear(View v)
    {
        EditText nombre_et = (EditText) v.findViewById(R.id.nombre_etdatos);

        EditText apellido_et = (EditText) v.findViewById(R.id.apellido_etdatos);

        EditText edad_et = (EditText) v.findViewById(R.id.edad_etdatos);

        EditText nacio_et = (EditText) v.findViewById(R.id.nacio_etdatos);

        EditText pcia_et = (EditText) v.findViewById(R.id.pcia_etdatos);

        EditText domi_et = (EditText) v.findViewById(R.id.domi_etdatos);

        EditText ecivil_et = (EditText) v.findViewById(R.id.ecivil_etdatos);

        nombre_et.setText(auxDatos.getNombre().toString());
        nombre_et.setEnabled(false);

        apellido_et.setText(auxDatos.getApellido().toString());
        apellido_et.setEnabled(false);

        edad_et.setText(auxDatos.getEdad().toString());
        edad_et.setEnabled(false);

        nacio_et.setText(auxDatos.getNacionalidad().toString());
        nacio_et.setEnabled(false);

        pcia_et.setText(auxDatos.getProvincia().toString());
        pcia_et.setEnabled(false);

        domi_et.setText(auxDatos.getDomicilio().toString());
        domi_et.setEnabled(false);

        ecivil_et.setText(auxDatos.getEcivil().toString());
        ecivil_et.setEnabled(false);
    }

    public void guardar(Integer idCred)
    {
        EditText nombre_et = (EditText) getView().findViewById(R.id.nombre_etdatos);

        EditText apellido_et = (EditText) getView().findViewById(R.id.apellido_etdatos);

        EditText edad_et = (EditText) getView().findViewById(R.id.edad_etdatos);

        EditText nacio_et = (EditText) getView().findViewById(R.id.nacio_etdatos);

        EditText pcia_et = (EditText) getView().findViewById(R.id.pcia_etdatos);

        EditText domi_et = (EditText) getView().findViewById(R.id.domi_etdatos);

        EditText ecivil_et = (EditText) getView().findViewById(R.id.ecivil_etdatos);

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        ContentValues values = new ContentValues();

        if(queHacer.equals("guardar"))
        {
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

            ((Button)getView().findViewById(R.id.guardar_btndatos)).setVisibility(View.INVISIBLE);
            ((Button)getView().findViewById(R.id.modificar_btndatos)).setVisibility(View.VISIBLE);
        }
        if(queHacer.equals("modificar"))
        {
            values.put(DatosContract.DatosEntry.CAMPO_NOMBRE, nombre_et.getText().toString());
            values.put(DatosContract.DatosEntry.CAMPO_APELLIDO, apellido_et.getText().toString());
            values.put(DatosContract.DatosEntry.CAMPO_EDAD, edad_et.getText().toString());
            values.put(DatosContract.DatosEntry.CAMPO_NACION, nacio_et.getText().toString());
            values.put(DatosContract.DatosEntry.CAMPO_PCIA, pcia_et.getText().toString());
            values.put(DatosContract.DatosEntry.CAMPO_DOMI, domi_et.getText().toString());
            values.put(DatosContract.DatosEntry.CAMPO_ECIVIL, ecivil_et.getText().toString());
            values.put(DatosContract.DatosEntry.CAMPO_IDCRED, idCred);

            db.update("Datos", values, "IdCred = " + idCred, null);

            Toast.makeText(this.getActivity().getApplicationContext(), "Actualizado correctamente.", Toast.LENGTH_SHORT).show();

            ((Button)getView().findViewById(R.id.guardar_btndatos)).setVisibility(View.INVISIBLE);
            ((Button)getView().findViewById(R.id.modificar_btndatos)).setVisibility(View.VISIBLE);
        }
    }
}
