package view;

import controller.Controller;
import model.RepositoryException;

import java.util.Scanner;

public class ConsoleView extends BaseView {
    private Scanner scanner;

    public ConsoleView(Controller controller) {
        super(controller);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void init() throws RepositoryException {
        showMessage("Bienvenido a la aplicación de gestión de tareas.");
        mostrarMenuPrincipal();
    }

    @Override
    public void showMessage(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public void showErrorMessage(String error) {
        System.err.println(error);
    }

    @Override
    public void end() {
        showMessage("Gracias por usar la aplicación. ¡Adiós!");
    
        scanner.close();
    }

    private void mostrarMenuPrincipal() throws RepositoryException {
        boolean ejecutando = true;
        while (ejecutando) {
            try {
                System.out.println("\nMenú Principal:");
                System.out.println("1. Operaciones CRUD");
                System.out.println("2. Exportar/Importar Tareas");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                
                
                int opcion = Integer.parseInt(scanner.nextLine()); 
    
                switch (opcion) {
                    case 1:
                        mostrarMenuCRUD();
                        break;
                    case 2:
                        mostrarMenuExportarImportar();
                        break;
                    case 3:
                        ejecutando = false;
                        controller.saveState();
                        end();
                        break;
                    default:
                        showErrorMessage("Opción no válida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                
                showErrorMessage("Entrada inválida. Por favor, ingrese un número.");
            }
        }
    }
    

    private void mostrarMenuCRUD() {
        System.out.println("\nMenú CRUD:");
        System.out.println("1. Crear tarea");
        System.out.println("2. Listar tareas");
        System.out.println("3. Modificar tarea");
        System.out.println("4. Eliminar tarea");
        System.out.println("5. Eliminar todas las tareas"); 
        System.out.println("6. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
    
        try {
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
                    eliminarTodasLasTareas();
                    break;
                case 6:
                    return; 
                default:
                    showErrorMessage("Opción no válida.");
            }
        } catch (Exception e) {
            showErrorMessage("Error: " + e.getMessage());
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

    try {
        
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la ruta para exportar el archivo CSV: ");
                    String csvPath = scanner.nextLine();
                    controller.exportarTareas("csv", csvPath);
                    break;
                case 2:
                    System.out.print("Ingrese la ruta para exportar el archivo JSON: ");
                    String jsonPath = scanner.nextLine();
                    controller.exportarTareas("json", jsonPath);
                    break;
                case 3:
                    System.out.print("Ingrese la ruta para importar desde el archivo CSV: ");
                    String importCsvPath = scanner.nextLine();
                    controller.importarTareas("csv", importCsvPath);
                    break;
                case 4:
                    System.out.print("Ingrese la ruta para importar desde el archivo JSON: ");
                    String importJsonPath = scanner.nextLine();
                    controller.importarTareas("json", importJsonPath);
                    break;
                case 5:
                    return; 
                default:
                    showErrorMessage("Opción no válida.");
            }
        
    } catch (Exception e) {
        showErrorMessage("Error durante la operación: " + e.getMessage());
    }
}


    private void crearTarea() {
        try {
            System.out.println("Ingrese el título de la tarea:");
            String titulo = scanner.nextLine();
            
            System.out.println("Ingrese la descripción de la tarea:");
            String contenido = scanner.nextLine();
            
            int prioridad;
            do {
                System.out.println("Ingrese la nueva prioridad (1-5):");
                prioridad = scanner.nextInt();
                scanner.nextLine();
                if (prioridad < 1 || prioridad > 5) {
                    showErrorMessage("Error: La prioridad debe estar entre 1 y 5. Intente nuevamente.");
                }
            } while (prioridad < 1 || prioridad > 5);
            
            System.out.println("Ingrese la duración estimada en minutos:");
            int duracion = scanner.nextInt();
            scanner.nextLine();
    
            
    
            Boolean completada = null;
            while (completada == null) {
                System.out.println("¿La tarea está completada? (true/false):");
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("true")) {
                    completada = true;
                } else if (input.equals("false")) {
                    completada = false;
                } else {
                    showErrorMessage("Error: Debe ingresar 'true' o 'false'. Intente nuevamente.");
                }
            }
    
            controller.crearTarea(titulo, contenido, prioridad, duracion, completada);
        } catch (Exception e) {
            showErrorMessage("Error al crear la tarea. Verifique los datos e inténtelo de nuevo.");
        }
    }

    private void listarTareas() {
        try {
            System.out.println("Identifier      Title                Date                 Content                        Priority       EstimatedDuration    Completed");
            controller.listarTareas();
        } catch (Exception e) {
            showErrorMessage("Error al listar las tareas: " + e.getMessage());
        }
    }
    

    private void modificarTarea() {
        try {
            System.out.println("Ingrese el ID de la tarea que desea modificar:");
            int id = scanner.nextInt();
            scanner.nextLine();
    
            System.out.println("Ingrese el nuevo título de la tarea:");
            String titulo = scanner.nextLine();
            
            System.out.println("Ingrese la nueva descripción de la tarea:");
            String contenido = scanner.nextLine();
            
            int prioridad;
            do {
                System.out.println("Ingrese la nueva prioridad (1-5):");
                prioridad = scanner.nextInt();
                scanner.nextLine();
                if (prioridad < 1 || prioridad > 5) {
                    showErrorMessage("Error: La prioridad debe estar entre 1 y 5. Intente nuevamente.");
                }
            } while (prioridad < 1 || prioridad > 5);
            
            System.out.println("Ingrese la nueva duración estimada en minutos:");
            int duracion = scanner.nextInt();
            scanner.nextLine();
        
    
            Boolean completada = null;
        while (completada == null) {
            System.out.println("¿La tarea está completada? (true/false):");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                completada = true;
            } else if (input.equals("false")) {
                completada = false;
            } else {
                System.out.println("Error: Debe ingresar 'true' o 'false'. Intente nuevamente.");
            }
        }
    
            controller.modificarTarea(id, titulo, contenido, prioridad, duracion, completada);
        } catch (Exception e) {
            showErrorMessage("Error al modificar la tarea. Verifique los datos e inténtelo de nuevo.");
        }
    }
    

    private void eliminarTarea() {
        System.out.println("Ingrese el ID de la tarea que desea eliminar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        controller.eliminarTarea(id);
    }

    private void eliminarTodasLasTareas() {
    System.out.print("¿Está seguro de que desea eliminar todas las tareas? (si/no): ");
    String confirmacion = scanner.nextLine().toLowerCase();

    if (confirmacion.equals("sí") || confirmacion.equals("si")) {
        controller.eliminarTodasLasTareas();
    } else {
        System.out.println("Operación cancelada.");
    }
}

}
