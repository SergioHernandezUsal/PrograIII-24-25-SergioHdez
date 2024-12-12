package model;

import java.io.*;
import java.util.ArrayList;

public class BinaryRepository implements IRepository {
    private final String filePath;
    private ArrayList<Task> tasks;

    @SuppressWarnings("unchecked")
    public BinaryRepository()throws RepositoryException {
        this.filePath = System.getProperty("user.home") + File.separator + "tasks.bin";
        this.tasks = new ArrayList<>();

       
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            tasks = (ArrayList<Task>) ois.readObject();
            
        } catch (FileNotFoundException e) {
            throw new RepositoryException("Archivo binario no encontrado. Comenzando con una lista vacía.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException("Error al cargar el archivo binario: " + e.getMessage());
        }
    }

   

    private void saveTasks() throws RepositoryException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            throw new RepositoryException("Error guardando las tareas", e);
        }
    }

    @Override
    public Task addTask(Task t) throws RepositoryException {
        if (tasks.stream().anyMatch(task -> task.getIdentifier() == t.getIdentifier())) {
            throw new RepositoryException("Ya existe una tarea con ese id");
        }
        saveTasks();
        tasks.add(t);
        
        return t;
    }

    @Override
    public void removeTask(Task t) throws RepositoryException {
        if (!tasks.remove(t)) {
            throw new RepositoryException("Tarea no encontrada");
        }
        saveTasks();
    }

    @Override
    public void modifyTask(Task t) throws RepositoryException {
        Task existingTask = tasks.stream()
                                 .filter(task -> task.getIdentifier() == t.getIdentifier())
                                 .findFirst()
                                 .orElseThrow(() -> new RepositoryException("Task not found."));
        tasks.remove(existingTask);
        tasks.add(t);
        saveTasks();
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public String saveToFile() throws RepositoryException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
            return ("Tareas guardadas exitosamente en " + filePath);
        } catch (IOException e) {
            throw new RepositoryException("Error al guardar las tareas en " + filePath + ": " + e.getMessage());
        }
    }
}