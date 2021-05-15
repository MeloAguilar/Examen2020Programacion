package Clases;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * El string va a tener el siguiente orden
 * --
 * --
 * La clase producto será una clase padre de EPI y de Medicamento
 */
public abstract class Producto implements Comparable, Serializable {
    //Atributos
    private String codigoBarras;
    private String nombre;
    private LocalDate fechaCaducidad;
    private double precio;


    //Métodos


    public Producto(String codigoBarras, String nombre, LocalDate fechaCaducidad, double precio) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
        this.precio = precio;
    }

    public String getCodigoBarras() {
        return codigoBarras;
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


    @Override
    public int compareTo(Object o) {
        int comp;
        if(o == null)
            throw new NullPointerException();
        if(o instanceof Producto) {
            Producto p = (Producto) o;
            comp = this.getCodigoBarras().compareTo(p.getCodigoBarras());
    }else{
            throw new ClassCastException();
        }
        return comp;
    }

    @Override
    public boolean equals(Object o) {
        boolean eq = false;
        if (this == o) {
            eq = true;
        }
        if ((o instanceof Producto)) {
            Producto p = (Producto) o;
            eq = codigoBarras.equals(p.codigoBarras);
        }
        return eq;
    }


    @Override
    public int hashCode() {
        return Objects.hash(codigoBarras);
    }

    @Override
    public String toString() {
        return codigoBarras +
                "," + nombre +
                "," + fechaCaducidad +
                "," + precio;
    }
}
