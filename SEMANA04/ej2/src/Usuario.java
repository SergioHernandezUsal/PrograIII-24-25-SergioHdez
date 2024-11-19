public class Usuario {
    private String nombre;
    private double peso;
    private double altura;


    public Usuario(String nombre, double peso, double altura) {
        this.nombre = nombre;
        this.peso = peso;
        this.altura = altura;
    }


    public double calcularIMC() {
        return (peso / (altura * altura));
    }

    public void mostrarInformacion() {
        System.out.printf("%-15s %-10.2f %-10.2f %-10.2f%n", nombre, peso, altura, calcularIMC());
    }

    public static Usuario crearDesdeParametros(String[] parametros) {
        try {
            String nombre = parametros[0];
            double peso = Double.parseDouble(parametros[1]);
            double altura = Double.parseDouble(parametros[2]);
            return new Usuario(nombre, peso, altura);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El peso y la altura deben ser valores numéricos.");
        }
    }

}
    


