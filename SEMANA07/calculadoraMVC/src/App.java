
public class App {


    public static void main(String[] args) throws Exception {
        
        CalculadoraModel model = new CalculadoraModel();
        CalculadoraView view = new CalculadoraView();
        CalculadoraController controller = new CalculadoraController(model,view);

        controller.run();

    }
}
