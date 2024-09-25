import java.util.Scanner;

import com.coti.tools.Esdia;

public class App {
    public static void main(String[] args) throws Exception {
        int i;
        float suma=0;
        float media=0;
         int num = Esdia.readInt("Introduzca un numero entero valido: ", 0, 999999);

         for(i=0; i<num;i++){
            float num1=Esdia.readFloat("Introduzca numero:");
            suma = suma+num1;
            
         }
         media = suma/num;
         System.err.printf("La media es %.2f", media);





        

    }
}
