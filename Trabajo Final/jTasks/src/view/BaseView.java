package view;

import controller.Controller;

public abstract class BaseView {
    protected Controller controller;

   
    public BaseView(Controller controller) {
        this.controller = controller;
    }

    
    public abstract void init(); 
    public abstract void showMessage(String mensaje); 
    public abstract void showErrorMessage(String error); 
    public abstract void end(); 
}

