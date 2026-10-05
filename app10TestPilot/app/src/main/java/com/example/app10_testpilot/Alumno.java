package com.example.app10_testpilot;

import java.io.Serializable;

public class Alumno implements Serializable {
    private int idAlumno;
    private String nombre;
    private String carrera;

    // Constructor completo (Para leer de BD)
    public Alumno(int idAlumno, String nombre, String carrera) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    // Constructor sin ID (Para guardar desde el formulario)
    public Alumno(String nombre, String carrera) {
        this.nombre = nombre;
        this.carrera = carrera;
    }

    // Getters y Setters
    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }
}