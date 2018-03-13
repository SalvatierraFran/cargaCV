package com.example.francosalvatierra.cargacv.Entities;

import com.example.francosalvatierra.cargacv.ConocimFragment;

/**
 * Created by franco.salvatierra on 13/03/2018.
 */

public class Conocimientos {
    private Integer id;
    private String nombre;
    private String nivel;

    public Conocimientos(Integer Id, String Nombre, String Nivel)
    {
        this.id = Id;
        this.nombre = Nombre;
        this.nivel = Nivel;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
