package datos;

public class Persona {
    private String nombre;
    private double peso;
    private double altura;

    public Persona() {
        
    }

    public Persona(String nombre) {
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public double getAltura() {
        return altura;
    }
    public double getPeso() {
        return peso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double calcularIMC(){
        if (peso <= 0 || altura <= 0) {
            System.err.println("Error: El peso y la altura deben ser mayores que cero para calcular el IMC.");
            return 0;
        }
        double IMC = (peso/(altura*altura));
        return IMC;
                    
    }
}

