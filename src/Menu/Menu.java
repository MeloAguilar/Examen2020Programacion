package Menu;

import Clases.Producto;
import GestionFicheros.FileAcces;

import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public static final String MENSAJE_MENU_GESTION = "1 --> Añadir Producto\n2 --> Borrar Producto\n3 --> Salir";
    public static final String MENSAJE_MENU_CALCULOS = "1 --> Total de productos en comprimidos\n2 --> Total de productos en gotas\n3 --> Total de productos en suspension\n4 --> Salir";
    public static final String MENSAJE_MENU_IMPRESION = "1 --> Imprimir productos con el mismo nombre\n" +
            "2 --> Imprimir productos con fecha de caducidad posterior a la introducida\n" +
            "3 --> Imprimir total en € de los productos existentes en el almacén.\n" +
            "4 --> Salir";


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


    /**
     * <h2>montarProductoUnico(FileAccess, String)</h2>
     *
     *
     * Método que valida que un producto no se encuentre repetido en el fichero y lo inserta en este.
     * Precondiciones:
     * Postcondiciones:
     * @param tt
     * @param productoAMontar
     * @return
     */
    public static Producto montarProductoUnico(FileAcces tt, String productoAMontar){
        Producto p = FileAcces.montarProducto (productoAMontar);
        boolean exit = false;
        while(!exit) {
            for (int i = 0; i < tt.getProductos ( ).size () && !exit; i++) {
                if (p.equals (tt.getProductos ().get(i))) {
                    p = null;
                    exit = true;
                }
            }
            exit = true;
        }
        return p;
    }
/*
    public static Producto montarProducto(){
        return Producto p = new Producto ()
    }

 */


    /**
     * Método que valida una fecha introducida como String
     * @param fechaString
     * @return
     */
    public static LocalDate validarFecha(String fechaString){
        LocalDate fechaBuena;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern ("yyyy-MM-dd");
        try{
            fechaBuena = LocalDate.parse (fechaString, formato);
        }catch(DateTimeParseException e){
            fechaBuena = null;
        }
        return fechaBuena;
    }



}
