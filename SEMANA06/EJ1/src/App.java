

import static com.coti.tools.Esdia.*;
import java.util.ArrayList;

import datos.Coche;




public class App {
    private static ArrayList<Coche> coches= new ArrayList<>();
    public static void main(String[] args) throws Exception {
        


        int opcion;
        do {
            System.out.println("\n--- MENU CRUD ---");
            System.out.println("1. Agregar coche");
            System.out.println("2. Eliminar coche");
            System.out.println("3. Modificar coche");
            System.out.println("4. Listar coches");
            System.out.println("5. Salir");
            opcion = readInt("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    agregarCoche();
                    break;
                case 2:
                    eliminarCoche();
              
                    break;
                case 3:
                    modificarCoche();
                    break;
                case 4:
                    listarCoches();
                    break;
                case 5:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    public static void agregarCoche(){

    int id=readInt("Escriba el id del coche: ", 0, 999);
    String modelo=readString("Escriba el modelo del coche: ");
    int anio=readInt("Escriba el anio del coche: ");
    int cv=readInt("Escriba los caballos del coche: ");

    coches.add(new Coche(id, modelo, anio, cv));
    }


    public static void eliminarCoche(){
        int id=readInt("Escriba el id del coche a eliminar: ");
        Coche cocheaeliminar=null;

        for(Coche coche : coches){
            if(coche.getId() == id){
                cocheaeliminar=coche;
                break;
            }
        }

        if(cocheaeliminar != null){
            coches.remove(cocheaeliminar);

        
        System.out.println("Coche eliminado.");
        } else {
            System.out.println("Coche no encontrado.");
        }
    }


    public static void modificarCoche(){
        int id=readInt("Escriba el id del coche a modificar");
        Coche cocheamodificar=null;

        for(Coche coche : coches){
            if(coche.getId() == id){
                cocheamodificar=coche;
                break;
            }
        }

        if(cocheamodificar != null){
            int nuevoid=readInt("Escriba el nuevo id: ");
            String nuevomodelo= readString("Escriba el nuevo modelo: ");
            int nuevoanio=readInt("Escriba el nuevo anio: ");
            int nuevocv=readInt("Escriba los nuevos caballos: ");

            cocheamodificar.setId(nuevoid);
            cocheamodificar.setModelo(nuevomodelo);
            cocheamodificar.setAnio(nuevoanio);
            cocheamodificar.setCv(nuevocv);

    } else {
            System.out.println("Coche no encontrado.");
        }
    }

        public static void listarCoches(){
            if(coches.isEmpty()){
                System.out.println("No hay coches");
                return;
            }

            for(Coche coche : coches){
                System.out.println(coche.getEstadocomoString());
            }
        



    }
}


