package view;

import controller.Controller;

import java.util.Scanner;

public class ConsoleView extends BaseView {
    private Scanner scanner;

    public ConsoleView(Controller controller) {
        super(controller);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void init() {
        System.out.println("Bienvenido a la aplicación de tareas.");
        mostrarMenuPrincipal();
    }

    @Override
    public void showMessage(String mensaje) {
        System.out.println("Mensaje: " + mensaje);
    }

    @Override
    public void showErrorMessage(String error) {
        System.err.println("Error: " + error);
    }

    @Override
    public void end() {
        System.out.println("Gracias por usar la aplicación. ¡Adiós!");
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        boolean ejecutando = true;
        while (ejecutando) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Operaciones CRUD");
            System.out.println("2. Exportar/Importar Tareas");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    mostrarMenuCRUD();
                    break;
                case 2:
                    mostrarMenuExportarImportar();
                    break;
                case 3:
                    ejecutando = false;
                    end();
                    break;
                default:
                    showErrorMessage("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void mostrarMenuCRUD() {
        System.out.println("\nMenú CRUD:");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Modificar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                crearTarea();
                break;
            case 2:
                listarTareas();
                break;
            case 3:
                modificarTarea();
                break;
            case 4:
                eliminarTarea();
                break;
            case 5:
                return;
            default:
                showErrorMessage("Opción no válida.");
        }
    }

    private void mostrarMenuExportarImportar() {
        System.out.println("\nMenú de Exportación/Importación:");
        System.out.println("1. Exportar tareas a CSV");
        System.out.println("2. Exportar tareas a JSON");
        System.out.println("3. Importar tareas desde CSV");
        System.out.println("4. Importar tareas desde JSON");
        System.out.println("5. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcion) {
            case 1:
                exportarTareasACSV();
                break;
            case 2:
                exportarTareasAJSON();
                break;
            case 3:
                importarTareasDesdeCSV();
                break;
            case 4:
                importarTareasDesdeJSON();
                break;
            case 5:
                return;
            default:
                showErrorMessage("Opción no válida.");
        }
    }

    private void crearTarea() {
        System.out.println("Creando una nueva tarea...");
    }

    private void listarTareas() {
        System.out.println("Listando todas las tareas...");
    }

    private void modificarTarea() {
        System.out.println("Modificando una tarea existente...");
    }

    private void eliminarTarea() {
        System.out.println("Eliminando una tarea...");
    }

    private void exportarTareasACSV() {
        System.out.println("Exportando tareas a un archivo CSV...");
    }

    private void exportarTareasAJSON() {
        System.out.println("Exportando tareas a un archivo JSON...");
    }

    private void importarTareasDesdeCSV() {
        System.out.println("Importando tareas desde un archivo CSV...");
    }

    private void importarTareasDesdeJSON() {
        System.out.println("Importando tareas desde un archivo JSON...");
    }
}
