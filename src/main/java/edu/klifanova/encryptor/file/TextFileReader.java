package edu.klifanova.encryptor.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class TextFileReader {


    public String readFile(String fullFileName) {
        File file = new File(fullFileName);
        try (FileReader reader = new FileReader(file)) {
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            return new String(chars);
        } catch (IOException | NullPointerException e) {
            log.error("Error occurred during reading from file: ", e);
            System.exit(1);
        }
        return null;
    }
}
