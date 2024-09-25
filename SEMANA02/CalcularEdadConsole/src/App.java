import java.io.Console;

public class App {
    public static void main(String[] args) throws Exception {
        Console console = System.console();

        System.err.println("Año de nacimiento: ");
        String nacimientoStr = console.readLine();
        int nacimiento= Integer.parseInt(nacimientoStr);
        System.err.println("Año actual: ");
        String actualStr = console.readLine();
        int actual= Integer.parseInt(actualStr);
        int edad = actual-nacimiento;
        System.out.printf("Tienes %d años", edad);
    }
}
