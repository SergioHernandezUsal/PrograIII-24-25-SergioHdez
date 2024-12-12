package model;

public class ExporterFactory {
    public static IExporter getExporter(String type) {
        switch (type.toLowerCase()) {
            case "csv":
                return new CSVExporter();
            case "json":
                return new JSONExporter();
            default:
                throw new IllegalArgumentException("Tipo de exportacion invalido: " + type);
        }
    }
}
