package modell;

import java.util.Locale;

public class Factura {
    private String concepto;
    private String fecha;
    private double descuento;
    private double importe;
    private String nif;
    private String nombreCliente;
    private String direccion;
    private double iva;

    

//metodo constructor
    public Factura(String concepto, String fecha, double descuento, double importe, String nif, String nombreCliente,
            String direccion, double iva) {
        this.concepto = concepto;
        this.fecha = fecha;
        this.descuento = descuento;
        this.importe = importe;
        this.nif = nif;
        this.nombreCliente = nombreCliente;
        this.direccion = direccion;
        this.iva = iva;
    }

//Factory method (Factura)

    public static Factura getFacturaFromString(String facturaString, String delimitador){
        try{
            String[] datos = facturaString.split("\t");
            if (datos.length == 8){
                String concepto = datos[0];
                double descuento= Double.parseDouble(datos[1]);
                String fecha= datos[2];
                double importe= Double.parseDouble(datos[3]);
                String nif = datos[4];
                String nombreCliente = datos[5];
                String direccion = datos[6];
                double iva= Double.parseDouble(datos[7]);



                return new Factura(concepto, fecha, descuento, importe, nif, nombreCliente, direccion, iva);
            }else{
                return null;
            }
        } catch (Exception e){
            return null;
        }
    }

    public String getFacturasAsDelimitedString(String delimitador){
        return String.format(Locale.ENGLISH, "%s" + delimitador + " %s " + delimitador + " %f " + delimitador + " %f " + delimitador + " %s " + delimitador +  " %s " + delimitador + " %s " + delimitador + " %f ", concepto, fecha, descuento, importe, nif, nombreCliente, direccion, iva);
    }


    public String getConcepto() {
        return concepto;
    }




    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }




    public String getFecha() {
        return fecha;
    }




    public void setFecha(String fecha) {
        this.fecha = fecha;
    }




    public double getDescuento() {
        return descuento;
    }




    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }




    public double getImporte() {
        return importe;
    }




    public void setImporte(double importe) {
        this.importe = importe;
    }




    public String getNif() {
        return nif;
    }




    public void setNif(String nif) {
        this.nif = nif;
    }




    public String getNombreCliente() {
        return nombreCliente;
    }




    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }




    public String getDireccion() {
        return direccion;
    }




    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }




    public double getIva() {
        return iva;
    }




    public void setIva(double iva) {
        this.iva = iva;
    }
}

    
