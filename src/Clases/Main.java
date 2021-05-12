package Clases;

import GestionFicheros.FileAcces;
import ÇEnums.Presentacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        LocalDate hoy = LocalDate.parse("2023-04-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	Medicamento p = new Medicamento("QWET12354D", "AeroRed", hoy, 3.45, Presentacion.C, "Gaseador");
	Epi e = new Epi("WTFDSRWEF23DF");
        FileAcces tt = new FileAcces();
        tt.introducirProducto(p);
        tt.introducirProductosEnFichero();
        String coso = FileAcces.txtReader(FileAcces.archivoProductos);
       Producto producto = FileAcces.montarProducto(FileAcces.devolverArrayDeObjetos (coso));
        System.out.println(producto);
    }
}
