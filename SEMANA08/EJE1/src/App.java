

import view.View;
import controller.Controller;
import model.QuinielaModel;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Model
        QuinielaModel m = new QuinielaModel();
        // View
        View v = new View();
        // Controlador
        Controller c = new Controller(v, m);

        c.init();

    }
}