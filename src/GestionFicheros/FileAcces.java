package GestionFicheros;

import Clases.*;
import ÇEnums.ParteCuerpoEPI;
import ÇEnums.Presentacion;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

public class FileAcces {

    private static final String PATH = ".\\src\\Ficheros\\Antipandemia.txt";
    private File archivoAux;
    private File archivoProductos;
    private List<Producto> productos;
    private static String AUXFILEPATH = ".\\src\\Ficheros\\fichAux.txt";

    public FileAcces() {
        archivoProductos = new File (PATH);
        archivoAux = new File (AUXFILEPATH);
        this.productos = new LinkedList<> ( );
    }

    public File getArchivoProductos() {
        return archivoProductos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public File getArchivoAux() {
        return archivoAux;
    }

    /**
     * @param p
     * @return
     */
    public File introducirProductoEnFichero(Producto p) {
        BufferedWriter br = null;
        try {
            br = new BufferedWriter (new FileWriter (archivoProductos, true));
            br.write (p.toString ( ));
            br.newLine ( );
        } catch (IOException e) {
            System.out.println ("something was not what i expected");
        } finally {
            if (br != null)
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


    /**
     * Método que lee un File y lo convierte en un String
     *
     * @param file
     * @return
     */
    public static List<String> txtReader(File file) {
        LinkedList<String> productos = new LinkedList<> ( );
        String productoString;
        BufferedReader input = null;
        try {
            input = new BufferedReader (new FileReader (file));//Creamos el lector de Strings
            while ((productoString = input.readLine ( )) != null) {
                //mientras que la linea de Strings que lee input no sea null
                productos.add (productoString);
            }//end while
        }//End try
        catch (IOException e) {
            System.out.println ("Can´t open the file");
            if (input != null)
                cerrarFlujo (input);
        }
        return productos;
    }

    public boolean validarProducto(Producto p) {
        boolean success = true;
        BufferedReader br = null;
        String linea = "";
        try {
            br = new BufferedReader (new FileReader (this.archivoProductos));
            while ((linea = br.readLine ( )) != null && success) {
                if (linea.split (",")[1].equals (p.getCodigoBarras ( ))) {
                    success = false;
                }
            }
        } catch (IOException e) {

        } finally {
            if (br != null)
                cerrarFlujo (br);
        }
        return success;
    }


    /**
     * <h2>introducirProductoEnFachero(Producto)</h2>
     * <p>
     * Método que introduce un producto en el fichero de productos.
     * Precondiciones: productoAIntroducir debe ser un producto instanciado.
     * Postcondiciones: Ninguna
     * y en caso de ser false, significará que no se ha podido realizar la insercion, ya que el producto no ha podido ser validado
     *
     * @param productoAIntroducir
     * @return
     */
    public boolean introducirProductoEnFachero(Producto productoAIntroducir) {
        boolean success = true;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter (new FileWriter (this.archivoAux, true));
            if (validarProducto (productoAIntroducir)) {
                bw.write (productoAIntroducir.toString ( ));
                bw.newLine ( );
            } else {
                success = false;
            }
        } catch (IOException e) {

        } finally {
            if (bw != null)
                cerrarFlujo (bw);
        }
        return success;
    }

    /**
     * <h2>montarProducto(String[])</h2>
     * <p>
     * Método que devuelve una Lista de Productos a partir de un Array de cadenas con los siguientes
     * criterios de inserción.
     *
     * @param file: Array de cadenas que, en cada posicion contiene todos los atributos de un Objeto Producto
     * @return n: Lista de Productos construidos a partir de las cadenas
     * recogidas de un Array de Cadenas
     */
    public static List<Producto> getProductosList(File file) {
        List<String> listaProductos = txtReader (file);
        //Creamos la lista de objetos que se devolverá
        List<Producto> pr = new LinkedList<Producto> ( );
        //Creamos un array de cadenas gracias a .split
        for (String atributos : listaProductos) {
            Producto n = montarProducto (atributos);
            if (n != null)
                pr.add (n);
        }

        //Se devuelve un Producto construido.
        return pr;
    }


    /**
     * <h2>montarProducto(String)</h2>
     * <p>
     * Método que crea un producto a partir de una cadena de caracteres separados por coma
     * <ol><h1>Criterio de inserción</h1>
     *    <h3>Comunes</h3>
     *   <li>codigoBarras</li>
     *   <li>nombre</li>
     *    <li>fechaCaducidad</li>
     *      <li>precio</li>
     *      <ol>
     *            <h3>Epi</h3>
     *              <li>parte</li>
     *              <li>material</li>
     *
     *          </ol>
     *          <ol>
     *              <h3>Medicamento</h3>
     *              <li>presentacion</li>
     *              <li>principioActivo</li>
     *
     *          </ol>
     *      </ol>
     *
     * @param prodString
     * @return
     */
    public static Producto montarProducto(String prodString) {
        LocalDate fechaCaducidad = null;
        double precio;
        Presentacion presentacion;
        ParteCuerpoEPI parte;
        String[] productoArray;
        Producto n = null;
        productoArray = prodString.split (",");
        if ((productoArray.length > 0)) {
            //Se da valor a los atributos comunes
            fechaCaducidad = LocalDate.parse (productoArray[3], DateTimeFormatter.ofPattern ("yyyy-MM-dd"));
            precio = Double.parseDouble (productoArray[4]);
            //Comprobamos si es un Epi o un Medicamento por la primera posicion del array
            switch (productoArray[0]) {
                case "Medicamento" -> {
                    presentacion = Presentacion.valueOf (productoArray[5]);
                    n = new Medicamento (productoArray[1], productoArray[2], fechaCaducidad, precio, presentacion, productoArray[6]);
                }
                case "Epi" -> {
                    parte = ParteCuerpoEPI.valueOf (productoArray[5]);
                    n = new Epi (productoArray[1], productoArray[2], fechaCaducidad, precio, parte, productoArray[6]);
                }
            }
        }
        return n;
    }


    /**
     * <h2>ordenarArchivoEnLista()</h2>
     * <p>
     * Método que ordena los Productos de un archivo en una lista y lo devuelve para ser utilizada en otro
     * método que ordenará el archivo auxiliar
     *
     * @return
     */
    public List<Producto> ordenarArchivoEnLista() {
        productos.clear ( );
        Producto prodCreado;
        String linea = "";
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader (new FileReader (this.archivoAux));
            while ((linea = br.readLine ( )) != null) {
                prodCreado = montarProducto (linea);
                this.productos.add (prodCreado);
            }

        } catch (IOException e) {
            cerrarFlujo (br);
        }
        productos.sort (Producto::compareTo);
        return productos;
    }
    public boolean escribirListaEnFicheroAux(List<Producto> productos) {
        boolean success = true;
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter (new FileWriter (this.archivoAux));
            for (Producto producto : productos) {
                bw.write (producto.toString ( ));
                bw.newLine ( );
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        } finally {
            cerrarFlujo (bw);
        }

        return success;
    }


    public boolean consolidarArchivoMaestro() {
        boolean success = true;
        BufferedWriter bw = null;
        BufferedReader be = null;
        BufferedReader br = null;
        String line = "";
        String lineArchivoMAster = "";
        try {
            bw = new BufferedWriter (new FileWriter (this.archivoProductos, true));
            br = new BufferedReader (new FileReader (this.archivoProductos));
            be = new BufferedReader (new FileReader (this.archivoAux));
            while((line = be.readLine ()) != null){
                if(this.archivoProductos.length () > 0) {
                    if (!line.split (",")[1].equals (br.readLine ( ).split (",")[1])) {
                        bw.write (line);
                        bw.newLine ( );
                    }
                }else {
                    bw.write (line);
                    bw.newLine ();
                }
            }
        } catch (IOException e) {
            cerrarFlujo (bw);
            success = false;
        }

        return success;
    }

    public static List<Producto> mostrarProductos(File file) {
        BufferedReader br = null;
        List<Producto> productos = new LinkedList<> ( );
        String line = "";
        Producto p;
        try {
            br = new BufferedReader (new FileReader (file));
            while ((line = br.readLine ( )) != null) {
                p = montarProducto (line);
                productos.add (p);
            }
        } catch (IOException e) {
            e.printStackTrace ( );
        } finally {
            if (br != null)
                cerrarFlujo (br);
        }
        return productos;
    }


    /**
     * Método que elimina un producto comparando, gracias a un fichero auxiliar,
     * todos los productos de un fichero con un código de barras previamente
     * introducido como parámetro
     * Precondiciones: Ninguna
     * Postcondiciones: Devolverá un booleano que, en caso de ser false, significará
     * que el borrado no ha sido realizado ya que el código de barras no coincide
     * con ninguno de los que tenemos en el fichero
     *
     * @param codigoBarras
     * @return boolean success
     */
    public boolean eliminarProducto(String codigoBarras) {
        BufferedReader brFichMaster = null;
        boolean success = false;
        BufferedReader brFichAux = null;
        BufferedWriter bwFichMaster = null;
        BufferedWriter bwFichAux = null;
        String lineaLeida = "";
        String lineaLeidaAux = "";
        try {
            //Creamos un fichero auxiliar para el borrado de productos

            //Se establecen los lectores y escritores de ambos archivos
            brFichMaster = new BufferedReader (new FileReader (this.archivoProductos));
            brFichAux = new BufferedReader (new FileReader (archivoAux));
            bwFichAux = new BufferedWriter (new FileWriter (archivoAux));
            bwFichMaster = new BufferedWriter (new FileWriter (this.archivoProductos));
            //Mientras que no sea null, se leerá la siguiente linea del fichero Master
            while ((lineaLeida = brFichMaster.readLine ( )) != null) {
                //Spliteamos para recoger el atributo del array que coincide con el valor de código de barras
                if (!lineaLeida.split (",")[1].equals (codigoBarras)) {
                    //Si no es el fichero a borrar, este será escrito en el fichero auxiliar
                    bwFichAux.write (lineaLeida);
                    bwFichAux.newLine ( );
                } else {
                    //Si se encuentra, este no se escribirá en el fichero auxiliar
                    success = true;
                }
            }
            //Borramos el archivo master y establecemos que el archivo auxiliar ahora será el fichero master
            archivoProductos.delete ( );
            archivoAux.renameTo (archivoProductos);
            while ((lineaLeidaAux = brFichAux.readLine ( )) != null) {
                bwFichMaster.write (lineaLeidaAux);
                bwFichMaster.newLine ( );
            }

        } catch (IOException e) {
            //Cerramos los flujos
            cerrarFlujo (brFichAux);
            cerrarFlujo (bwFichAux);
            cerrarFlujo (brFichMaster);
            cerrarFlujo (bwFichMaster);
        }
        return success;
    }


}
