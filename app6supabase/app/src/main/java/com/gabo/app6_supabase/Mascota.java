package com.gabo.app6_supabase;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Mascota implements Serializable {
    @SerializedName("id")
    private Long id;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("raza")
    private String raza;

    @SerializedName("edad")
    private int edad;

    @SerializedName("color")
    private String color;

    // Constructor para crear nueva mascota (sin ID)
    public Mascota(String nombre, String raza, int edad, String color) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
        this.color = color;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}