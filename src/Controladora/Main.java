package Controladora;

import Clases.Epi;
import Clases.Medicamento;
import Clases.Producto;
import GestionFicheros.FileAcces;
import Menu.Menu;
import ÇEnums.ParteCuerpoEPI;
import ÇEnums.Presentacion;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner (System.in);

    public static void main(String[] args) {
        boolean exit = false;
        boolean exit2 = false;
        FileAcces tt = new FileAcces ( );
        introducirDatosIniciales (tt);
        do {
            System.out.println (Menu.MENSAJEINICIAL);
            switch (sc.nextLine ( )) {
                //Menú de Impresión.
                case "1" -> {
                    do {
                        switch (sc.nextLine ( )) {
                            //listado de Productos con el mismo nombre
                            case "1" -> {
                                String nombre = Menu.pedirDato (sc, "el nombre del producto.");
                                mostrarProductosPorNombre (tt, nombre);
                                exit2 = true;
                            }
                            //Listado de productos por fecha de caducidad
                            case "2" -> {
                                LocalDate fecha = LocalDate.parse (Menu.pedirDato (sc, "la fecha de caducidad con formato yyyy-MM-dd"));

                            }
                            //
                            case "3" -> {

                            }
                            //Mensaje de error
                            default -> {
                                System.out.println (Menu.MENSAJEERROR);
                            }
                        }
                    } while (!exit2);
                }
                //Menú de Cálculos.
                case "2" -> {

                }
                //Menú de Gestión
                case "3" -> {

                }
                //Dato mal introducido
                default -> {
                    System.out.println (Menu.MENSAJEERROR);
                }

            }

        } while (!exit);
    }


    private static void introducirDatosIniciales(FileAcces tt) {
        LocalDate hoy = LocalDate.parse ("2023-04-23", DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
        Medicamento p = new Medicamento ("QWET12354D",
                "AeroRed", hoy, 3.45, Presentacion.C, "Gaseador");
        Medicamento o = new Medicamento ("QWET1UIO54D",
                "AeroRed", hoy, 4.45, Presentacion.C, "Gaseador");
        Epi e = new Epi ("WTFDSRWEF23DF", "Mascarilla",
                LocalDate.now ( ), 2.30, ParteCuerpoEPI.C, "Caucho");
        Medicamento l = new Medicamento ("PEROTY54BSI",
                "Paracetamol", hoy, 4.45, Presentacion.G, "Gaseador");
        Epi ll = new Epi ("WTFDSRWERWQEF23DF",
                "Tapaojos", LocalDate.now ( ), 2.30, ParteCuerpoEPI.O, "Caucho");
        Medicamento pp = new Medicamento ("QWET1UIO54D",
                "AeroRed", hoy, 4.45, Presentacion.C, "Gaseador");
        Epi jj = new Epi ("WTFDSRIURJWEF23DF",
                "Mascarilla fachera", LocalDate.now ( ), 2.30, ParteCuerpoEPI.O, "Caucho");
        Medicamento medi = new Medicamento ("DLFIUGSHL45NVV",
                "Ibuprofeno", LocalDate.of (2023, 02, 07),
                3.75, Presentacion.S, "Arginina");
        Medicamento medica = new Medicamento ("LASLGHABACMWQQWR",
                "Flutox", LocalDate.of (2023, 06, 04),
                6.5, Presentacion.S, "Codeina");
        Medicamento medicamento = new Medicamento ("QPEQIUTG4Q96BV",
                "Ibuprofeno", LocalDate.of (2024, 07, 04),
                4.6, Presentacion.C, "Vitamina B12");
        tt.introducirProducto (p);
        tt.introducirProducto (e);
        tt.introducirProducto (o);
        tt.introducirProducto (pp);
        tt.introducirProducto (jj);
        tt.introducirProducto (ll);
        tt.introducirProducto (l);
        tt.introducirProducto (medi);
        tt.introducirProducto (medica);
        tt.introducirProducto (medicamento);
            //Se introducen los productos en el fichero
        for (Producto producto : tt.getProductos ( )) {
            tt.introducirProductoEnFichero (producto);
        }
        //Se muestran los productos antes de ser ordenados
        mostrarProductos (tt);
        System.out.println ("********************************************************************************\n" +
                "   Productos Ordenados \n********************************************************************************"
            );
        //Se muestran los productos despues de ser ordenados y consolidados.
        tt.ordenarArchivoEnLista ();
        tt.consolidarArchivoMaestro ();
        mostrarProductos (tt);
    }


    private static void mostrarProductos(FileAcces tt) {
        LinkedList<String> cadenasOrdenadas = (LinkedList<String>) FileAcces.txtReader (tt.getArchivoProductos ( ));
        for (String cadena : cadenasOrdenadas) {
            System.out.println (FileAcces.montarProducto (cadena));
        }
    }


    /**
     * FileAcces tt = new FileAcces();
     * LinkedList<String> cosa = (LinkedList<String>) FileAcces.txtReader(tt.getArchivoProductos ());
     * <p>
     * LinkedList<Producto> productos = new LinkedList<Producto>();
     * for(int i = 0; i < cosa.size(); i++){
     * productos.add(FileAcces.montarProducto(cosa.get(i)));
     * System.out.println(productos.get(i));
     * }
     * tt.eliminarProducto("WTFDSRIURJWEF23DF");
     */


    private static void mostrarProductosPorNombre(FileAcces fileAcces, String nombreProducto) {
        File file = fileAcces.getArchivoProductos ( );
        for (Producto producto : FileAcces.getProductosList (file)) {
            if (producto.getNombre ( ).equals (nombreProducto)) {
                System.out.println (producto);
            }
        }
    }
}
