

import java.util.List;

import modell.Factura;
import modell.FacturaModel;

public class App {
    public static void main(String[] args) throws Exception {
        FacturaModel model = new FacturaModel();
        int facturasCorrectas= model.cargarFacturasDesdeArchivo();
        System.out.println("se han cargado " +  facturasCorrectas  + " facturas");

        List<Factura> facturas = model.getFacturas();
        for (Factura factura: facturas){
            System.out.println(factura.getFacturasAsDelimitedString(";"));
        }
    }
}
