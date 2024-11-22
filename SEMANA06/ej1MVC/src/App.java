public class App {
    public static void main(String[] args) throws Exception {
        CocheView vista = new CocheView();
        CocheController controlador = new CocheController(vista);

        controlador.run();
       
    }
}
