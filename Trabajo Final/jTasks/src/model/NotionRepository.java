package model;

import notion.api.v1.NotionClient;
import notion.api.v1.http.OkHttp5Client;
import notion.api.v1.logging.Slf4jLogger;
import notion.api.v1.model.databases.QueryResults;
import notion.api.v1.model.pages.Page;
import notion.api.v1.model.pages.PageParent;
import notion.api.v1.model.pages.PageProperty;
import notion.api.v1.model.pages.PageProperty.RichText;
import notion.api.v1.model.pages.PageProperty.RichText.Text;
import notion.api.v1.request.databases.QueryDatabaseRequest;
import notion.api.v1.request.pages.CreatePageRequest;
import notion.api.v1.request.pages.UpdatePageRequest;

import java.text.SimpleDateFormat;
import java.util.*;


public class NotionRepository implements IRepository {
    private final NotionClient client;
    private final String databaseId;
    private final String identifierColumnName = "Identifier";

    public NotionRepository(String apiToken, String databaseId) throws RepositoryException {
        this.client = new NotionClient(apiToken);
        this.client.setHttpClient(new OkHttp5Client(60000, 60000, 60000));
        this.client.setLogger(new Slf4jLogger());
        this.databaseId = databaseId;
        try {
            ArrayList<Task> importedTasks = new ArrayList<>();
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults results = client.queryDatabase(queryRequest);
    
            for (Page page : results.getResults()) {
                Task task = mapPageToTask(page.getId(), page.getProperties());
                if (task != null) {
                    importedTasks.add(task);
                }
            }
            
        } catch (Exception e) {
            throw new RepositoryException("Error al importar tareas desde Notion: " + e.getMessage());
        }
    }

    @Override
    public Task addTask(Task task) throws RepositoryException {
        try {
            Map<String, PageProperty> properties = Map.of(
                "Identifier", createTitleProperty(String.valueOf(task.getIdentifier())),
                "Title", createRichTextProperty(task.getTaskTitle()),
                "Date", createDateProperty(new SimpleDateFormat("yyyy-MM-dd").format(task.getDate())),
                "Content", createRichTextProperty(task.getContent()),
                "Priority", createNumberProperty(task.getPriority()),
                "EstimatedDuration", createNumberProperty(task.getEstimatedDuration()),
                "Completed", createCheckboxProperty(task.isCompleted())
            );

            PageParent parent = PageParent.database(databaseId);
            CreatePageRequest request = new CreatePageRequest(parent, properties);
            @SuppressWarnings("unused")
            Page response = client.createPage(request);
            return task;
        } catch (Exception e) {
            throw new RepositoryException("Error al subir las tareas a Notion", e);
        }
    }

    @Override
    public void removeTask(Task task) throws RepositoryException {
        try {
            String pageId = findPageIdByIdentifier(String.valueOf(task.getIdentifier()));
            if (pageId == null) {
                throw new RepositoryException("Tarea con identificador " + task.getIdentifier() + " no encontrada");
            }
            UpdatePageRequest updateRequest = new UpdatePageRequest(pageId, Collections.emptyMap(), true);
            client.updatePage(updateRequest);
        } catch (Exception e) {
            throw new RepositoryException("Error al eliminar la tarea de Notion", e);
        }
    }

    @Override
    public void modifyTask(Task task) throws RepositoryException {
        try {
            String pageId = findPageIdByIdentifier(String.valueOf(task.getIdentifier()));
            if (pageId == null) {
                throw new RepositoryException("Tarea con identificador " + task.getIdentifier() + " no encontrada");
            }

            Map<String, PageProperty> updatedProperties = Map.of(
                "Title", createRichTextProperty(task.getTaskTitle()),
                "Date", createDateProperty(new SimpleDateFormat("yyyy-MM-dd").format(task.getDate())),
                "Content", createRichTextProperty(task.getContent()),
                "Priority", createNumberProperty(task.getPriority()),
                "EstimatedDuration", createNumberProperty(task.getEstimatedDuration()),
                "Completed", createCheckboxProperty(task.isCompleted())
            );

            UpdatePageRequest updateRequest = new UpdatePageRequest(pageId, updatedProperties);
            client.updatePage(updateRequest);
        } catch (Exception e) {
            throw new RepositoryException("Error al modificar la tarea de Notion", e);
        }
    }

