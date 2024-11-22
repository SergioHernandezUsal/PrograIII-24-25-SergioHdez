package modell;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.coti.tools.Rutas;



public class FacturaModel {
    private List<Factura> facturas;
    private Path rutaArchivo=Rutas.pathToFileOnDesktop("facturas.tsv");

    public FacturaModel(){
        this.facturas = new ArrayList<>();

    }

    public int cargarFacturasDesdeArchivo(){
        // leer todas las lineas del fichero
        List<String> lineas = null;
        System.out.println("Cargar facturas desde fichero");
        try {
            lineas = Files.readAllLines(rutaArchivo, StandardCharsets.UTF_8);
            int lineasCorrectas = 0;
            for(String linea: lineas){
                Factura factura = Factura.getFacturaFromString(linea, "\t");
                if (factura != null){
                    facturas.add(factura);
                    lineasCorrectas++;
                }
                

            }
            return lineasCorrectas;
        } catch (IOException e) {
            return -1;
            // TODO: handle exception
        }
                

    }

    public List<Factura> getFacturas() {
            return facturas;
        
        
    }

    public List<String> getclientesmin(double valorFacturaMin){
        List<String> clientes=new ArrayList<>();
        for (Factura factura: facturas){
            if(factura.valorFactura()>valorFacturaMin){
                clientes.add(factura.getNombreCliente());
            }
        }
        return clientes;
    
    }

    public ArrayList<String> listadoFacturas(){
        ArrayList<String> listado=new ArrayList<>();
        for (Factura factura: facturas){
            listado.add(factura.getFacturasAsDelimitedString("\t"));

        }
        return listado;
        

    }

    public String exportarCsv(String archivo){
        try(PrintWriter writer=new PrintWriter(archivo)){
            writer.println("Concepto,Fecha,Descuento,Importe,NIF,NombreCliente,Direccion,IVA");
            for (Factura factura : facturas) {
                writer.println(factura.toCSVString());
            }
            return "ÉXITO";
        }catch(IOException e){
            return "ERROR";
        }

    }

   
}
