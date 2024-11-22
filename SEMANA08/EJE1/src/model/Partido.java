package model;

public class Partido {

    public static char[] getCabeceraTabla;
    String equipoLocal;
    String equipoVisitante;
    int golesLocal;
    int golesVisitante;

    
    public Partido(String equipoLocal, String equipoVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        golesLocal = -1;
        golesVisitante = -1;

    }

    // LOGICA DE NEGOCIO
    public static Partido createPartidoFromDelimitedString(String delimitedString, String delimiter){

        // Uso de "split"
        String[] trozos = delimitedString.split(delimiter);

        if(trozos.length == 2){
            return new Partido(trozos[0], trozos[1]);
        }else{
            return null;
        }
    }

    public static String getCabeceraTabla() {
        return String.format("| %20.20s | %20.20s | %20.20s |", "Local", "Visitante", "Resultado");
    }

    public String asTableRow() {
        return String.format("| %20.20s | %20.20s | %20.20s |", this.equipoLocal, this.equipoVisitante, this.resultadoComString());
    }

    public String resultadoComString(){
        if(golesLocal>golesVisitante){
            return "1";
        }else if(golesLocal<golesVisitante){
            return "2";
        }else{
            return "X";
        }

    }





    public String getEquipoLocal() {
        return equipoLocal;
    }


    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }


    public String getEquipoVisitante() {
        return equipoVisitante;
    }


    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }


    public int getGolesLocal() {
        return golesLocal;
    }


    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }


    public int getGolesVisitante() {
        return golesVisitante;
    }


    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    

    
    

    


}
