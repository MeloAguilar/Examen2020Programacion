package Clases;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * El string va a tener el siguiente orden
 * --
 * --
 * La clase producto será una clase padre de EPI y de Medicamento
 */
public abstract class Producto <Comparable>{
    //Atributos
    private String CodigoBarras;
    private String nombre;
    private LocalDate fechaCaducidad;
    private double precio;


    //Métodos


    public Producto(String codigoBarras, String nombre, LocalDate fechaCaducidad, double precio) {
        CodigoBarras = codigoBarras;
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
        this.precio = precio;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }



    public String getNombre() {
        return nombre;
    }


    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }


    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


 public int compareTo(Producto p2){
        return this.getCodigoBarras().compareTo(p2.getCodigoBarras());
 }


    public boolean equals(Producto o) {
        return Objects.equals(CodigoBarras, o.CodigoBarras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CodigoBarras);
    }

    @Override
    public String toString() {
        return CodigoBarras +
                "," + nombre +
                "," + fechaCaducidad +
                "," + precio;
    }
}
