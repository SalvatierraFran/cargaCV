package com.example.francosalvatierra.cargacv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.francosalvatierra.cargacv.DataModel.CVitaesDbHelper;
import com.example.francosalvatierra.cargacv.DataModel.ConocContract;
import com.example.francosalvatierra.cargacv.DataModel.CursoContract;
import com.example.francosalvatierra.cargacv.Entities.Cursos;

import java.util.ArrayList;

public class CursosFragment extends Fragment {

    Cursos auxCurso;
    ArrayList<Cursos> listaCursos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cursos, container, false);

        Bundle bundle = this.getActivity().getIntent().getExtras();

        final Integer idCred = ((Integer)bundle.get("Datos")).intValue();

        if(comprobar(idCred, v))
        {
            ((Button)v.findViewById(R.id.guardar_btncursos)).setVisibility(View.INVISIBLE);
        }

        ((Button)v.findViewById(R.id.guardar_btncursos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCurso(idCred);
            }
        });

        return v;
    }

    public Boolean comprobar(Integer idCred, View v)
    {
        Boolean result = false;

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Cursos WHERE IdCred = " + idCred, null);

        if(c.moveToFirst())
        {
            auxCurso = new Cursos(c.getInt(0), c.getString(1), c.getString(2));
            listaCursos.add(auxCurso);
            result = true;
        }while(c.moveToNext());

        if(result)
        {
            for(Integer i =0 ; i < listaCursos.size(); i++) {
                /*EditText nombre_et = (EditText) v.findViewById(R.id.nombre_etcurso);
                EditText detalle_et = (EditText) v.findViewById(R.id.detalle_etcurso);

                nombre_et.setText(auxCurso.getNombre().toString());
                detalle_et.setText(auxCurso.getDetalle().toString());*/

                EditText et = new EditText(getContext().getApplicationContext());

            }
        }

        return result;
    }

    public void guardarCurso(Integer idCred)
    {
        EditText nombre_et = (EditText)getView().findViewById(R.id.nombre_etcurso);
        EditText detalle_et = (EditText)getView().findViewById(R.id.detalle_etcurso);

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(CursoContract.CursoEntry.CAMPO_NOMBRE, nombre_et.getText().toString());
        values.put(CursoContract.CursoEntry.CAMPO_DETALLE, detalle_et.getText().toString());
        values.put(CursoContract.CursoEntry.CAMPO_IDCRED, idCred);

        Long idResultante = db.insert(CursoContract.CursoEntry.TABLE_NAME, CursoContract.CursoEntry._ID, values);

        Toast.makeText(this.getActivity().getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
    }

    class linear{

        public Integer cod;
        public String nombre;
        public String detalle;

        public linear(Integer cod, String nombre, String detalle) {
            this.cod = cod;
            this.nombre = nombre;
            this.detalle = detalle;
        }
    }
}
