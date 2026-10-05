package com.example.app10_testpilot;

import java.io.Serializable;

public class Nota implements Serializable {
    private int idNota;
    private String materia;
    private double valorNota; // DECIMAL(5,2) en SQL = double en Java
    private int alumnoId;     // El enlace manual que el usuario escribe en el formulario

    // Variables extra para la vitrina final (no van en la tabla "Nota" de la BD)
    private String nombreAlumnoCache;
    private String carreraAlumnoCache;

    // Constructor completo (Para leer de BD)
    public Nota(int idNota, String materia, double valorNota, int alumnoId) {
        this.idNota = idNota;
        this.materia = materia;
        this.valorNota = valorNota;
        this.alumnoId = alumnoId;
    }

    // Constructor sin ID (Para guardar desde el formulario)
    public Nota(String materia, double valorNota, int alumnoId) {
        this.materia = materia;
        this.valorNota = valorNota;
        this.alumnoId = alumnoId;
    }

    // Getters y Setters Básicos
    public int getIdNota() { return idNota; }
    public void setIdNota(int idNota) { this.idNota = idNota; }
    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }
    public double getValorNota() { return valorNota; }
    public void setValorNota(double valorNota) { this.valorNota = valorNota; }
    public int getAlumnoId() { return alumnoId; }
    public void setAlumnoId(int alumnoId) { this.alumnoId = alumnoId; }

    // Getters y Setters para la UI
    public String getNombreAlumnoCache() { return nombreAlumnoCache; }
    public void setNombreAlumnoCache(String nombreAlumnoCache) { this.nombreAlumnoCache = nombreAlumnoCache; }
    public String getCarreraAlumnoCache() { return carreraAlumnoCache; }
    public void setCarreraAlumnoCache(String carreraAlumnoCache) { this.carreraAlumnoCache = carreraAlumnoCache; }
}
