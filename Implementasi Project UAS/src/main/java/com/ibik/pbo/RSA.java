package com.ibik.pbo;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.swing.JOptionPane;

public class RSA {

    public void Algorithm(String data) throws Exception {
        // Generate key pair
        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Original message
        String message = data;

        // Encrypt the message using the public key
        byte[] encryptedMessage = encrypt(message, publicKey);

        // Decrypt the message using the private key
        String decryptedMessage = decrypt(encryptedMessage, privateKey);
        
        JOptionPane.showMessageDialog(null, "Encrypted: " + new String(encryptedMessage) +
				"\n\nDecrypted : " + decryptedMessage);
    }

    // Generate RSA key pair
    private static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // You can adjust the key size as needed
        return keyPairGenerator.generateKeyPair();
    }

    // Encrypt a message using RSA public key
    private static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        byte[] data = message.getBytes();
        // Cipher.ENCRYPT_MODE specifies encryption
        return rsaOperation(data, publicKey, Cipher.ENCRYPT_MODE);
    }

    // Decrypt a message using RSA private key
    private static String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        // Cipher.DECRYPT_MODE specifies decryption
        byte[] decryptedData = rsaOperation(encryptedMessage, privateKey, Cipher.DECRYPT_MODE);
        return new String(decryptedData);
    }

    // Perform RSA encryption or decryption
    private static byte[] rsaOperation(byte[] input, java.security.Key key, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(mode, key);
        return cipher.doFinal(input);
    }
}
