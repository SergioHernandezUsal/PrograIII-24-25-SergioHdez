public class CalculadoraController {
    private CalculadoraModel model;
    private CalculadoraView view;

    public CalculadoraController(CalculadoraModel model, CalculadoraView view) {
    this.model = model;
    this.view = view;
    }
    public void run() {
    boolean running = true;
    while (running) {
    view.showMenu();
    String option = view.getUserOption();
    switch (option) {
    case "1":
    int number1 = view.getNumberInput("Ingrese el primer número: ");
    int number2 = view.getNumberInput("Ingrese el segundo número: ");
    model.setNum1(number1);
    model.setNum2(number2);
    view.showMessage("Números leídos correctamente.");
    break;
    case "2":
    model.calcular();
    view.showMessage("Suma calculada correctamente.");
    break;
    case "3":
    view.showResult(model.getSum());
    break;
    case "q":
    running = false;
    view.showMessage("Saliendo del programa. ¡Adiós!");
    break;
    default:
    view.showMessage("Opción inválida, intente nuevamente.");
    break;
    }
    }
    }
    }
    