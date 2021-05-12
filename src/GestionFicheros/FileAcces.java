package GestionFicheros;

import Clases.*;
import ÇEnums.ParteCuerpoEPI;
import ÇEnums.Presentacion;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class FileAcces {

    private static final String PATH = ".\\src\\Ficheros\\Antipandemia.txt";
    public static File archivoProductos = new File (PATH);
    private List<Producto> productos;

    public FileAcces() {
        this.productos = new LinkedList<> ( );
    }

    /**
     * Método que sirve para introducir un producto en la lista de productos
     *
     * @param p
     * @return
     */
    public boolean introducirProducto(Producto p) {
        boolean success = false;
        if (p instanceof Medicamento || p instanceof Epi) {
            productos.add (p);
            success = true;
        }
        return success;
    }


    public File introducirProductosEnFichero() {
        BufferedWriter br = null;
        try {
            br = new BufferedWriter (new FileWriter (archivoProductos, true));
            for (Producto pr : productos) {
                if (!br.toString ( ).isBlank ( ))
                    br.newLine ( );
                br.write (pr.toString ( ));

            }
        } catch (IOException e) {
            System.out.println ("something was not what i expected");
        } finally {
            assert br != null;
            cerrarFlujo (br);
        }
        return this.archivoProductos;
    }


    /**
     * @param flujo
     */
    private static void cerrarFlujo(AutoCloseable flujo) {
        try {
            flujo.close ( );
        } catch (Exception t) {
            //No hace nada(Añadir algo)
        }
    }

    public static String[] devolverArrayDeObjetos(String fileString){
        String[] objectsArray = fileString.split ("!");
        return objectsArray;
    }

    /**
     *
     * Método que devuelve
     * Criterio de inserción
     * <ol>
     *     <h3>Comunes</h3>
     *     <li>codigoBarras</li>
     *     <li>nombre</li>
     *     <li>fechaCaducidad</li>
     *     <li>precio</li>
     *     <ol>
     *         <h3>Epi</h3>
     *         <li>parte</li>
     *         <li>material</li>
     *         <li>.endsWith("?")</li>
     *     </ol>
     *     <ol>
     *         <h3>Medicamento</h3>
     *         <li>presentacion</li>
     *         <li>principioActivo</li>
     *         <li>.endsWith(":")</li>
     *     </ol>
     * </ol>
     * @param stFile
     * @return
     */
     public static Producto montarProducto(String[] stFile) {
        LocalDate fechaCaducidad = null;
        double precio;
        Presentacion presentacion;
        ParteCuerpoEPI parte;
        Producto n = null;
        int contador = 0;
        //Creamos un array de cadenas gracias a .split
        for(String atributos : stFile){
            String[] stringArray = atributos.split (",");
            //Se da valor a los atributos comunes
            fechaCaducidad = LocalDate.parse (stringArray[1], DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
            precio = Double.parseDouble (stringArray[3]);
            //Comprobamos si es un Epi o un Medicamento por el último caracter de sus toString
            if(atributos.endsWith (":")){
                presentacion = Presentacion.valueOf (stringArray[4]);
                n = new Medicamento (stringArray[0], stringArray[1], fechaCaducidad, precio, presentacion, stringArray[5]);
            } else if(atributos.endsWith ("?")) {
                parte = ParteCuerpoEPI.valueOf (stringArray[4]);
                n = new Epi (stringArray[0], stringArray[1], fechaCaducidad, precio,parte,stringArray[5]);
            }
        }



        return n;
    }


    /**
     * @param file
     * @return
     */
    public static String txtReader(File file) {
        var loco = "";
        String madreMia;
        BufferedReader input = null;
        try {
            input = new BufferedReader (new FileReader (file));//Creamos el lector de Strings
            while ((madreMia = input.readLine ( )) != null) {
                // mientras que la linea de Strings que lee input no sea null
                loco += madreMia + "\n";
            }//end while
        }//End try
        catch (IOException e) {
            System.out.println ("Can´t open the file");
            assert input != null;
            cerrarFlujo (input);
        }
        return loco;
    }
}
