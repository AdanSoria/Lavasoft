/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

import java.net.URLEncoder;

/**
 *
 * @author pablo
 */
class Cliente {
    private int idCliente;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String fechaRegistro;
    private int whatsapp;

    public int getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(int whatsapp) {
        this.whatsapp = whatsapp;
    }

    // Constructor
    public Cliente(){
    
    }
    
    public Cliente(int idCliente, String nombre, String direccion, String telefono, String correo, String fechaRegistro,int whatsapp) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
        this.whatsapp = whatsapp;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
