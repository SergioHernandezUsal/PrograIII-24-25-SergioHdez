public class App {
    public static void main(String[] args) {
        
        if (args.length != 3) {
            System.out.println("Error: Debes proporcionar 3 parámetros: <nombre> <peso> <altura>");
            
            return;
        }

        try {
         
            Usuario usuario = Usuario.crearDesdeParametros(args);

            
            System.out.printf("%-15s %-10s %-10s %-10s%n", "Nombre", "Peso", "Altura", "IMC");
            System.out.printf("%-15s %-10s %-10s %-10s%n", "---------------", "----------", "----------", "----------");
            usuario.mostrarInformacion();
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}