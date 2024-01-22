package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.TextNormalizer;
import view.Menu;

public class TextNormalizerMain {

    TextNormalizer normalizer;
    String inputFile;
    String outputFile;

    public TextNormalizerMain() throws FileNotFoundException, IOException {
        inputFile = "input.txt";
        outputFile = "output.txt";
        normalizer = new TextNormalizer(inputFile);
    }

    public void displayMainMenu() {
        Menu<String> mainMenu = new Menu<>("Nothing much to choose here..", new String[]{
            "Normalize text from (input.txt)",
            "Exit"
        }) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1 ->
                        normalizeText();
                    case 2 ->
                        System.exit(0);
                    default ->
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        };
        mainMenu.run();
    }

    public void normalizeText() {
        try {
            normalizer.normalizeText(outputFile);
            System.out.println("Text normalization successful.");
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        TextNormalizerMain main = new TextNormalizerMain();
        main.displayMainMenu();
    }
}
