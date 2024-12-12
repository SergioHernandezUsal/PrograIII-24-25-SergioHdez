package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


public class JSONExporter implements IExporter {

    private void verifyFileExists(String path) throws ExporterException {
        File file = new File(path);
        if (!file.exists()) {
            throw new ExporterException("El archivo especificado no existe: " + path);
        }
    }
    
    @Override
    public void export(ArrayList<Task> tasks, String path) throws ExporterException {
        
        File file = new File(path);
        try (Writer writer = new FileWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
            gson.toJson(tasks, writer); 
        } catch (IOException e) {
            throw new ExporterException("Error al exportar las tareas a JSON.", e);
        }
    }
    

@Override
public ArrayList<Task> importData(String path) throws ExporterException {
    verifyFileExists(path); 
    try (Reader reader = new FileReader(path)) {
        Gson gson = new Gson();
        Task[] tasksArray = gson.fromJson(reader, Task[].class);
        return new ArrayList<>(Arrays.asList(tasksArray));
    } catch (IOException e) {
        throw new ExporterException("Error al importar tareas desde JSON.", e);
    }
}
}    
