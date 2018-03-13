package com.example.francosalvatierra.cargacv;

import android.content.ContentValues;
import android.content.Context;
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

public class CursosFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cursos, container, false);

        Bundle bundle = this.getActivity().getIntent().getExtras();

        final Integer idCred = ((Integer)bundle.get("Datos")).intValue();

        ((Button)v.findViewById(R.id.guardar_btncursos)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCurso(idCred);
            }
        });

        return v;
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
}
