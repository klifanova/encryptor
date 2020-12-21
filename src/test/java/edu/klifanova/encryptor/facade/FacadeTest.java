package edu.klifanova.encryptor.facade;

import edu.klifanova.encryptor.algorithm.impl.Shift;
import edu.klifanova.encryptor.algorithm.impl.Unicode;
import edu.klifanova.encryptor.arguments.Algorithm;
import edu.klifanova.encryptor.arguments.Arguments;
import edu.klifanova.encryptor.arguments.Mode;
import edu.klifanova.encryptor.file.TextFileReader;
import edu.klifanova.encryptor.file.TextFileWriter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacadeTest {

    @Mock
    private TextFileReader textFileReader;
    @Mock
    private TextFileWriter textFileWriter;
    @Mock
    private Shift shift;
    @Mock
    private Unicode unicode;

    @InjectMocks
    private Facade facade = new Facade();

    @Test
    @DisplayName("Process text with key and data specified")
    void processText_withKeyAndDataSpecified_encryptedAndPrinted() {
        Arguments arguments = new Arguments();
        arguments.setData("Lets Encrypt");
        arguments.setKey(7);

        facade.processText(arguments);

        verify(shift).encryption(arguments.getData(), arguments.getKey());
        verify(shift, never()).decryption(any(), anyInt());
        verifyNoInteractions(unicode);
        verifyNoInteractions(textFileReader);
        verifyNoInteractions(textFileWriter);
    }

    @Test
    @DisplayName("Process text with data and out and algorithm specified")
    void processText_withDataAndOutAndAlgorithmSpecified_decryptedAndPlacedInFile() {
        Arguments arguments = new Arguments();
        arguments.setData("Lets Encrypt");
        arguments.setAlg(Algorithm.shift);
        arguments.setMode(Mode.dec);
        arguments.setOut("output.txt");

        when(shift.decryption(arguments.getData(), arguments.getKey())).thenReturn(arguments.getData());

        facade.processText(arguments);

        verify(shift).decryption(arguments.getData(), arguments.getKey());
        verify(shift, never()).encryption(any(), anyInt());
        verify(textFileWriter).writeFile(arguments.getData(), arguments.getOut());
        verifyNoInteractions(unicode);
        verifyNoInteractions(textFileReader);
    }

    @Test
    @DisplayName("Process text with data and out and algorithm and in specified")
    void processText_withDataAndOutAndAlgorithmAndInSpecified_decryptedAndPlacedInFile() {
        Arguments arguments = new Arguments();
        arguments.setData("Lets Encrypt");
        arguments.setAlg(Algorithm.unicode);
        arguments.setMode(Mode.dec);
        arguments.setIn("input.txt");
        arguments.setOut("output.txt");

        when(unicode.decryption(arguments.getData(), arguments.getKey())).thenReturn(arguments.getData());

        facade.processText(arguments);

        verify(unicode).decryption(arguments.getData(), 0);
        verify(unicode, never()).encryption(any(), anyInt());
        verify(textFileWriter).writeFile(arguments.getData(), arguments.getOut());
        verifyNoInteractions(shift);
        verifyNoInteractions(textFileReader);
    }

    @Test
    @DisplayName("Process text with out and algorithm and in specified")
    void processText_withOutAndAlgorithmAndInSpecified_decryptedAndPlacedInFile() {
        Arguments arguments = new Arguments();
        arguments.setAlg(Algorithm.unicode);
        arguments.setMode(Mode.dec);
        arguments.setIn("input.txt");
        arguments.setOut("output.txt");

        when(textFileReader.readFile(arguments.getIn())).thenReturn("");
        when(unicode.decryption("", arguments.getKey())).thenReturn("");

        facade.processText(arguments);

        verify(unicode).decryption("", arguments.getKey());
        verify(unicode, never()).encryption(any(), anyInt());
        verify(textFileReader).readFile(arguments.getIn());
        verify(textFileWriter).writeFile("", arguments.getOut());
        verifyNoInteractions(shift);

    }

    @Test
    @DisplayName("Process text with out and algorithm and mode in specified")
    void processText_withOutAndAlgorithmAndModeSpecified_decryptedAndPlacedInFile() {
        Arguments arguments = new Arguments();
        arguments.setAlg(Algorithm.unicode);
        arguments.setMode(Mode.dec);
        arguments.setOut("output.txt");

        when(unicode.decryption("", arguments.getKey())).thenReturn("");

        facade.processText(arguments);

        verify(unicode).decryption("", arguments.getKey());
        verify(unicode, never()).encryption(any(), anyInt());
        verify(textFileWriter).writeFile("", arguments.getOut());
        verifyNoInteractions(shift);
        verifyNoInteractions(textFileReader);

    }
}
