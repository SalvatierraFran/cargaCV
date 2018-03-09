package com.example.francosalvatierra.cargacv.Entities;

/**
 * Created by franco.salvatierra on 09/03/2018.
 */

public class Credencial {

    private Integer id;
    private String is;
    private String pass;

    public Credencial(Integer Id, String Is, String Pass)
    {
        this.id = Id;
        this.is = Is;
        this.pass = Pass;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
