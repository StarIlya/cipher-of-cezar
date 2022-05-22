package service;

import model.BrutForceResult;

public class CezarCipherService {

    private static final int APPROXIMATE_WORD_LENGTH = 7;
    private static final String ALPHABET_WITH_SYMBOLS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!? ";
    public static final int ASSUMPTION_ERROR = 3;

    public static String encrypt(String message, int shiftKey) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            int charPosition = ALPHABET_WITH_SYMBOLS.indexOf(currentChar);
            if (charPosition != -1) {
                int keyVal = (shiftKey + charPosition) % ALPHABET_WITH_SYMBOLS.length();
                char replaceVal = ALPHABET_WITH_SYMBOLS.charAt(keyVal);
                cipherText.append(replaceVal);
            } else {
                cipherText.append(currentChar);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String message, int shiftKey) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);
            int charPosition = ALPHABET_WITH_SYMBOLS.indexOf(currentChar);
            if (charPosition != -1) {
                int keyVal = (charPosition - shiftKey) % ALPHABET_WITH_SYMBOLS.length();
                if (keyVal < 0) {
                    keyVal = ALPHABET_WITH_SYMBOLS.length() + keyVal;
                }
                char replaceVal = ALPHABET_WITH_SYMBOLS.charAt(keyVal);
                cipherText.append(replaceVal);
            } else {
                cipherText.append(currentChar);
            }
        }
        return cipherText.toString();
    }

    public static BrutForceResult brutForceEncryptedText(String encryptedText) {
        for (int i = 0; i < ALPHABET_WITH_SYMBOLS.length(); i++) {
            String decryptedText = decrypt(encryptedText, i);
            String[] numberOfWords = decryptedText.split(" ");
            if (numberOfWords.length >= decryptedText.length() / APPROXIMATE_WORD_LENGTH - ASSUMPTION_ERROR) {
                return new BrutForceResult(decryptedText, i);
            }
        }
        return null;
    }

}