    @Override
    public ArrayList<Task> getAllTasks() throws RepositoryException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults results = client.queryDatabase(queryRequest);
    
            for (Page page : results.getResults()) {
                Task task = mapPageToTask(page.getId(), page.getProperties());
                if (task != null) {
                    tasks.add(task); 
                }
            }
        } catch (Exception e) {
            throw new RepositoryException("Error al cargar las tareas de Notion.", e);
        }
        return tasks;
    }
    

    private String findPageIdByIdentifier(String identifier) {
        try {
            QueryDatabaseRequest queryRequest = new QueryDatabaseRequest(databaseId);
            QueryResults results = client.queryDatabase(queryRequest);

            for (Page page : results.getResults()) {
                Map<String, PageProperty> properties = page.getProperties();
                if (properties.containsKey(identifierColumnName) &&
                        properties.get(identifierColumnName).getTitle().get(0).getText().getContent().equals(identifier)) {
                    return page.getId();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Task mapPageToTask(String pageId, Map<String, PageProperty> properties) {
        try {
            if (properties == null || properties.isEmpty()) {
                
                return null; 
            }
    
            
            PageProperty identifierProperty = properties.get("Identifier");
            if (identifierProperty == null || identifierProperty.getTitle() == null || identifierProperty.getTitle().isEmpty()) {
                
                return null; 
            }
            int identifier = Integer.parseInt(identifierProperty.getTitle().get(0).getText().getContent());
    
           
            String title = getSafeText(properties.get("Title"), "Título no disponible");
            String content = getSafeText(properties.get("Content"), "Contenido no disponible");
            String dateString = getDateFromProperty(properties.get("Date"));
            Date date = dateString != null ? new SimpleDateFormat("yyyy-MM-dd").parse(dateString) : new Date();
            int priority = getNumberFromProperty(properties.get("Priority"), 1); // Prioridad por defecto: 1
            int estimatedDuration = getNumberFromProperty(properties.get("EstimatedDuration"), 0);
            boolean completed = getCheckboxFromProperty(properties.get("Completed"));
    
            return new Task(identifier, title, date, content, priority, estimatedDuration, completed);
        } catch (Exception e) {
            
            return null; 
        }
    }
    
    
    private String getSafeText(PageProperty property, String defaultValue) {
        if (property != null && property.getRichText() != null && !property.getRichText().isEmpty()) {
            return property.getRichText().get(0).getText().getContent();
        }
        return defaultValue;
    }
    
    private String getDateFromProperty(PageProperty property) {
        return (property != null && property.getDate() != null) ? property.getDate().getStart() : null;
    }
    
    private int getNumberFromProperty(PageProperty property, int defaultValue) {
        return (property != null && property.getNumber() != null) ? property.getNumber().intValue() : defaultValue;
    }
    
    private boolean getCheckboxFromProperty(PageProperty property) {
        return (property != null && property.getCheckbox() != null) ? property.getCheckbox() : false;
    }
    

    private PageProperty createTitleProperty(String title) {
        RichText richText = new RichText();
        richText.setText(new Text(title));
        PageProperty property = new PageProperty();
        property.setTitle(Collections.singletonList(richText));
        return property;
    }

    private PageProperty createRichTextProperty(String text) {
        RichText richText = new RichText();
        richText.setText(new Text(text));
        PageProperty property = new PageProperty();
        property.setRichText(Collections.singletonList(richText));
        return property;
    }

    private PageProperty createNumberProperty(int number) {
        PageProperty property = new PageProperty();
        property.setNumber(number);
        return property;
    }

    private PageProperty createDateProperty(String date) {
        PageProperty property = new PageProperty();
        PageProperty.Date dateProperty = new PageProperty.Date();
        dateProperty.setStart(date);
        property.setDate(dateProperty);
        return property;
    }

    private PageProperty createCheckboxProperty(boolean checked) {
        PageProperty property = new PageProperty();
        property.setCheckbox(checked);
        return property;
    }

    public void deletePageById(String pageId) throws RepositoryException {
        try {
            UpdatePageRequest updateRequest = new UpdatePageRequest(pageId, Collections.emptyMap(), true);
            client.updatePage(updateRequest);
        } catch (Exception e) {
            throw new RepositoryException("Error al eliminar la página con ID " + pageId, e);
        }
    }
    public NotionClient getClient() {
        return client;
    }
    
    public String getDatabaseId() {
        return databaseId;
    }
}
