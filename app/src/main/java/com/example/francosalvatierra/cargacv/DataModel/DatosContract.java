package com.example.francosalvatierra.cargacv.DataModel;

import android.provider.BaseColumns;

/**
 * Created by franco.salvatierra on 12/03/2018.
 */

public class DatosContract {
    public static abstract class DatosEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Datos";

        public static final String CAMPO_NOMBRE = "nombre";

        public static final String CAMPO_APELLIDO = "apellido";

        public static final String CAMPO_EDAD = "edad";

        public static final String CAMPO_NACION = "nacionalidad";

        public static final String CAMPO_PCIA = "provincia";

        public static final String CAMPO_DOMI = "domicilio";

        public static final String CAMPO_ECIVIL = "civil";

        public static final String CAMPO_IDCRED = "IdCred";

        public static final String CREAR_TABLE_DATOS = "CREATE TABLE " + TABLE_NAME + " (" + _ID + "  INTEGER PRIMARY " +
                "KEY AUTOINCREMENT, " + CAMPO_NOMBRE + " TEXT NOT NULL, " + CAMPO_APELLIDO + " TEXT NOT NULL, " + CAMPO_EDAD +
                " TEXT NOT NULL, " + CAMPO_NACION + " TEXT NOT NULL, " + CAMPO_PCIA + " TEXT NOT NULL, "
                + CAMPO_DOMI + " TEXT NOT NULL, " + CAMPO_ECIVIL + " TEXT NOT NULL, " + CAMPO_IDCRED + " TEXT NOT NULL)";
    }
}
