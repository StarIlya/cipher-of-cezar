package model;

public class BrutForceResult {

    private String decryptedText;
    private int shiftKey;

    public BrutForceResult(String decryptedText, int shiftKey) {
        this.decryptedText = decryptedText;
        this.shiftKey = shiftKey;
    }

    public String getDecryptedText() {
        return decryptedText;
    }

    public void setDecryptedText(String decryptedText) {
        this.decryptedText = decryptedText;
    }

    public int getShiftKey() {
        return shiftKey;
    }

    public void setShiftKey(int shiftKey) {
        this.shiftKey = shiftKey;
    }
}
