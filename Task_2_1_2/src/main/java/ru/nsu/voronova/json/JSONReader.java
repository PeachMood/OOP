package ru.nsu.voronova.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

/**
 * The JSONReader class allows to read the parameters of the pizzeria from a file.
 */
public class JSONReader {
    private static final String DEFAULT_FILE_NAME = "pizzeria.json";
    private final File file;
    private final String fileName;
    private BufferedReader reader;

    /**
     * Creates an instance of the class JSONReader.
     * Initializes an instance of the File class with the default file name.
     */
    public JSONReader() {
        this.fileName = DEFAULT_FILE_NAME;
        this.file = new File(DEFAULT_FILE_NAME);
    }

    /**
     * Creates an instance of the class JSONReader.
     * Initializes an instance of the File class with the specified file name.
     *
     * @param fileName - the name of the file to read data from.
     */
    public JSONReader(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    /**
     * Allows getting the name of the file associated with this writer.
     *
     * @return the file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Creates, if necessary, a file for storing notes with the specified name {@link #fileName} and opens it for reading.
     * In case of an error, displays a message.
     */
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

    /**
     * Reads all text from the file.
     *
     * @param reader - buffered reader associated with the file.
     * @return read text from the file.
     * @throws IOException there are problems if there are problems when reading from the file.
     */
    private String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            content.append(currentLine).append("\n");
        }
        return content.toString();
    }

    /**
     * Reads pizzeria parameters from the file.
     * In case of an error, displays a message.
     *
     * @return the array of read notes.
     */
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

    /**
     * Closes buffered reader associated with the file.
     * In case of an error, displays a message.
     */
    public void close() {
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
