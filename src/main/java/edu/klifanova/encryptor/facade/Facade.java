package edu.klifanova.encryptor.facade;

import edu.klifanova.encryptor.algorithm.impl.Shift;
import edu.klifanova.encryptor.algorithm.impl.Unicode;
import edu.klifanova.encryptor.arguments.Algorithm;
import edu.klifanova.encryptor.arguments.Arguments;
import edu.klifanova.encryptor.arguments.Mode;
import edu.klifanova.encryptor.file.TextFileReader;
import edu.klifanova.encryptor.file.TextFileWriter;

public class Facade {

    private TextFileReader textFileReader = new TextFileReader();
    private TextFileWriter textFileWriter = new TextFileWriter();
    private Shift shift = new Shift();
    private Unicode unicode = new Unicode();

    public void processText(Arguments args) {
        var text = getText(args);
        var algorithm = args.getAlg() == Algorithm.shift ? shift : unicode;
        var textToProcess = args.getMode() == Mode.enc ?
                algorithm.encryption(text, args.getKey())
                : algorithm.decryption(text, args.getKey());
        setText(args, textToProcess);
    }

    private String getText(Arguments args) {
        if (args.getIn() != null & args.getData() == null) {
            return textFileReader.readFile(args.getIn());
        } else {
            return args.getData() != null ? args.getData() : "";
        }
    }

    private void setText(Arguments args, String textToProcess) {
        if (args.getOut() != null) {
            textFileWriter.writeFile(textToProcess, args.getOut());
        } else {
            System.out.println(textToProcess);
        }
    }
}
