import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanRef = new Scanner(System.in);



        System.err.println("Escriba el primer numero: ");
        int num1 = scanRef.nextInt();
        System.err.println("Escriba el segundo numero: ");
        int num2 = scanRef.nextInt();
        System.err.println("Escriba el tercero numero: ");
        int num3 = scanRef.nextInt();

        if(num1 == num2 && num2 == num3){
            System.out.printf("Los tres números son iguales: %d ", num1);
        }else if(num1>=num2 && num1>=num3){
            System.out.printf("El numero mayor es el primero: %d ", num1);
        }else if(num2>=num1 && num2>=num3){
            System.out.printf("El numero mayor es el segundo: %d ", num2);
        }else if(num3>=num1 && num3>=num1){
            System.out.printf("El numero mayor es el tercero: %d ", num3);
}
scanRef.close();
}
}