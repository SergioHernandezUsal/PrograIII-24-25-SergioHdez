package view;
import static com.coti.tools.Esdia.*;

import java.util.ArrayList;
import java.util.List;

import controller.FacturaCrontroller;
public class FacturaView {

   
    private FacturaCrontroller controlador;

    public void menu(){
        int opcion;

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Mostrar clientes con facturas superiores a un valor mínimo");
            System.out.println("2. Mostrar listado total de facturas");
            System.out.println("3. Exportar a csv");
            System.out.println("0. Salir");
           
            opcion= readInt("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    mostarFacturasSuperior();
                    break;
                case 2:
                    listadoFacturas();
                    break;

                case 3:
                exportarCsv();
                    break;
                
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostarFacturasSuperior(){
        double valorFacturaMin=readDouble("Ingrese el valor minimo de factura: ");

        List<String> clientes = controlador.getclientesmin(valorFacturaMin);
        for (String cliente : clientes) {
            System.out.println(cliente);
        }

    }

    private void listadoFacturas(){
        System.out.println("LISTADO DE FACTURAS");
        ArrayList<String> listado= controlador.listadofacturas();
        for (String factura : listado) {
            System.out.println(factura);
        }
        
        
    

    }

    private void exportarCsv(){
        String archivo=readString("Introduzca el nombre del archivo CSV: ");
        System.out.println(controlador.exportarCsv(archivo));
        
    }

    public void setControlador(FacturaCrontroller controlador) {
        this.controlador = controlador;
    }


}
