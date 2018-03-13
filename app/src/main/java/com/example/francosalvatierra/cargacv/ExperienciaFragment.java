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
import com.example.francosalvatierra.cargacv.DataModel.DatosContract;
import com.example.francosalvatierra.cargacv.DataModel.ExperienciaContract;
import com.example.francosalvatierra.cargacv.Entities.Experiencia;


public class ExperienciaFragment extends Fragment {

    Experiencia auxExp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_experiencia, container, false);

        Bundle bundle = this.getActivity().getIntent().getExtras();

        final Integer idCred = ((Integer)bundle.get("Datos")).intValue();

        if(comprobar(idCred, v))
        {
            ((Button)v.findViewById(R.id.guardar_btnexp)).setVisibility(View.INVISIBLE);
        }

        ((Button)v.findViewById(R.id.guardar_btnexp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    guardarExp(idCred);
            }
        });

        return v;
    }

    public Boolean comprobar(Integer idCred, View v)
    {
        Boolean result = false;

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Experiencia WHERE IdCred = " + idCred, null);

        if(c.moveToFirst())
        {
            auxExp = new Experiencia(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
            result = true;
        }

        if(result)
        {
            EditText empresa_et = (EditText)v.findViewById(R.id.empresa_etexp);
            EditText puesto_et = (EditText)v.findViewById(R.id.puesto_etexp);
            EditText detalle_et = (EditText)v.findViewById(R.id.detalle_etexp);

            empresa_et.setText(auxExp.getEmpresa().toString());
            puesto_et.setText(auxExp.getPuesto().toString());
            detalle_et.setText(auxExp.getDetalle().toString());
        }

        return result;
    }

    public void guardarExp(Integer idCred)
    {
        EditText empresa_et = (EditText)getView().findViewById(R.id.empresa_etexp);
        EditText puesto_et = (EditText)getView().findViewById(R.id.puesto_etexp);
        EditText detalle_et = (EditText)getView().findViewById(R.id.detalle_etexp);

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ExperienciaContract.ExperienciaEntry.CAMPO_EMPRESA, empresa_et.getText().toString());
        values.put(ExperienciaContract.ExperienciaEntry.CAMPO_PUESTO, puesto_et.getText().toString());
        values.put(ExperienciaContract.ExperienciaEntry.CAMPO_DETALLE, detalle_et.getText().toString());
        values.put(ExperienciaContract.ExperienciaEntry.CAMPO_IDCRED, idCred);

        Long idResultante = db.insert(ExperienciaContract.ExperienciaEntry.TABLE_NAME, ExperienciaContract.ExperienciaEntry._ID, values);

        Toast.makeText(this.getActivity().getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
    }
}
