package quiniela.model;

public class Partido {

    private String equipoLocal;
    private String equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    private String resultadoAsString;




    
    public Partido(String equipoLocal, String equipoVisitante, int golesLocal, int golesVisitante, String resultadoAsString) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = -1;
        this.golesVisitante = -1;
        this.resultadoAsString = "";
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
    public String getResultadoAsString() {
        return resultadoAsString;
    }
    public void setResultadoAsString(String resultadoAsString) {
        this.resultadoAsString = resultadoAsString;
    }



    

}
