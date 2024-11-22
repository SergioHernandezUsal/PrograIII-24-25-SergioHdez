

import static com.coti.tools.Esdia.*;


public class CocheView {
 public void mostarmenu(){
    System.out.println("\n--- MENU CRUD ---");
    System.out.println("1. Agregar coche");
    System.out.println("2. Eliminar coche");
    System.out.println("3. Modificar coche");
    System.out.println("4. Listar coches");
    System.out.println("5. Salir");
 }

 public String getOpcion() {
    return readString("Seleccione una opción: ");
    }
    public int leerInt(String mensaje) {
        return readInt(mensaje);
    }

    public String leerString(String mensaje) {
        return readString(mensaje);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

}
