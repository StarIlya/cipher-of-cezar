package runner;

import model.BrutForceResult;
import service.CezarCipherService;
import service.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CipherRunner {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер функции:");
        System.out.println("1 - Шифрование текста");
        System.out.println("2 - Дешифрование текста");
        System.out.println("3 - Брутфорс текста");

        String numberOfFunction = scanner.nextLine();
        if ("1".equals(numberOfFunction)) {
            encryptFunction(scanner);
        } else if ("2".equals(numberOfFunction)) {
            decryptFunction(scanner);
        } else if ("3".equals(numberOfFunction)) {
            brutForceText(scanner);
        } else {
            System.out.println("Такой функции нету в системе");
        }

    }

    private static void encryptFunction(Scanner scanner) throws IOException {
        System.out.println("Ввдети имя файла с текстом:");
        String plainTextFileName = scanner.nextLine();
        String textFromFile = getTextFromFile(plainTextFileName, scanner);

        System.out.println("Введите ключ шифра:");
        int shiftKey = Integer.parseInt(scanner.nextLine());
        String encryptedText = CezarCipherService.encrypt(textFromFile, shiftKey);

        System.out.println("Введите имя зашифрованного файла:");
        String encryptedFileName = scanner.nextLine();
        FileService.saveTextToNewFile(encryptedText, encryptedFileName);
    }

    private static void decryptFunction(Scanner scanner) throws IOException {
        System.out.println("Введите имя зашифрованного файла:");
        String encryptedFileName = scanner.nextLine();

        String textFromFile = getTextFromFile(encryptedFileName, scanner);

        System.out.println("Введите ключ шифра:");
        int shiftKey = Integer.parseInt(scanner.nextLine());
        String decryptedText = CezarCipherService.decrypt(textFromFile, shiftKey);

        System.out.println("Введите имя дешифрованного файла:");
        String decryptedFileName = scanner.nextLine();
        FileService.saveTextToNewFile(decryptedText, decryptedFileName);
    }

    private static void brutForceText(Scanner scanner) throws IOException {
        System.out.println("Введите имя зашифрованного файла:");
        String encryptedFileName = scanner.nextLine();
        String encryptedText = getTextFromFile(encryptedFileName, scanner);
        BrutForceResult brutForceResult = CezarCipherService.brutForceEncryptedText(encryptedText);

        if (brutForceResult != null) {
            System.out.println("Введите имя дешифрованного файла:");
            String decryptedFileName = scanner.nextLine();
            FileService.saveTextToNewFile(brutForceResult.getDecryptedText(), decryptedFileName);
            System.out.println("Ключ шифрования: " + brutForceResult.getShiftKey());
        } else {
            System.out.println("Брут форс атака не удалась.");
        }
    }

    private static String getTextFromFile(String fileName, Scanner scanner) throws IOException {
        try {
            return FileService.getTextFromFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Такого файла нету в системе. Введите имя другого файла:");
            String newFileName = scanner.nextLine();
            return getTextFromFile(newFileName, scanner);
        }
    }

}

