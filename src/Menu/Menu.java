package Menu;

import Clases.Producto;
import GestionFicheros.FileAcces;

import java.util.Scanner;

public class Menu {

    public static final String MENSAJEINICIAL = "1 --> Menú de Impresiones.\n2 --> Menú de Cálculos\n3 --> Menú de Gestión";
    public static final String MENSAJEIMPRESIONES = "1 --> Mostrar el listado de productos con el mismo nombre\n" +
            "2 --> Mostrar listado de productos con fecha de caducidad\nposterior a una fecha introducida\n" +
            "3 --> Imprimir precio total del almacén";
    public static final String MENSAJECALCULOS = "1 --> Mostrar cantidad de Productos con presentación Comprimidos\n" +
            "2 --> Mostrar cantidad de Productos con presentación Gotas\n3 --> Mostrar cantidad de Productos con presentación suspensión";
    public static final String MENSAJEGESTIONES = "";
    public static final String MENSAJEERROR = "Introduce una opción válida.";


    /**
     *<h2>pedirDato(Scanner, String)</h2>
     *
     * Método que devuelve un String introducido por teclado
     *
     * Precondiciones: ninguna
     * Postcondiciones: ninguna
     * @param sc
     * @param datoAIntroducir
     * @return
     */
    public static String pedirDato(Scanner sc, String datoAIntroducir){
        String mensaje = "introduzca aquí ";
        System.out.println (mensaje + datoAIntroducir );
        return sc.nextLine ();
    }



    public static Producto montarProductoUnico(FileAcces tt, String productoAMontar){
        Producto p = FileAcces.montarProducto (productoAMontar);
        while(p != null) {
            for (Producto producto : tt.getProductos ( )) {
                if (p.equals (producto)) {
                    p = null;
                }
            }
        }
        return p;
    }
}
