package model;

import java.util.ArrayList;

public interface IExporter {
    void export(ArrayList<Task> tasks, String filePath) throws ExporterException;
    ArrayList<Task> importData(String filePath) throws ExporterException;
}
