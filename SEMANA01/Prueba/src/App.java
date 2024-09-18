import java.io.Console;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
       

        /*Scanner scanRef = new Scanner(System.in);



        System.err.println("Escriba su nombre: ");
        String nombre = scanRef.nextLine();
        System.err.println("Escriba su edad: ");
        int edad = scanRef.nextInt();

        System.out.printf("El nombre es %s y tienes %d años", nombre, edad);

        scanRef.close();*/


        Console console = System.console();

        System.err.println("Escriba su nombre");
        String nombre = console.readLine();
        System.err.println("Escriba su contraseña");
        char[] password = console.readPassword();

        System.out.printf("Mi nombre es %s y mi contraseña tiene un tamaño %d", nombre, password.length);
    }
}
