package com.gabo.app6_sqlite;
import java.io.Serializable;

public class Persona implements Serializable {
    private int id;
    private String nombres;
    private String apellidos;
    private String ci;

    public Persona(int id, String nombres, String apellidos, String ci) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
    }

    public Persona(String nombres, String apellidos, String ci) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci = ci;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCi() { return ci; }
    public void setCi(String ci) { this.ci = ci; }
}