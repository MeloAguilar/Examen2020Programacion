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
        if (p instanceof Medicamento || p instanceof Epi) {
            productos.add(p);
            success = true;
        }
        return success;
    }


    public File introducirProductosEnFichero() {
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(archivoProductos, true));
            for (Producto pr : productos) {
                if (!br.toString().isBlank())
                    br.newLine();
                br.write(pr.toString());

            }
        } catch (IOException e) {
            System.out.println("something was not what i expected");
        } finally {
            assert br != null;
            cerrarFlujo(br);
        }
        return this.archivoProductos;
    }


    private static void cerrarFlujo(AutoCloseable flujo)  {
        try {

            flujo.close();
        } catch (Exception t) {

        }
    }

    public boolean rellenarFichero() {
        boolean success = false;
        BufferedWriter br = null;
        try {
            br = new BufferedWriter((new FileWriter(archivoProductos, true)));
        } catch (IOException e) {

        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException r) {

            }
        }
        return success;
    }

    public static Producto montarProducto(String stFile){
        String codigoBarras = "";
        String nombre = "";
        LocalDate fechaCaducidad = null;
        double precio = 0;
        Presentacion presentacion = null;
        String principioActivo = "";
        ParteCuerpoEPI parte = null;
        String material = "";

        Producto n = null;
        String[] stringArray = stFile.split(", ");
        for (String atributo : stringArray) {
            String[] atributoValor = atributo.split("=");
            String atributo1 = atributoValor[0];
            String valor = atributoValor[1];
            switch (atributo1) {
                case "codigoBarras" -> {
                    codigoBarras = valor;
                }
                case "nombre" -> {
                    nombre = valor;
                }
                case "fechaCaducidad" -> {
                    String fecha = valor;
                    fechaCaducidad = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                case "precio" -> {
                    precio = Double.parseDouble(valor);
                }
                case "presentacion" -> {
                    presentacion = Presentacion.valueOf(valor);
                }
                case "principioActivo" -> {
                    principioActivo = valor;
                }
                case "parte" ->{
                    parte = ParteCuerpoEPI.valueOf(valor);
                }
                case "material" ->{
                    material = valor;
                }

            }

        }

        if(parte == null){
            n = new Medicamento(codigoBarras,nombre,fechaCaducidad,precio,presentacion,principioActivo);
        }else{
            n = new Epi(codigoBarras,nombre,fechaCaducidad,precio,parte,material);
        }
        return n;
    }


    public static String txtReader(File file) {
        var loco = "";
        String madreMia;
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(file));//Creamos el lector de Strings
            while ((madreMia = input.readLine()) != null) {
                // mientras que la linea de Strings que lee input no sea null
                loco += madreMia + "\n";
            }//end while
        }//End try
        catch (IOException e) {
            System.out.println("Can´t open the file");
                assert input != null;
                cerrarFlujo(input);
        }
        return loco;
    }
}
