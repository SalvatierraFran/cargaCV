package com.example.francosalvatierra.cargacv.DataModel;

import android.provider.BaseColumns;

/**
 * Created by franco.salvatierra on 13/03/2018.
 */

public class CursoContract {
    public static abstract class CursoEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Cursos";

        public static final String CAMPO_NOMBRE = "nombre";

        public static final String CAMPO_DETALLE = "detalle";

        public static final String CAMPO_IDCRED = "idCred";

        public static final String CREAR_TABLE_CURSO = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY" +
                " KEY AUTOINCREMENT, " + CAMPO_NOMBRE + " TEXT NOT NULL, " + CAMPO_DETALLE + " TEXT NOT NULL, " +
                CAMPO_IDCRED + " TEXT NOT NULL)";
    }
}
