



import controller.FacturaCrontroller;

import modell.FacturaModel;
import view.FacturaView;

public class App {
    public static void main(String[] args) throws Exception {
        FacturaModel model = new FacturaModel();
        FacturaView view=new FacturaView();
        FacturaCrontroller crontroller=new FacturaCrontroller(view, model);
        
        int facturasCorrectas= model.cargarFacturasDesdeArchivo();
        System.out.println("se han cargado " +  facturasCorrectas  + " facturas");

        crontroller.iniciar();
    }
}
