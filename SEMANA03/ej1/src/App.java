import java.util.Scanner;
import datos.Persona;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanRef = new Scanner(System.in);
        Persona[] personas = new Persona[3];

        for (int i = 0; i < personas.length; i++) {
            personas[i] = new Persona();
            System.out.printf("Ingrese el nombre de la persona %d: ", i + 1);
            
            String nombre = scanRef.nextLine();
            personas[i].setNombre(nombre);
            System.out.printf("Ingrese el peso de la persona %d (en kg): ", i + 1);
            
            double peso = scanRef.nextDouble();
            personas[i].setPeso(peso);
            System.out.printf("Ingrese la altura de la persona %d (en m): ", i + 1);
            
            double altura = scanRef.nextDouble();
            personas[i].setAltura(altura);
            scanRef.nextLine();
        }

        Persona masAlto = personas[0];
        Persona masPesado = personas[0];

        for (int i = 0; i < personas.length; i++) {
         
            if (personas[i].getAltura() > masAlto.getAltura()) {
                masAlto = personas[i];
            }
          
            if (personas[i].getPeso() > masPesado.getPeso()) {
                masPesado = personas[i];
            }

            double imc= personas[i].calcularIMC();
            System.out.println("El IMC de la persona " + (i+1) + " es " +imc);
        }

        
        System.out.println("La persona más alta es: " + masAlto.getNombre() + " con una altura de " + masAlto.getAltura() + " metros.");
        System.out.println("La persona que más pesa es: " + masPesado.getNombre() + " con un peso de " + masPesado.getPeso() + " kilogramos.");
        scanRef.close();
    }import java.util.Scanner;
    import datos.Persona;
    
    public class App {
        public static void main(String[] args) throws Exception {
            Scanner scanRef = new Scanner(System.in);
            Persona[] personas = new Persona[3];
    
            for (int i = 0; i < personas.length; i++) {
                personas[i] = new Persona();
                System.out.printf("Ingrese el nombre de la persona %d: ", i + 1);
                
                String nombre = scanRef.nextLine();
                personas[i].setNombre(nombre);
                System.out.printf("Ingrese el peso de la persona %d (en kg): ", i + 1);
                
                double peso = scanRef.nextDouble();
                personas[i].setPeso(peso);
                System.out.printf("Ingrese la altura de la persona %d (en m): ", i + 1);
                
                double altura = scanRef.nextDouble();
                personas[i].setAltura(altura);
                scanRef.nextLine();
            }
    
            Persona masAlto = personas[0];
            Persona masPesado = personas[0];
    
            for (int i = 0; i < personas.length; i++) {
             
                if (personas[i].getAltura() > masAlto.getAltura()) {
                    masAlto = personas[i];
                }
              
                if (personas[i].getPeso() > masPesado.getPeso()) {
                    masPesado = personas[i];
                }
    
                double imc= personas[i].calcularIMC();
                System.out.println("El IMC de la persona " + (i+1) + " es " +imc);
            }
    
            
            System.out.println("La persona más alta es: " + masAlto.getNombre() + " con una altura de " + masAlto.getAltura() + " metros.");
            System.out.println("La persona que más pesa es: " + masPesado.getNombre() + " con un peso de " + masPesado.getPeso() + " kilogramos.");
            scanRef.close();
        }
    
}
