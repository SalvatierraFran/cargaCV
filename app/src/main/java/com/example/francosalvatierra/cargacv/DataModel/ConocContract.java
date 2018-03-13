package com.example.francosalvatierra.cargacv.DataModel;

import android.provider.BaseColumns;

/**
 * Created by franco.salvatierra on 13/03/2018.
 */

public class ConocContract{
    public static abstract class ConocEntry implements BaseColumns{

        public static final String TABLE_NAME = "Conocimientos";

        public static final String CAMPO_NOMBRE = "nombre";

        public static final String CAMPO_NIVEL = "nivel";

        public static final String CAMPO_IDCRED = "idCred";

        public static final String CREAR_TABLE_CONOC = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY " +
                "KEY AUTOINCREMENT, " + CAMPO_NOMBRE + " TEXT NOT NULL, " + CAMPO_NIVEL + " TEXT NOT NULL, " +
                CAMPO_IDCRED + " TEXT NOT NULL)";
    }
}
