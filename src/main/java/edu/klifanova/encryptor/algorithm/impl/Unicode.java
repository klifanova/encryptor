package edu.klifanova.encryptor.algorithm.impl;


import edu.klifanova.encryptor.algorithm.TextProcessor;

public class Unicode implements TextProcessor {

    public String decryption(String text, int key) {
        return encryption(text, -key);
    }

    public String encryption(String text, int key) {
        var result = new StringBuilder();
        int len = text.length();
        for (int x = 0; x < len; x++) {
            result.append((char) (text.charAt(x) + key));
        }
        return result.toString();
    }
}
