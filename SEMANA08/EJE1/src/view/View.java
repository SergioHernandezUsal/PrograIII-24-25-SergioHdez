package view;

import java.util.ArrayList;

import com.coti.tools.Esdia;
import controller.Controller;
import model.Partido;

public class View {

    Controller c;

    public void setController(controller.Controller controller) {
        this.c = controller;
    }

    public void showMainMenu() {
        
        boolean salir  = false;

        while(!salir){

            int opcion = mostrarMenuPorPantalla();


            switch (opcion) {
                case 1:
                    cargarPartidosDeFichero();
                    break;

                case 2:
                    insertarResultados();
                    break;

                case 3:
                    mostrarQuiniela();
                    break;

                case 4:
                    System.out.println("SALIENDO...");
                    salir = true;
                    break;
            
                default:
                    break;
            }
        }

    }

    private void mostrarQuiniela() {
        ArrayList<Partido> partidos = c.getPartidos();

        // Mostrar la cabecera
        System.out.println(Partido.getCabeceraTabla());
        // Mostrar lineas de partido
        for (Partido p : partidos) {
            System.out.println(p.asTableRow());;
        }
    }

    private void insertarResultados() {
        ArrayList<Partido> partidos = c.getPartidos();
        for (Partido p : partidos) {
            System.out.println("Partido:"+p.getEquipoLocal()+"-"+p.getEquipoVisitante());
            int golesLocal = Esdia.readInt("Goles Local: ");
            int golesVisitante = Esdia.readInt("Goles Visitante: ");
            p.setGolesLocal(golesLocal);
            p.setGolesVisitante(golesVisitante);
        }
        c.setPartidos(partidos);
    }

    private int mostrarMenuPorPantalla() {
        System.out.println(" ----- MENU QUINIELA -----");
        System.out.println(" 1) CARGAR LOS PARTIDOS");
        System.out.println(" 2) PEDIR LOS RESULTADOS AL USUARIO");
        System.out.println(" 3) MOSTRAR QUINIELA");
        System.out.println(" 4) SALIR");

        int opcion = Esdia.readInt("Introduzca una opción: ", 1,4);
        return opcion;
    }

    private void cargarPartidosDeFichero() {
        if(c.cargarPartidosDeFichero()){
            showMessage("Fichero leido correctamente");
        }else{
            showErrorMessage(null);
        }
    }

    public void showMessage(String string) {
        System.out.println(string);
    }

    public void showErrorMessage(String string) {
        System.err.println(string);
    }
}
