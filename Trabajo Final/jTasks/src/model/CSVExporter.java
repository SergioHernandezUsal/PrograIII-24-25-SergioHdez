package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class CSVExporter implements IExporter {
    
    private void verifyFileExists(String path) throws ExporterException {
        File file = new File(path);
        if (!file.exists()) {
            throw new ExporterException("El archivo especificado no existe: " + path);
        }
    }
    @Override
public void export(ArrayList<Task> tasks, String path) throws ExporterException {
   
    File file = new File(path);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
       
        writer.write("ID,Titulo,Fecha,Contenido,Prioridad,Duracion,Completado\n");
        
        
        for (Task task : tasks) {
            writer.write(String.format(
                "%d,%s,%s,%s,%d,%d,%b\n",
                task.getIdentifier(),
                task.getTaskTitle(),
                new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").format(task.getDate()),
                task.getContent(),
                task.getPriority(),
                task.getEstimatedDuration(),
                task.isCompleted()
            ));
        }
    } catch (IOException e) {
        throw new ExporterException("Error al exportar las tareas a CSV.", e);
    }
}


@Override
public ArrayList<Task> importData(String path) throws ExporterException {
    verifyFileExists(path);

    ArrayList<Task> tasks = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.forLanguageTag("es")); // Manejo de fechas en español

    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
        String line = reader.readLine(); // Leer y descartar la cabecera

        while ((line = reader.readLine()) != null) {
            try {
                String[] datos = line.split(",");
                Task task = new Task(
                    Integer.parseInt(datos[0]),   // Identifier
                    datos[1],                     // Title
                    dateFormat.parse(datos[2]),   // Date
                    datos[3],                     // Content
                    Integer.parseInt(datos[4]),   // Priority
                    Integer.parseInt(datos[5]),   // EstimatedDuration
                    Boolean.parseBoolean(datos[6]) // Completed
                );
                tasks.add(task);
            } catch (ParseException e) {
                throw new ExporterException("Error en la fecha: " + line, e);
            }
        }
    } catch (IOException e) {
        throw new ExporterException("Error en la lectura del archivo", e);
    }

    return tasks;
}


}    