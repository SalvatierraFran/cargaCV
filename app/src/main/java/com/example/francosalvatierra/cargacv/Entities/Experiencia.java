package com.example.francosalvatierra.cargacv.Entities;

import java.util.IdentityHashMap;

/**
 * Created by franco.salvatierra on 12/03/2018.
 */

public class Experiencia {
    private Integer id;
    private String empresa;
    private String puesto;
    private String detalle;

    public Experiencia(Integer Id, String Empresa, String Puesto, String Detalle)
    {
        this.id = Id;
        this.empresa = Empresa;
        this.puesto = Puesto;
        this.detalle = Detalle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
