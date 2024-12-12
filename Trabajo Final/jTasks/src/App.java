import model.*;
import controller.Controller;
import view.ConsoleView;

public class App {
    public static void main(String[] args) {
        IRepository repository = null;

        try {
            
            if (args.length >= 2 && args[0].equals("--repository")) {
                if (args[1].equalsIgnoreCase("bin")) {
                    repository = new BinaryRepository();
                } else if (args[1].equalsIgnoreCase("notion") && args.length == 4) {
                    String apiKey = args[2];
                    String databaseId = args[3];
                    System.out.println("Importando tareas de Notion...");
                    repository = new NotionRepository(apiKey, databaseId);
                } else {
                    System.err.println("Uso incorrecto. Ejemplo:");
                    System.err.println("  java -jar app.jar --repository bin");
                    System.err.println("  java -jar app.jar --repository notion \"API_KEY\" \"DATABASE_ID\"");
                    System.exit(1);
                }
            } else {
                System.out.println("No se especificó repositorio. Usando 'bin' por defecto.");
                repository = new BinaryRepository();

            }

           
            Model model = new Model(repository);
            Controller controller = new Controller(model, null);
            ConsoleView view = new ConsoleView(controller);

            int importadas=controller.contarTareasImportadas();
            System.out.println("Tareas importadas: "+importadas);

            controller.setView(view);

            
            view.init();

            
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
