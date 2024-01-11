package com.ibik.pbo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

import java.util.Base64;

public class AES {

    private static final String ALGORITHM = "AES";
    private static final String MODE = "AES/CBC/PKCS5Padding";

    private static String encrypt(String plainText, SecretKeySpec key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String encryptedText, SecretKeySpec key, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

   public void Algorithm(String Data) throws Exception {
        String keyString = "0123456789abcdef"; // 16 bytes for AES-128
        String ivString = "1234567890abcdef"; // 16 bytes IV

        SecretKeySpec key = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(ivString.getBytes());

        String message = Data;

        // Encryption
        String encryptedMessage = encrypt(message, key, iv);
        String decryptedMessage = decrypt(encryptedMessage, key, iv);
        JOptionPane.showMessageDialog(null, "Encrypted: " + encryptedMessage +
        								"\n\nDecrypted : " + decryptedMessage);
    }
}
