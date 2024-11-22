import static com.coti.tools.Esdia.*;

public class CalculadoraView {
    public void showMenu() {

        System.out.println("\nMenú:");
        System.out.println("1. Leer los números");
        System.out.println("2. Calcular la suma");
        System.out.println("3. Mostrar el resultado");
        System.out.println("q. Salir");
        
        }
        public int getNumberInput(String prompt) {
        return readInt(prompt);
        }
        public void showResult(int result) {
        System.out.println("El resultado de la suma es: " + result);
        }
        public String getUserOption() {
        return readString("Seleccione una opción: ");
        }
        public void showMessage(String message) {
        System.out.println(message);
        }
}
