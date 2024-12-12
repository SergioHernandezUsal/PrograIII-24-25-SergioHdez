package model;

import java.util.ArrayList;

import notion.api.v1.model.databases.QueryResults;
import notion.api.v1.model.pages.Page;
import notion.api.v1.request.databases.QueryDatabaseRequest;

public class Model {
    private final IRepository repository;
    private ArrayList<Task> tasks;
    private IExporter exporter;

    public Model(IRepository repository) throws RepositoryException {
        this.repository = repository;

        // Cargar tareas desde el repositorio
        this.tasks = repository.getAllTasks();
    }

    public IRepository getRepository() {
        return this.repository;
    }

    public String saveState() throws RepositoryException {
        
        if (repository instanceof BinaryRepository) {
            return ((BinaryRepository) repository).saveToFile();
        }else if (repository instanceof NotionRepository){
            return "Tareas actualizdas en Notion";
        }else
                return null;
    }

    public void addTask(Task task) throws RepositoryException {
        repository.addTask(task);
    }

    public void removeTask(Task task) throws RepositoryException {
        repository.removeTask(task);
    }

    public void modifyTask(Task task) throws RepositoryException {
        repository.modifyTask(task);
    }

    public ArrayList<Task> getAllTasks() throws RepositoryException {
        return repository.getAllTasks();
    }

    public void setExporter(IExporter exporter) {
        this.exporter = exporter;
    }

    public void exportTasks(String filePath) throws ExporterException {
        if (exporter == null) {
            throw new ExporterException("No se ha configurado un exportador.");
        }
        try {
            ArrayList<Task> tasks = repository.getAllTasks();
            exporter.export(tasks, filePath);
        } catch (RepositoryException e) {
            throw new ExporterException("Error al obtener tareas del repositorio.", e);
        }
    }

    public void importTasks(String filePath) throws ExporterException {
        if (exporter == null) {
            throw new ExporterException("No se ha configurado un exportador.");
        }
        try {
            ArrayList<Task> importedTasks = exporter.importData(filePath);
            for (Task task : importedTasks) {
                repository.addTask(task);
            }
        } catch (RepositoryException e) {
            throw new ExporterException("Error al agregar tareas al repositorio.", e);
        }
    }

    public int generateIdentifier() {
        try {
            ArrayList<Task> tasks = repository.getAllTasks();
            if (tasks.isEmpty()) {
                return 1;
            }

            int maxId = tasks.stream().mapToInt(Task::getIdentifier).max().orElse(0);
            return maxId + 1;
        } catch (RepositoryException e) {
            throw new RuntimeException("Error al generar un identificador único: " + e.getMessage(), e);
        }
    }

    public Task getTaskById(int id) {
        try {
            ArrayList<Task> tasks = repository.getAllTasks();
            return tasks.stream()
                        .filter(task -> task.getIdentifier() == id)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("No se encontró la tarea con ID: " + id));
        } catch (RepositoryException e) {
            throw new RuntimeException("Error al obtener la tarea con ID: " + id + ". " + e.getMessage(), e);
        }
    }

    public void removeAllTasks() throws RepositoryException {
        if (repository instanceof NotionRepository) {
            NotionRepository notionRepo = (NotionRepository) repository;
            try {
                QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(notionRepo.getDatabaseId());
                QueryResults results = notionRepo.getClient().queryDatabase(queryRequest);

                for (Page page : results.getResults()) {
                    String pageId = page.getId();
                    try {
                        notionRepo.deletePageById(pageId);
                    } catch (Exception e) {
                        throw new RepositoryException("Error al eliminar la tarea con ID: " + pageId, e);
                    }
                }
            } catch (Exception e) {
                throw new RepositoryException("Error al eliminar todas las tareas de Notion.", e);
            }
        } else {
            ArrayList<Task> tasks = repository.getAllTasks();
            for (Task task : tasks) {
                repository.removeTask(task);
            }
        }
    }
}

       

    
    



