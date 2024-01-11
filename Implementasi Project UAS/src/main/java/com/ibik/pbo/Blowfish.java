package com.ibik.pbo;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

import java.util.Base64;

public class Blowfish {

    public void Algorithm(String message)throws Exception {
        try {
            // Generate a random Blowfish key
            SecretKey secretKey = generateBlowfishKey();

            // Encrypt the message
            String encryptedMessage = encrypt(message, secretKey);

            // Decrypt the message
            String decryptedMessage = decrypt(encryptedMessage, secretKey);

            JOptionPane.showMessageDialog(null, "Encrypted: " + encryptedMessage +
										"\n\nDecrypted : " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static SecretKey generateBlowfishKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        return keyGenerator.generateKey();
    }

    private static String encrypt(String input, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private static String decrypt(String encryptedInput, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedInput);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
