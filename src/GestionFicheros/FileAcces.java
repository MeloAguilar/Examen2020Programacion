package GestionFicheros;
import Clases.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class FileAcces {

    private static final String PATH = ".\\Ficheros\\src\\Antipandemia.txt";
    private File archivoProductos = new File(PATH);
    private List<Producto> productos;

    public FileAcces() {
        this.productos = new LinkedList<>();
    }

    /**
     * Método que sirve para introducir un producto en la lista de productos
     * @param p
     * @return
     */
    public boolean introducirProducto(Producto p){
        boolean success = false;
        if(p instanceof Medicamento || p instanceof Epi){
            productos.add(p);
            success = true;
        }
    return success;
    }


    public File introducirProductosEnFichero(){
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(archivoProductos, true));
            for(Producto pr : productos){
                br.write(pr.toString());
            }
        }catch(IOException e){
            System.out.println("something was not what i expected");
        }finally{
            try{
                assert br != null;
                br.close();
            }catch(IOException t){

            }
        }
        return this.archivoProductos;
    }


    public boolean rellenarFichero(){
        boolean success = false;
        BufferedWriter br = null;
        try {
            br = new BufferedWriter((new FileWriter(archivoProductos, true)));


        }catch(IOException e){

        }finally{
            try{
                assert br != null;
                br.close();
            }catch(IOException r){

            }
        }
        return success;
    }




    public static String txtReader(File file) {
        var loco = "";
        String madreMia;
        BufferedReader input = null;
        try {
            FileReader buffer = new FileReader(file);//Creamos el lector de caracteres
            input = new BufferedReader(buffer);//Creamos el lector de Strings
            while ((madreMia = input.readLine()) != null) {//mientras que la linea de Strings que lee input no sea null
                loco += madreMia + "\n";
            }//end while

        }//End try
        catch (IOException e) {
            System.out.println("Can´t open the file");
            try{
                assert input != null;
                input.close();
            }catch(IOException r){
                System.out.println("Can´t close something closed");
            }
        }
        return loco;
    }
}
