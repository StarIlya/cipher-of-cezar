package service;

import java.io.*;

public class FileService {

    private static final int END_OF_FILE = -1;

    public static String getTextFromFile(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            StringBuilder allText = new StringBuilder();
            int numChar;
            while ((numChar = input.read()) != END_OF_FILE) {
                char symbol = (char) numChar;
                allText.append(symbol);
            }
            return allText.toString();
        }
    }

    public static void saveTextToNewFile(String text, String fileName) throws IOException {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(fileName))) {
            output.write(text);
            output.flush();
        }
    }
}
