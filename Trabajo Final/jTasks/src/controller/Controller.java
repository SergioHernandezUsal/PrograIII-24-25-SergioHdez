package controller;

import model.*;
import view.BaseView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    private Model model;
    private BaseView view;

    public Controller(Model model, BaseView view) {
        this.model = model;
        this.view = view;
    }

    public void setView(BaseView view) {
        this.view = view;
    }

    public void crearTarea(String titulo, String contenido, int prioridad, int duracion, boolean completada) {
        try {
            Task task = new Task(model.generateIdentifier(), titulo, new Date(), contenido, prioridad, duracion, completada);
            model.addTask(task);
            view.showMessage("Tarea creada correctamente.");
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al crear la tarea: " + e.getMessage());
        }
    }

    public void listarTareas() {
        try {
            List<Task> tasks = model.getAllTasks();
            tasks.forEach(task -> view.showMessage(task.toString()));
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al listar las tareas: " + e.getMessage());
        }
    }

    public void modificarTarea(int id, String titulo, String contenido, int prioridad, int duracion, boolean completada) {
        try {
            Task task = model.getTaskById(id);
            task.setTaskTitle(titulo);
            task.setContent(contenido);
            task.setPriority(prioridad);
            task.setEstimatedDuration(duracion);
            task.setCompleted(completada);
            model.modifyTask(task);
            view.showMessage("Tarea modificada correctamente.");
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al modificar la tarea: " + e.getMessage());
        }
    }

    public void eliminarTarea(int id) {
        try {
            Task task = model.getTaskById(id);
            model.removeTask(task);
            view.showMessage("Tarea eliminada correctamente.");
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al eliminar la tarea: " + e.getMessage());
        }
    }

    public void exportarTareas(String tipo, String ruta) {
        try {
            model.setExporter(ExporterFactory.getExporter(tipo));
            model.exportTasks(ruta);
            view.showMessage("Tareas exportadas exitosamente a: " + ruta);
        } catch (ExporterException e) {
            view.showErrorMessage("Error al exportar tareas: " + e.getMessage());
        }
    }
    
    public void importarTareas(String tipo, String ruta) {
        try {
            model.setExporter(ExporterFactory.getExporter(tipo));
            model.importTasks(ruta);
            view.showMessage("Tareas importadas exitosamente desde: " + ruta);
        } catch (ExporterException e) {
            view.showErrorMessage("Error al importar tareas: " + e.getMessage());
        }
    }
    public void eliminarTodasLasTareas() {
        try {
            model.removeAllTasks();
            view.showMessage("Todas las tareas han sido eliminadas con éxito.");
        } catch (RepositoryException e) {
            view.showErrorMessage("Error al eliminar las tareas: " + e.getMessage());
        }
    }
    public int contarTareasImportadas() {
    try {
        
        ArrayList<Task> tasks = model.getAllTasks();
        return tasks.size(); 
    } catch (RepositoryException e) {
        
        view.showErrorMessage("Error al contar las tareas importadas: " + e.getMessage());
        return 0; 
    }
    }
    public void saveState() throws RepositoryException {
        String save=model.saveState();
        view.showMessage(save);
    }

    

   
    
}
