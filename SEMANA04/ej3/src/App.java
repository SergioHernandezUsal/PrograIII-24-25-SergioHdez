import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        System.out.println("Ingrese el precio sin IVA del kg de peras:");
        double precioPeras = scanner.nextDouble();
        System.out.println("Ingrese el precio sin IVA del kg de manzanas:");
        double precioManzanas = scanner.nextDouble();

        Producto[] productos = {
            new Producto("Peras", precioPeras),
            new Producto("Manzanas", precioManzanas)
        };

        int numeroCliente = 1;
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nAtendiendo al Cliente nº" + numeroCliente);
            double totalFactura = 0;

           
            double[] cantidades = new double[productos.length];
            for (int i = 0; i < productos.length; i++) {
                System.out.println("Ingrese la cantidad de " + productos[i].getNombre() + " en Kg:");
                cantidades[i] = scanner.nextDouble();
            }

          
            System.out.println("\nFactura del Cliente #" + numeroCliente);
            System.out.printf("%-15s %-10s %-15s %-15s%n", "Producto", "Cantidad", "Precio/Kg (IVA)", "Total");
            System.out.printf("%-15s %-10s %-15s %-15s%n", "---------------", "----------", "---------------", "---------------");

            for (int i = 0; i < productos.length; i++) {
                double precioPorKgConIVA = productos[i].getPrecioConIVA();
                double precioTotalPorProducto = productos[i].getPrecioTotal(cantidades[i]);
                totalFactura += precioTotalPorProducto;

                System.out.printf("%-15s %-10.2f %-15.2f %-15.2f%n",
                    productos[i].getNombre(),
                    cantidades[i],
                    precioPorKgConIVA,
                    precioTotalPorProducto
                );
            }

            System.out.printf("\nTOTAL A PAGAR: %.2f€%n", totalFactura);

            System.out.println("\n Si desea salir pulse n, para continuar pulse cualquier otra tecla: ");
            scanner.nextLine(); 
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                continuar = false;
            } else {
                numeroCliente++;
            }
        }

        System.out.println("Fin de la jornada.");
        scanner.close();
    }
}



    

