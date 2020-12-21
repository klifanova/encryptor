package edu.klifanova.encryptor;


import edu.klifanova.encryptor.arguments.Arguments;
import edu.klifanova.encryptor.facade.Facade;
import io.airlift.airline.SingleCommand;

public class Application {

    public static void main(String[] args) {
        var arguments = SingleCommand.singleCommand(Arguments.class).parse(args);
        new Facade().processText(arguments);
    }
}
