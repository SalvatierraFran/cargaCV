package com.example.francosalvatierra.cargacv.DataModel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by franco.salvatierra on 09/03/2018.
 */

public class CVitaesDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CVitaesDB.db";

    public CVitaesDbHelper(Context context, String DATABASE_NAME, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CredencialContract.CredencialEntry.CREAR_TABLE_CREDENCIAL);
        db.execSQL(DatosContract.DatosEntry.CREAR_TABLE_DATOS);
        db.execSQL(ExperienciaContract.ExperienciaEntry.CREAR_TABLE_EXP);
        db.execSQL(ConocContract.ConocEntry.CREAR_TABLE_CONOC);
        db.execSQL(CursoContract.CursoEntry.CREAR_TABLE_CURSO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
