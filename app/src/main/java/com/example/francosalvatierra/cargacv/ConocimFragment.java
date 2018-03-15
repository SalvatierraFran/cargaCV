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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.francosalvatierra.cargacv.DataModel.CVitaesDbHelper;
import com.example.francosalvatierra.cargacv.DataModel.ConocContract;
import com.example.francosalvatierra.cargacv.DataModel.DatosContract;
import com.example.francosalvatierra.cargacv.Entities.Conocimientos;

import java.util.ArrayList;

public class ConocimFragment extends Fragment {

    Spinner sp_niveles;
    String[] items;
    ArrayList<Conocimientos> listaConoc = new ArrayList<>();
    Conocimientos auxConoc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_conocim, container, false);

        Bundle bundle = this.getActivity().getIntent().getExtras();

        final Integer idCred = ((Integer)bundle.get("Datos")).intValue();

        sp_niveles = (Spinner)v.findViewById(R.id.spinner_conoc);

        items = getResources().getStringArray(R.array.lista_niveles);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.lista_niveles, R.layout.support_simple_spinner_dropdown_item);

        sp_niveles.setAdapter(adapter);

        sp_niveles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext().getApplicationContext(), items[position], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(comprobar(idCred, v))
        {
            ((Button)v.findViewById(R.id.guardar_btnconoc)).setVisibility(View.INVISIBLE);
        }

        ((Button)v.findViewById(R.id.guardar_btnconoc)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarConoc(idCred);
            }
        });

        return v;
    }

    public boolean comprobar(Integer idCred, View v)
    {
        Boolean result = false;

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Conocimientos WHERE IdCred = " + idCred, null);

        if(c.moveToFirst())
        {
            auxConoc = new Conocimientos(c.getInt(0), c.getString(1), c.getString(2));
            listaConoc.add(auxConoc);
            result = true;
        }while(c.moveToNext());

        if(result)
        {
            TextView nombre_et = (TextView)v.findViewById(R.id.nombre_etconoc);
            Spinner nivel_sp = (Spinner)v.findViewById(R.id.spinner_conoc);

            for(Integer i = 0; i < listaConoc.size(); i++)
            {
                nombre_et.setText(listaConoc.get(i).getNombre().toString());
            }
        }

        return result;
    }

    public void guardarConoc(Integer idCred)
    {
        TextView nombre_et = (TextView)getView().findViewById(R.id.nombre_etconoc);
        Spinner nivel_sp = (Spinner) getView().findViewById(R.id.spinner_conoc);

        CVitaesDbHelper conn = new CVitaesDbHelper(this.getContext(), "CVitaesDB", null, CVitaesDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(ConocContract.ConocEntry.CAMPO_NOMBRE, nombre_et.getText().toString());
        values.put(ConocContract.ConocEntry.CAMPO_NIVEL, nivel_sp.getSelectedItem().toString());
        values.put(ConocContract.ConocEntry.CAMPO_IDCRED, idCred);

        Long idResultante = db.insert(ConocContract.ConocEntry.TABLE_NAME, ConocContract.ConocEntry._ID, values);

        Toast.makeText(this.getActivity().getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
    }
}
