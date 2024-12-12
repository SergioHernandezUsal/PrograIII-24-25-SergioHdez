package view;

import controller.Controller;
import model.RepositoryException;

public abstract class BaseView {
    protected Controller controller;

   
    public BaseView(Controller controller) {
        this.controller = controller;
    }

    
    public abstract void init() throws RepositoryException; 
    public abstract void showMessage(String mensaje); 
    public abstract void showErrorMessage(String error); 
    public abstract void end(); 
}

