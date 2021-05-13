package Clases;

import ÇEnums.ParteCuerpoEPI;

import java.time.LocalDate;

public class Epi extends Producto {

    //Atributos
    private ParteCuerpoEPI parte;
    private String material;

    //Métodos

    public Epi(String codigoBarras, String nombre, LocalDate fechaCaducidad, double precio, ParteCuerpoEPI parte, String material) {
        super(codigoBarras, nombre, fechaCaducidad, precio);
        this.parte = parte;
        this.material = material;
    }

    public ParteCuerpoEPI getParte() {
        return parte;
    }

    public void setParte(ParteCuerpoEPI parte) {
        this.parte = parte;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Epi," + super.toString() +
                "," + parte +
                "," + material;
    }
}
