package com.example.francosalvatierra.cargacv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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

        final LinearLayout LLMain = v.findViewById(R.id.contenedorCursos);

        FloatingActionButton fab = (FloatingActionButton)v.findViewById(R.id.agregar_cursos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearBloque(LLMain);
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
                EditText nombre_et = (EditText) v.findViewById(R.id.nombre_etcurso);
                EditText detalle_et = (EditText) v.findViewById(R.id.detalle_etcurso);

                nombre_et.setText(auxCurso.getNombre().toString());
                detalle_et.setText(auxCurso.getDetalle().toString());

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

    public void crearBloque(LinearLayout LLmain)
    {
        LinearLayout nombreLL = new LinearLayout(getContext().getApplicationContext());
        nombreLL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);

        LinearLayout detalleLL = new LinearLayout(getContext().getApplicationContext());
        detalleLL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);

        TextView nombre_tv = new TextView(getContext().getApplicationContext());
        nombre_tv.setText("Nombre: ");
        nombre_tv.setTextColor(Color.BLACK);

        EditText nombre_et = new EditText(getContext().getApplicationContext());
        nombre_et.setBackgroundColor(Color.WHITE);
        nombre_et.setTextColor(Color.BLACK);
        nombre_et.setHeight(80);
        nombre_et.setWidth(600);

        nombreLL.addView(nombre_tv);
        nombreLL.addView(nombre_et);

        TextView detalle_tv = new TextView(getContext().getApplicationContext());
        detalle_tv.setText("Detalle: ");
        detalle_tv.setTextColor(Color.BLACK);

        EditText detalle_et = new EditText(getContext().getApplicationContext());
        detalle_et.setBackgroundColor(Color.WHITE);
        detalle_et.setTextColor(Color.BLACK);
        detalle_et.setHeight(80);
        detalle_et.setWidth(600);

        detalleLL.addView(detalle_tv);
        detalleLL.addView(detalle_et);

        LLmain.addView(nombreLL);
        LLmain.addView(detalleLL);
    }
}
