import com.coti.tools.Esdia;
import datos.Persona;

public class App {
    public static void main(String[] args) throws Exception {
        Persona[] personas = new Persona[3];

        for (int i = 0; i < personas.length; i++) {
            personas[i] = new Persona();
            String prompt = String.format("Ingrese el nombre de la persona %d: ", i + 1);
            String nombre = Esdia.readString(prompt);
            personas[i].setNombre(nombre);

            String promptPeso = String.format("Ingrese el peso de la persona %d (en kg): ", i + 1);
            double peso = Esdia.readDouble(promptPeso);
            personas[i].setPeso(peso);

            String promptAltura = String.format("Ingrese la altura de la persona %d (en m): ", i + 1);
            double altura = Esdia.readDouble(promptAltura);
            personas[i].setAltura(altura);
        }

        Persona masAlto = personas[0];
        Persona masPesado = personas[0];

        for (int i = 1; i < personas.length; i++) {
         
            if (personas[i].getAltura() > masAlto.getAltura()) {
                masAlto = personas[i];
            }
          
            if (personas[i].getPeso() > masPesado.getPeso()) {
                masPesado = personas[i];
            }
        }

        
        System.out.println("La persona más alta es: " + masAlto.getNombre() + " con una altura de " + masAlto.getAltura() + " metros.");
        System.out.println("La persona que más pesa es: " + masPesado.getNombre() + " con un peso de " + masPesado.getPeso() + " kilogramos.");
    }
}

