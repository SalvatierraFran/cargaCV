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
    Integer cont = 0;

    TextView nombre_tv;
    EditText nombre_et;
    TextView nombre2_tv;
    EditText nombre2_et;
    TextView detalle_tv;
    EditText detalle_et;
    TextView detalle2_tv;
    EditText detalle2_et;
    TextView nombre3_tv;
    EditText nombre3_et;
    TextView detalle3_tv;
    EditText detalle3_et;

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
        if(cont == 0) {
            LinearLayout nombreLL = new LinearLayout(getContext().getApplicationContext());
            nombreLL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);

            LinearLayout detalleLL = new LinearLayout(getContext().getApplicationContext());
            detalleLL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);

            nombre_tv = new TextView(getContext().getApplicationContext());
            nombre_tv.setText("Nombre: ");
            nombre_tv.setTextColor(Color.BLACK);

            nombre_et = new EditText(getContext().getApplicationContext());
            nombre_et.setBackgroundColor(Color.WHITE);
            nombre_et.setTextColor(Color.BLACK);
            nombre_et.setHeight(80);
            nombre_et.setWidth(600);

            nombreLL.addView(nombre_tv);
            nombreLL.addView(nombre_et);

            detalle_tv = new TextView(getContext().getApplicationContext());
            detalle_tv.setText("Detalle: ");
            detalle_tv.setTextColor(Color.BLACK);

            detalle_et = new EditText(getContext().getApplicationContext());
            detalle_et.setBackgroundColor(Color.WHITE);
            detalle_et.setTextColor(Color.BLACK);
            detalle_et.setHeight(80);
            detalle_et.setWidth(600);

            detalleLL.addView(detalle_tv);
            detalleLL.addView(detalle_et);

            LLmain.addView(nombreLL);
            LLmain.addView(detalleLL);

            cont++;
        }
        else if (cont == 1)
        {
            LinearLayout nombre2LL = new LinearLayout(getContext().getApplicationContext());
            nombre2LL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER );

            LinearLayout detalle2LL = new LinearLayout(getContext().getApplicationContext());
            detalle2LL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);

            nombre2_tv = new TextView(getContext().getApplicationContext());
            nombre2_tv.setTextColor(Color.BLACK);
            nombre2_tv.setText("Nombre: ");

            nombre2_et = new EditText(getContext().getApplicationContext());
            nombre2_et.setBackgroundColor(Color.WHITE);
            nombre2_et.setTextColor(Color.BLACK);
            nombre2_et.setHeight(80);
            nombre2_et.setWidth(600);

            nombre2LL.addView(nombre2_tv);
            nombre2LL.addView(nombre2_et);

            detalle2_tv = new TextView(getContext().getApplicationContext());
            detalle2_tv.setText("Detalle: ");
            detalle2_tv.setTextColor(Color.BLACK);

            detalle2_et = new EditText(getContext().getApplicationContext());
            detalle2_et.setBackgroundColor(Color.WHITE);
            detalle2_et.setTextColor(Color.BLACK);
            detalle2_et.setHeight(80);
            detalle2_et.setWidth(600);

            detalle2LL.addView(detalle2_tv);
            detalle2LL.addView(detalle2_et);

            LLmain.addView(nombre2LL);
            LLmain.addView(detalle2LL);

            cont++;

        }
        else if (cont == 2)
        {
            LinearLayout nombre3LL = new LinearLayout(getContext().getApplicationContext());
            nombre3LL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER );

            LinearLayout detalle3LL = new LinearLayout(getContext().getApplicationContext());
            detalle3LL.setVerticalGravity(View.TEXT_ALIGNMENT_CENTER);

            nombre3_tv = new TextView(getContext().getApplicationContext());
            nombre3_tv.setTextColor(Color.BLACK);
            nombre3_tv.setText("Nombre: ");

            nombre3_et = new EditText(getContext().getApplicationContext());
            nombre3_et.setBackgroundColor(Color.WHITE);
            nombre3_et.setTextColor(Color.BLACK);
            nombre3_et.setHeight(80);
            nombre3_et.setWidth(600);

            nombre3LL.addView(nombre3_tv);
            nombre3LL.addView(nombre3_et);

            detalle3_tv = new TextView(getContext().getApplicationContext());
            detalle3_tv.setText("Detalle: ");
            detalle3_tv.setTextColor(Color.BLACK);

            detalle3_et = new EditText(getContext().getApplicationContext());
            detalle3_et.setBackgroundColor(Color.WHITE);
            detalle3_et.setTextColor(Color.BLACK);
            detalle3_et.setHeight(80);
            detalle3_et.setWidth(600);

            detalle3LL.addView(detalle3_tv);
            detalle3LL.addView(detalle3_et);

            LLmain.addView(nombre3LL);
            LLmain.addView(detalle3LL);

            cont++;
        }
        else
        {
            Toast.makeText(this.getActivity().getApplicationContext(), "No se pueden agregar más cursos", Toast.LENGTH_SHORT).show();
        }
    }
}