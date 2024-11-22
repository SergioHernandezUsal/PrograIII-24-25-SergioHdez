public class CocheModel {
    private int id;
    private String modelo;
    private int anio;
    private int cv;
    
    public CocheModel(int id, String modelo, int anio, int cv) {
        this.id = id;
        this.modelo = modelo;
        this.anio = anio;
        this.cv = cv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public String getEstadocomoString(){
        return "Coche [id=" + id + ", modelo=" + modelo + ", anio=" + anio+ ", cv="+cv+"]";
    }



    

    

}
