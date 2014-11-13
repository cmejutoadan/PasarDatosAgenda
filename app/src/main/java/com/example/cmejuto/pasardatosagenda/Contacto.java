package com.example.cmejuto.pasardatosagenda;

import java.io.Serializable;

/**
 * Created by cmejuto on 07/11/2014.
 */
public class Contacto implements Serializable { //tenemos que implementar serializable para poder pasar el objeto de una activity a otra
    private String nombre;
    private String telefono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = this.nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;


    }
}
