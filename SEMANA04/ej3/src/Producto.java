

public class Producto {

    private String nombre;
    private double precioSinIVA;
    private static final double IVA = 1.21;

    
    public Producto(String nombre, double precioSinIVA) {
        this.nombre = nombre;
        this.precioSinIVA = precioSinIVA;
    }


    public String getNombre() {
        return nombre;
    }


    public double getPrecioConIVA(){
        return precioSinIVA * IVA;
    }

    public double getPrecioTotal(double cantidadkg){
        return getPrecioConIVA()*cantidadkg;
    }


}
