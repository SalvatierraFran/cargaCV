package com.example.francosalvatierra.cargacv.Entities;

/**
 * Created by franco.salvatierra on 13/03/2018.
 */

public class Cursos {
    private Integer id;
    private String nombre;
    private String detalle;

    public Cursos(Integer Id, String Nombre, String Detalle)
    {
        this.id = Id;
        this.nombre = Nombre;
        this.detalle = Detalle;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
