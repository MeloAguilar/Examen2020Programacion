package Clases;

import GestionFicheros.FileAcces;
import ÇEnums.ParteCuerpoEPI;
import ÇEnums.Presentacion;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<String> cosa = (LinkedList<String>) FileAcces.txtReader(FileAcces.archivoProductos);

        LinkedList<Producto> productos = new LinkedList<Producto>();
        for(int i = 0; i < cosa.size(); i++){
            productos.add(FileAcces.montarProducto(cosa.get(i)));
            System.out.println(productos.get(i));
        }
        FileAcces.eliminarProducto("WTFDSRIURJWEF23DF");
    }







    private static void introducirDatosIniciales() {
        LocalDate hoy = LocalDate.parse("2023-04-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Medicamento p = new Medicamento("QWET12354D", "AeroRed", hoy, 3.45, Presentacion.C, "Gaseador");
        Medicamento o = new Medicamento("QWET1UIO54D", "AeroRed", hoy, 4.45, Presentacion.C, "Gaseador");
        Epi e = new Epi("WTFDSRWEF23DF", "Mascarilla", LocalDate.now(), 2.30, ParteCuerpoEPI.C, "Caucho");
        Medicamento l = new Medicamento("PEROTY54BSI", "Paracetamol", hoy, 4.45, Presentacion.G, "Gaseador");
        Epi ll = new Epi("WTFDSRWERWQEF23DF", "Tapaojos", LocalDate.now(), 2.30, ParteCuerpoEPI.O, "Caucho");
        Medicamento pp = new Medicamento("QWET1UIO54D", "AeroRed", hoy, 4.45, Presentacion.C, "Gaseador");
        Epi jj = new Epi("WTFDSRIURJWEF23DF", "Mascarilla fachera", LocalDate.now(), 2.30, ParteCuerpoEPI.O, "Caucho");
        FileAcces tt = new FileAcces();
        tt.introducirProducto(p);
        tt.introducirProducto(e);
        tt.introducirProducto(o);
        tt.introducirProducto(pp);
        tt.introducirProducto(jj);
        tt.introducirProducto(ll);
        tt.introducirProducto(l);
        LinkedList<String> cosa = (LinkedList<String>) FileAcces.txtReader(FileAcces.archivoProductos);

        LinkedList<Producto> productos = new LinkedList<Producto>();
        for(int i = 0; i < cosa.size(); i++){
            productos.add(FileAcces.montarProducto(cosa.get(i)));
            System.out.println(productos.get(i));
        }
        for (Producto prod : productos) {
            System.out.println(prod);
        }
    }



}
