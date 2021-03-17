package com.safaricom.dxl.streaming.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

/**
 * The type Utility.
 */
public class Utility {

    static ObjectMapper mapper = new ObjectMapper();

    /**
     * Read properties file properties.
     *
     * @param fileName the file name
     * @return the properties
     */
    public static Properties readPropertiesFile(String fileName){
        Properties prop = null;
        try (InputStream fis = Utility.class.getClassLoader().getResourceAsStream(fileName)) {
            prop = new Properties();
            prop.load(fis);
        } catch (IOException ioe) {
            // catch file not found error
        }
        return prop;
    }

    /**
     * @return one Jackson mapper
     */
    public static ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * Decrypt string.
     *
     * @param secretKey     the secret key
     * @param encryptedText the encrypted text
     * @return the string
     * @throws NoSuchAlgorithmException  the no such algorithm exception
     * @throws NoSuchPaddingException    the no such padding exception
     * @throws InvalidKeyException       the invalid key exception
     * @throws IllegalBlockSizeException the illegal block size exception
     * @throws BadPaddingException       the bad padding exception
     */
    public static String decrypt(String secretKey, String encryptedText) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKey aesKey = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        return new String(decryptedByte);
    }


    private Utility(){
        // Empty constructor
    }
}
