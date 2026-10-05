package com.example.app8crudsqlite;


import java.io.Serializable;

public class Rutina implements Serializable {
    private int id;
    private String nombreRutina;

    public Rutina (int id, String nombreRutina){
        this.id = id;
        this.nombreRutina = nombreRutina;
    }

    public Rutina(String nombreRutina){
        this.nombreRutina = nombreRutina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }
}
