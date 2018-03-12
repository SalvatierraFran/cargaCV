package com.example.francosalvatierra.cargacv.Entities;

/**
 * Created by franco.salvatierra on 12/03/2018.
 */

public class DatosPersonales {
    private Integer id;
    private String nombre;
    private String apellido;
    private String edad;
    private String nacionalidad;
    private String provincia;
    private String domicilio;
    private String ecivil;

    public DatosPersonales(Integer Id, String Nombre, String Apellido, String Edad, String Nacionalidad, String Provincia, String Domicilio, String Ecivil)
    {
        this.id = Id;
        this.nombre = Nombre;
        this.apellido = Apellido;
        this.edad = Edad;
        this.nacionalidad = Nacionalidad;
        this.provincia = Provincia;
        this.domicilio = Domicilio;
        this.ecivil = Ecivil;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEcivil() {
        return ecivil;
    }

    public void setEcivil(String ecivil) {
        this.ecivil = ecivil;
    }
}
