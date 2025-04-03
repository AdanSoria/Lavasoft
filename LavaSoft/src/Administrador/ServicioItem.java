/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Administrador;

/**
 *
 * @author soria
 */
public class ServicioItem {
    private int id;
    private String descripcion;
    private double precioBase;
    private double precioPorKilo;

    public ServicioItem(int id, String descripcion, double precioBase, double precioPorKilo) {
        this.id = id;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.precioPorKilo = precioPorKilo;
    }

    public int getId() { return id; }
    public String getDescripcion() { return descripcion; }
    public double getPrecioBase() { return precioBase; }
    public double getPrecioPorKilo() { return precioPorKilo; }
    
   @Override
public String toString() {
    return descripcion + " ($" + String.format("%.2f", precioBase) + " + $" + 
           String.format("%.2f", precioPorKilo) + "/kg)";
}

}
