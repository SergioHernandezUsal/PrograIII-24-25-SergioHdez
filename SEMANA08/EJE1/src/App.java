import java.nio.file.Path;
import java.nio.file.Paths;

import com.coti.tools.Rutas;

import quiniela.model.Quiniela;

public class App {
    public static void main(String[] args) throws Exception {
        
        Path rutaArchivo = Paths.get(System.getProperty("user.home"), "Desktop", "partidos.txt");

        //Rutas con biblioteca.jar
        //Un fichero (partidos.txt) en Desktop
        //Al hacerlo con biblioteca.jar es más facil pero valen los dos
        Path rutaArchivoConBiblioteca = Rutas.pathToFileOnDesktop("partidos.txt");

        Quiniela quiniela = new Quiniela();
        int partidosCount = quiniela.cargarPartidos(rutaArchivo);
        System.out.printf("Se han cargado %d partidos", partidosCount);

    }
}