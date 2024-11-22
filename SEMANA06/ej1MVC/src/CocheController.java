
import static com.coti.tools.Esdia.readInt;
import static com.coti.tools.Esdia.readString;

import java.util.ArrayList;





public class CocheController {
    
    private ArrayList<CocheModel> coches;
    private CocheView view;

    public CocheController(CocheView view) {
        this.coches = new ArrayList<>();
        this.view = view;
    }

    public void run(){
    boolean running = true;
    while (running) {
    view.mostarmenu();
    String opcion = view.getOpcion();
    switch (opcion) {
        case "1":
        int id=view.leerInt("Ingrese el id del coche: ");
        String modelo=view.leerString("Ingrese el modelo del coche:");
        int anio=view.leerInt("Ingrese el anio del coche: ");
        int cv=view.leerInt("Ingrese los cv del coche: ");

        coches.add(new CocheModel(id, modelo, anio, cv));
        break;
        case "2":
         id=readInt("Escriba el id del coche a eliminar: ");
        CocheModel cocheaeliminar=null;

        for(CocheModel coche : coches){
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
            break;
        case "3":
        id=readInt("Escriba el id del coche a modificar");
        CocheModel cocheamodificar=null;

        for(CocheModel coche : coches){
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
        view.mostrarMensaje("Coche no encontrado ");
        }    
            break;
        case "4":
        if(coches.isEmpty()){
            System.out.println("No hay coches");
            return;
        }

        for(CocheModel coche : coches){
            System.out.println(coche.getEstadocomoString());
        }
            break;
        case "5":
        running = false;
        view.mostrarMensaje("Saliendo del programa. ¡Adiós!");  
            break;
    
        default:
        view.mostrarMensaje("Opción inválida, intente nuevamente.");
            break;
    }
    }

    
}
}
