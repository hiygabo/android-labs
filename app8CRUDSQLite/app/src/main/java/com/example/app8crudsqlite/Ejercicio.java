package com.example.app8crudsqlite;

import java.io.Serializable;

public class Ejercicio implements Serializable {
    private int id;
    private String nombreEjercicio;
    private int series;

    private int rutinaId;
    private String nombreRutina;

    public Ejercicio (int id, String nombreEjercicio, int series, int rutinaId, String nombreRutina){
        this.id = id;
        this.nombreEjercicio = nombreEjercicio;
        this.series = series;
        this.rutinaId = rutinaId;
        this.nombreRutina = nombreRutina;
    }

    public Ejercicio(String nombreEjercicio, int series, int rutinaId){
        this.nombreEjercicio = nombreEjercicio;
        this.series = series;
        this.rutinaId = rutinaId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRutinaId() {
        return rutinaId;
    }

    public void setRutinaId(int rutinaId) {
        this.rutinaId = rutinaId;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }
}
