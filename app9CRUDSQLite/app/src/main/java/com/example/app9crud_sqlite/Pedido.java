package com.example.app9crud_sqlite;
import java.io.Serializable;
import java.time.LocalDateTime;
public class Pedido implements Serializable {
    private int idPedido;
    private String nombrePedido;
    private LocalDateTime fecha;
    private int idCliente;
    private String nombreCliente;
    private String apellidoCliente;

    public Pedido(int idPedido, String nombrePedido, LocalDateTime fecha, int idCliente, String nombreCliente, String apellidoCliente){
        this.idPedido = idPedido;
        this.nombrePedido = nombrePedido;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }

    public Pedido(String nombrePedido, LocalDateTime fecha, int idCliente){
        this.nombrePedido = nombrePedido;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombrePedido() {
        return nombrePedido;
    }

    public void setNombrePedido(String nombrePedido) {
        this.nombrePedido = nombrePedido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }
}
