package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task implements Serializable {
    private static final long serialVersionUID = 1L;
    private int identifier;
    private String taskTitle;
    private Date date;
    private String content;
    private int priority;
    private int estimatedDuration;
    private boolean completed;

    public Task(int identifier, String taskTitle, Date date, String content, int priority, int estimatedDuration, boolean completed) {
        this.identifier = identifier;
        this.taskTitle = taskTitle;
        this.date = date;
        this.content = content;
        this.priority = priority;
        this.estimatedDuration = estimatedDuration;
        this.completed = completed;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
   public String toString() {
    return String.format("%-15s %-20s %-20s %-30s %-14s %-20s %-10s",
            identifier,
            taskTitle,
            new SimpleDateFormat("yyyy-MM-dd").format(date),
            content,
            priority,
            estimatedDuration + " min",
            completed ? "True" : "False");
}
}
