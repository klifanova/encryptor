package edu.klifanova.encryptor.algorithm;

import edu.klifanova.encryptor.algorithm.impl.Shift;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShiftTest {

    private Shift shift = new Shift();

    @DisplayName("Should encryption in algorithm Shift the correct result")
    @ParameterizedTest(name = "{index} => message={0}, key={1}, result={2}")
    @CsvSource({
            "t,7,a",
            "b,-1,a",
            "a,1,b",
            "z,1,a",
            "a,-1,z",
            "z,27,a",
            "a,-27,z",
            "Lets Encrypt,7,Slaz Lujyfwa",
            "4?,1,4?",
            "katya,0,katya"

    })
    void encryptionShift(String message, int key, String result) {
        assertEquals(result, shift.encryption(message, key));
    }

    @DisplayName("Should decryption in algorithm Shift the correct result")
    @ParameterizedTest(name = "{index} => message={0}, key={1}, result={2}")
    @CsvSource({
            "a,7,t",
            "b,1,a",
            "a,-1,b",
            "a,1,z",
            "z,-1,a",
            "a,27,z",
            "z,-27,a",
            "Slaz Lujyfwa,7,Lets Encrypt",
            "katya,0,katya",
            "4?,1,4?"

    })
    void decryptionShift(String message, int key, String result) {
        assertEquals(result, shift.decryption(message, key));
    }
}
