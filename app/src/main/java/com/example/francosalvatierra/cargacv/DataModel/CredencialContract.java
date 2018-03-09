package com.example.francosalvatierra.cargacv.DataModel;

import android.provider.BaseColumns;

/**
 * Created by franco.salvatierra on 09/03/2018.
 */

public class CredencialContract {

    public static abstract class CredencialEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Credencial";

        public static final String CAMPO_IS = "name";

        public static final String CAMPO_PASS = "pass";

        public static final String CREAR_TABLE_CREDENCIAL = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY " +
                "KEY AUTOINCREMENT, " + CAMPO_IS + " TEXT NOT NULL, " + CAMPO_PASS + " TEXT NOT NULL)";
    }
}
