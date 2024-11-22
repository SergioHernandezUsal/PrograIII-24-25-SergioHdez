package controller;

import java.util.ArrayList;
import java.util.List;


import modell.FacturaModel;
import view.FacturaView;

public class FacturaCrontroller {
    
    private FacturaView vista;
    private FacturaModel modelo;

    public FacturaCrontroller(FacturaView vista, FacturaModel modelo) {
        this.modelo=modelo;
        this.vista = vista;

        this.vista.setControlador(this);
    }
    public void iniciar() {

        vista.menu();
    }

    public List<String> getclientesmin(double valorFacturaMin){
        return modelo.getclientesmin(valorFacturaMin);
    }

    public ArrayList<String> listadofacturas(){
        return modelo.listadoFacturas();
    }

    public String exportarCsv(String archivo){
        return modelo.exportarCsv(archivo);
    }


    
}
