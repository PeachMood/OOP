package ru.nsu.voronova.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JSONReader {
    private static final String DEFAULT_FILE_NAME = "pizzeria.json";
    private final File file;
    private final String fileName;
    private BufferedReader reader;

    public JSONReader() {
        this.fileName = DEFAULT_FILE_NAME;
        this.file = new File(DEFAULT_FILE_NAME);
    }

    public JSONReader(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void open() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new FileReader(file));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            content.append(currentLine).append("\n");
        }
        return content.toString();
    }

    public PizzeriaJSON read() {
        String content;
        try {
            content = readAllLines(reader);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }

        if (content.equals("")) {
            return null;
        }
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(content, PizzeriaJSON.class);
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
