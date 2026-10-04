package com.example.app7crud_sqlite;
import java.io.Serializable;
public class Persona implements Serializable{
    private int idPersona;
    private String nombres;
    private String apellidos;
    private String ci;

    public Persona(int idPersona, String nombres, String apellidos, String ci) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ci=ci;
    }

    public Persona (String nombres, String apellidos, String ci){
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.ci=ci;
    }

    public int getId(){
        return idPersona;
    }

    public String getNombres(){
        return nombres;
    }

    public String getApellidos(){
        return apellidos;
    }
    public String getCi(){
        return ci;
    }
}
