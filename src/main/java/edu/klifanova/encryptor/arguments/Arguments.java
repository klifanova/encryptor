package edu.klifanova.encryptor.arguments;

import io.airlift.airline.Command;
import io.airlift.airline.Option;
import lombok.Data;

@Command(name = "arguments")
@Data
public class Arguments {

    @Option(name = "-mode", description = "Type of operation")
    private Mode mode = Mode.enc;
    @Option(name = "-in", description = "Path to the file with data for processing")
    private String in;
    @Option(name = "-out", description = "Path to the file where you want to save the result")
    private String out;
    @Option(name = "-key", description = "Offset for encoding/decoding")
    private int key = 0;
    @Option(name = "-alg", description = "Type of the algorithm")
    private Algorithm alg = Algorithm.shift;
    @Option(name = "-data", description = "Text data for encoding/decoding")
    private String data;
}
