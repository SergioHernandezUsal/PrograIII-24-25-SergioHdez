import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanRef = new Scanner(System.in);



        System.out.println("Año de nacimiento: ");
        int nacimiento = scanRef.nextInt();
        System.out.println("Año actual: ");
        int actual = scanRef.nextInt();
        int edad = actual-nacimiento;
        System.err.printf("Tienes %d años", edad);
        scanRef.close();
    }
}
