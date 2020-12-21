package edu.klifanova.encryptor.algorithm;


import edu.klifanova.encryptor.algorithm.impl.Unicode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnicodeTest {

    private Unicode unicode = new Unicode();

    @DisplayName("Should encryption in algorithm Unicode the correct result")
    @ParameterizedTest(name = "{index} => message={0}, key={1}, result={2}")
    @CsvSource({
            "t,7,{",
            "a,1,b",
            "a,65536,a",
            "a,-65536,a",
            "Lets Encrypt,5,Qjyx%Jshw~uy",
            "katya,0,katya",
            "?,-1,>"
    })
    void encryptionUnicode(String message, int key, String result) {
        assertEquals(result, unicode.encryption(message, key));
    }

    @DisplayName("Should decryption in algorithm Unicode the correct result")
    @ParameterizedTest(name = "{index} => message={0}, key={1}, result={2}")
    @CsvSource({
            "{,7,t",
            "b,1,a",
            "a,65536,a",
            "a,-65536,a",
            "Qjyx%Jshw~uy,5,Lets Encrypt",
            "katya,0,katya",
            ">,-1,?"
    })
    void decryptionUnicode(String message, int key, String result) {
        assertEquals(result, unicode.decryption(message, key));
    }
}
