package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * TextNormalizer class for reading and normalizing text from a file
 */
public class TextNormalizer {

    private String fileName;
    private BufferedReader reader;

    
    /**
     * create class goes along with a file
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public TextNormalizer(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        reader = new BufferedReader(new FileReader(fileName));
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void displayFileContent() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    /**
     * display file content
     *
     * @param output
     */
    public void displayFileContent(String output) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(output))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
        }
    }

    /**
     * for the fileName that gives along with the class
     *
     * @param outputFile
     * @throws IOException
     */
    public void normalizeText(String outputFile) throws IOException {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            // Reset the reader to read from the new file
            reader = new BufferedReader(new FileReader(fileName));

            boolean isFirstLine = true;
            String line;

            while ((line = reader.readLine()) != null) {
                // Remove leading and trailing whitespaces
                line = line.trim();

                // Normalize spaces between words
                line = line.replaceAll("\\s+", " ");

                // Normalize spaces after comma, dot, and colon
                line = line.replaceAll("\\s*([,\\.\\:])\\s*", "$1 ");

                // Normalize quotes
                line = line.replaceAll("\\s*\"\\s*", "\"");

                // Capitalize first character of the first word in the first line
                if (isFirstLine) {
                    line = capitalizeFirstWord(line);
                    isFirstLine = false;
                }
                // Ensure there is a dot at the end of the text
                if (!line.endsWith(".")) {
                    line += ".";
                }
                // Write the normalized line to the output file
                writer.write(line);
                writer.newLine();
            }
        }
    }

    /**
     * for more general purpose when dealing with more general inputFile
     *
     * @param inputFile
     * @param outputFile
     * @throws IOException
     */
    public void normalizeText(String inputFile, String outputFile) throws IOException {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            // Reset the reader to read from the new file
            reader = new BufferedReader(new FileReader(inputFile));

            boolean isFirstLine = true;
            String line;

            while ((line = reader.readLine()) != null) {
                // Remove leading and trailing whitespaces
                line = line.trim();

                // Normalize spaces between words
                line = line.replaceAll("\\s+", " ");

                // Normalize spaces after comma, dot, and colon
                line = line.replaceAll("\\s*([,\\.\\:])\\s*", "$1 ");

                // Normalize quotes
                line = line.replaceAll("\\s*\"\\s*", "\"");

                // Capitalize first character of the first word in the first line
                if (isFirstLine) {
                    line = capitalizeFirstWord(line);
                    isFirstLine = false;
                }
                // Ensure there is a dot at the end of the text
                if (!line.endsWith(".")) {
                    line += ".";
                }
                // Write the normalized line to the output file
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private String capitalizeFirstWord(String line) {
        if (line.isEmpty()) {
            return line;
        }
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
}
