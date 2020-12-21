package edu.klifanova.encryptor.file;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class TextFileWriter {

    public void writeFile(String text, String fullFileName) {
        File file = new File(fullFileName);
        try (BufferedWriter output = new BufferedWriter(new FileWriter(file))) {
            output.write(text);
        } catch (IOException | NullPointerException e) {
            log.error("Error occurred during writing to file: ", e);
            System.exit(1);
        }
    }
}
