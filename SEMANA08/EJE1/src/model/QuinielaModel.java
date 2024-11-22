package model;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class QuinielaModel {

    ArrayList<Partido> partidos;

    public QuinielaModel() {
        partidos = new ArrayList<Partido>();
    }






    public boolean cargarPartidosDeFichero() {
        
        // Identificar la ruta (con Path)
        Path rutaAlFichero = Paths.get(System.getProperty("user.home"), "Desktop", "DATOS", "equipos.txt");
        // Objeto File
        File f = rutaAlFichero.toFile();

        // Comprobamos que la ruta exista y que ademas sea un fichero
        if(f.exists() && f.isFile()){

        ArrayList<Partido> partidosLeidos = new ArrayList<>();    
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(rutaAlFichero, StandardCharsets.UTF_8); // suponemos que está en UTF-8

            for (String linea : lineas) {
                // Transformamos la linea en un objeto Partido
                // Factory Method
                Partido p = Partido.createPartidoFromDelimitedString(linea, "-");
                if(p != null){
                    partidosLeidos.add(p);
                }
            }

            if(partidosLeidos.size() == 15){
                // Añadimos los leidos al ArrayList
                this.partidos = partidosLeidos;
                return true;
            }else{
                return false;
            }


        } catch (IOException ex) {
            // Es una mala practica comentar un error en un try catch, peeero...
            //ex.printStackTrace();
            return false;
        }


        }else{
            return false;
        }



    }






    public ArrayList<Partido> getPartidos() {
        // En vez de hacer return partidos; (referencia directa el modelo)
        return new ArrayList<>(partidos); // Crea una copia
    }






    public void setPartidos(ArrayList<Partido> partidos2) {
        this.partidos = partidos2;
    }

}
