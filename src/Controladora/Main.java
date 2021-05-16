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
        boolean exit3 = false;
        boolean exit4 = false;
        FileAcces tt = new FileAcces ( );
        introducirDatosIniciales (tt);
        do {
            System.out.println (Menu.MENSAJEINICIAL);
            switch (sc.nextLine ( )) {
                //Menú de Impresión.
                case "1" -> {
                    do {
                        System.out.println (Menu.MENSAJE_MENU_IMPRESION);
                        switch (sc.nextLine ( )) {
                            //listado de Productos con el mismo nombre
                            case "1" -> {
                                String nombre = Menu.pedirDato (sc, "el nombre del producto.");
                                mostrarProductosPorNombre (tt, nombre);
                            }
                            //Listado de productos por fecha de caducidad
                            case "2" -> {
                                LocalDate fecha = Menu.validarFecha (Menu.pedirDato (sc, "La fecha en formato yyyy-MM-dd"));

                            }
                            //Imprimir el total en euros del fichero
                            case "3" -> {

                            }
                            //Salir
                            case "4" -> {
                                exit2 = true;
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
                    do {
                        System.out.println (Menu.MENSAJE_MENU_CALCULOS);
                        switch (sc.nextLine ( )) {
                            //total de productos comprimidos
                            case "1" -> {

                            }
                            //Total de productos gotas
                            case "2" -> {

                            }
                            //Total de productos suspensión
                            case "3" -> {

                            }
                            //Salir
                            case "4" -> {
                                exit3 = true;
                            }
                        }
                    } while (!exit3);
                }
                //Menú de Gestión
                case "3" -> {
                    do {
                        System.out.println (Menu.MENSAJE_MENU_GESTION);
                        switch (sc.nextLine ( )) {
                            //Añadir producto
                            case "1" -> {

                            }
                            //Borrar producto
                            case "2" -> {

                            }
                            //Salir
                            case "3" -> {
                                exit4 = true;
                            }
                        }
                    } while (!exit4);
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
        tt.introducirProductoEnFachero (p);
        tt.introducirProductoEnFachero (e);
        tt.introducirProductoEnFachero (o);
        tt.introducirProductoEnFachero (pp);
        tt.introducirProductoEnFachero (jj);
        tt.introducirProductoEnFachero (ll);
        tt.introducirProductoEnFachero (l);
        tt.introducirProductoEnFachero (medi);
        tt.introducirProductoEnFachero (medica);
        tt.introducirProductoEnFachero (medicamento);
        //Se muestran los productos antes de ser ordenados
        mostrarProductos (tt);
        System.out.println ("********************************************************************************\n" +
                "   Productos Ordenados \n********************************************************************************"
        );
        //Se muestran los productos despues de ser ordenados y consolidados.
        List<Producto> productos =  tt.ordenarArchivoEnLista ( );
        tt.escribirListaEnFicheroAux (productos);
        tt.consolidarArchivoMaestro ( );

        mostrarProductos (tt);
    }


    private static void mostrarProductos(FileAcces tt) {
        LinkedList<Producto> cadenasOrdenadas = new LinkedList<> (  );
        if(tt.getArchivoProductos ().length () < 1) {
           cadenasOrdenadas = (LinkedList<Producto>) FileAcces.mostrarProductos (tt.getArchivoAux ( ));
        } else {
            cadenasOrdenadas = (LinkedList<Producto>) FileAcces.mostrarProductos (tt.getArchivoProductos ());
        }
        for(Producto producto : cadenasOrdenadas){
            System.out.println (producto );
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
        File file = fileAcces.getArchivoAux ( );
        for (Producto producto : FileAcces.getProductosList (file)) {
            if (producto.getNombre ( ).equals (nombreProducto)) {
                System.out.println (producto);
            }
        }
    }
}
