package Clases;

import ÇEnums.Presentacion;

import java.time.LocalDate;

public class Medicamento extends Producto{

    private Presentacion presentacion;
    private String principioActivo;


    public Medicamento(String codigoBarras, String nombre, LocalDate fechaCaducidad, double precio,Presentacion presentacion, String principioActivo) {
        super(codigoBarras, nombre, fechaCaducidad, precio);
        this.presentacion = presentacion;
        this.principioActivo = principioActivo;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "," + presentacion +
                "," + principioActivo+":!" ;
    }
}
