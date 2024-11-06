package quiniela.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Quiniela {

    private ArrayList<Partido> partidos;


    public Quiniela(){
        this.partidos = new ArrayList<>();
    }

    public int cargarPartidos(Path archivoPartidos){
        List<String> lineas = null;
        try {
            lineas = Files.readAllLines(archivoPartidos, StandardCharsets.UTF_8);
            for(String linea: lineas){
                //.split separa cada equipo por el guion "-"
                String[] equipos = linea.split("-");
                partidos.add(new Partido(equipos[0],equipos[1], 0, 0, linea));

            }

            return lineas.size();
        } catch (IOException e) {
            // TODO: handle exception
        }
        return -1;
    }
