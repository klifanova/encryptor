package edu.klifanova.encryptor.algorithm.impl;


import edu.klifanova.encryptor.algorithm.TextProcessor;

public class Shift implements TextProcessor {

    private static final int ALPHABET_LENGTH = 26;
    private static final char START_CHAR_LOWE_CASE = 'a';
    private static final char END_CHAR_LOWER_CASE = 'z';
    private static final char START_CHAR_UPPER_CASE = 'A';
    private static final char END_CHAR_UPPER_CASE = 'Z';

    public String decryption(String text, int key) {
        return encryption(text, -key);
    }

    public String encryption(String text, int key) {
        var result = new StringBuilder();
        for (char character : text.toCharArray()) {
            char alphabetStart = Character.isUpperCase(character) ? START_CHAR_UPPER_CASE : START_CHAR_LOWE_CASE;
            if ((character >= START_CHAR_LOWE_CASE && character <= END_CHAR_LOWER_CASE)
                    || (character >= START_CHAR_UPPER_CASE && character <= END_CHAR_UPPER_CASE)) {
                int originalAlphabetPosition = character - alphabetStart;
                int positiveKey = ALPHABET_LENGTH + (key % ALPHABET_LENGTH);
                int newAlphabetPosition = (originalAlphabetPosition + positiveKey) % ALPHABET_LENGTH;
                char newCharacter = (char) (alphabetStart + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return String.valueOf(result);
    }
}
