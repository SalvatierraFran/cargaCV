package com.example.francosalvatierra.cargacv.DataModel;

import android.provider.BaseColumns;

/**
 * Created by franco.salvatierra on 12/03/2018.
 */

public class ExperienciaContract {
    public abstract class ExperienciaEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Experiencia";

        public static final String CAMPO_EMPRESA = "empresa";

        public static final String CAMPO_PUESTO = "puesto";

        public static final String CAMPO_DETALLE = "detalle";

        public static final String CAMPO_IDCRED = "idCred";

        public static final String CREAR_TABLE_EXP = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY " +
                "KEY AUTOINCREMENT, " + CAMPO_EMPRESA + " TEXT NOT NULL, " + CAMPO_PUESTO + " TEXT NOT NULL, " + CAMPO_DETALLE +
                " TEXT NOT NULL, " + CAMPO_IDCRED + " TEXT NOT NULL)";
    }
}
