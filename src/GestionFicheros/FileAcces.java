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
    public static File archivoProductos = new File(PATH);
    private List<Producto> productos;

    public FileAcces() {
        this.productos = new LinkedList<>();
    }

    /**
     * Método que sirve para introducir un producto en la lista de productos
     *
     * @param p
     * @return
     */
    public boolean introducirProducto(Producto p) {
        boolean success = false;
        boolean exit = false;
        for(Producto pr : this.productos) {
            if(pr.equals(p)){
                exit = true;
            }
        }
            if ((p instanceof Medicamento || p instanceof Epi) && !exit) {
                productos.add(p);
                success = true;
            }

        return success;
    }

    /**
     *
     *
     * @param p
     * @return
     */
    public File introducirProductoEnFichero(Producto p) {
        BufferedWriter br = null;
        try {
            //todo quitar
            br = new BufferedWriter(new FileWriter(archivoProductos, true));
                br.write(p.toString());
                br.newLine();
        } catch (IOException e) {
            System.out.println("something was not what i expected");
        } finally {
            if(br != null)
            cerrarFlujo(br);
        }
        return this.archivoProductos;
    }


    /**
     * @param flujo
     */
    private static void cerrarFlujo(AutoCloseable flujo) {
        try {
            flujo.close();
        } catch (Exception t) {
            //No hace nada(Añadir algo)
        }
    }

    /**
     * Método que lee un File y lo convierte en un String
     *
     * @param file
     * @return
     */
   public static List<String> txtReader(File file) {
        LinkedList<String> productos = new LinkedList<>();
        String productoString;
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(file));//Creamos el lector de Strings
            while ((productoString = input.readLine()) != null) {
                // mientras que la linea de Strings que lee input no sea null
                productos.add(productoString);
            }//end while
        }//End try
        catch (IOException e) {
            System.out.println("Can´t open the file");
            if(input != null)
            cerrarFlujo(input);
        }
        return productos;
    }


    /**
     * <h2>montarProducto(String[])</h2>
     *
     * Método que devuelve una Lista de Productos a partir de un Array de cadenas con los siguientes
     * criterios de inserción.
     *
     *
     * <ol><h1>Criterio de inserción</h1>
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
     *
     * @param listaProductos: Array de cadenas que, en cada posicion contiene todos los atributos de un Objeto Producto
     * @return n: Lista de Productos construidos a partir de las cadenas
     * recogidas de un Array de Cadenas
     */
    public static List<Producto> getProductos(List<String> listaProductos) {
        //Creamos la lista de objetos que se devolverá
        List<Producto> pr = new LinkedList<Producto>();
        //Creamos un array de cadenas gracias a .split
        for (String atributos : listaProductos) {
           Producto n = montarProducto(atributos);
            if(n != null)
            pr.add(n);
        }

        //Se devuelve un Producto construido.
        return pr;
    }


    /**
     * <h2>montarProducto(String)</h2>
     *
     * Método que crea un producto a partir de una cadena de caracteres separados por coma
     * @param prodString
     * @return
     */
    public static Producto montarProducto(String prodString){
        LocalDate fechaCaducidad = null;
        double precio;
        Presentacion presentacion;
        ParteCuerpoEPI parte;
        String[] productoArray;
        Producto n = null;
        productoArray = prodString.split(",");
        if(!(productoArray.length <= 1)) {
            //Se da valor a los atributos comunes
            fechaCaducidad = LocalDate.parse(productoArray[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            precio = Double.parseDouble(productoArray[4]);
            //Comprobamos si es un Epi o un Medicamento por la primera posicion del array
            if (productoArray[0].equals("Medicamento")) {
                presentacion = Presentacion.valueOf(productoArray[5]);
                n = new Medicamento(productoArray[1], productoArray[2], fechaCaducidad, precio, presentacion, productoArray[6]);
            } else if (productoArray[0].equals("Epi")) {
                parte = ParteCuerpoEPI.valueOf(productoArray[5]);
                n = new Epi(productoArray[1], productoArray[2], fechaCaducidad, precio, parte, productoArray[6]);
            }
        }
        return n;
    }


}
