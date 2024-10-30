
public class App {


    public static void main(String[] args) throws Exception {
        
        CalculadoraModel model = new CalculadoraModel();
        CalculadoraView view = new CalculadoraView();
        ControllerCalculadora controller = new ControllerCalculadora();

        controller.run();

    }
}
