package com.example.francosalvatierra.cargacv.Entities;

import com.example.francosalvatierra.cargacv.ConocimFragment;

/**
 * Created by franco.salvatierra on 13/03/2018.
 */

public class Conocimientos {
    private Integer id;
    private String nombre;
    private Integer nivel;

    public Conocimientos(Integer Id, String Nombre, Integer Nivel)
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
}
