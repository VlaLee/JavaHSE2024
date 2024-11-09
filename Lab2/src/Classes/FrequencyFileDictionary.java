package Classes;

import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import Interfaces.FrequencyDictionary;
import Interfaces.Writer;

public class FrequencyFileDictionary implements FrequencyDictionary, Writer {

    private final HashMap<Character, Integer> freqDict;
    private final String fileNameToRead;
    private final String fileNameToWrite;

    public FrequencyFileDictionary(final String fileNameToRead, final String fileNameToWrite) {
        this.fileNameToRead = fileNameToRead;
        this.fileNameToWrite = fileNameToWrite;

        freqDict = new HashMap<>();

        for (char character = 'a'; character <= 'z'; ++character) {
            freqDict.put(Character.toUpperCase(character), 0);
            freqDict.put(character, 0);
        }
    }

    public FrequencyFileDictionary(final FrequencyDictionary dict, final String fileNameToRead,
                                   final String fileNameToWrite) {
        this.fileNameToRead = fileNameToRead;
        this.fileNameToWrite = fileNameToWrite;

        freqDict = dict.getDict();
    }

    public FrequencyFileDictionary(final FrequencyDictionary dict, final String fileNameToWrite) {
        this(dict, null, fileNameToWrite);
    }

    private void putInDict(final char character) {
        boolean isLowerChar = false, isUpperChar = false;

        if (character >= 'a' && character <= 'z') {
            isLowerChar = true;
        }
        else if (character >= 'A'&& character <= 'Z') {
            isUpperChar = true;
        }

        if (isLowerChar || isUpperChar) {
            freqDict.put(character, freqDict.get(character) + 1);
        }
    }

    @Override
    public void fillDict() throws IOException {
        if (fileNameToRead == null) {
            throw new NullPointerException("Attempt to fill dictionary, but filename string is null");
        }

        try (FileReader reader = new FileReader(fileNameToRead)) {

            int character;
            while ((character = reader.read()) != -1) {
                putInDict((char) character);
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void writeToFile() throws IOException {
        if (fileNameToWrite == null) {
            throw new NullPointerException("Attempt to write information to file, but filename string is null");
        }

        String row;

        try (FileWriter writer = new FileWriter(fileNameToWrite)) {

            for (char character = 'A'; character <= 'Z'; ++character) {
                row = String.format("%c: %d\n", character, freqDict.get(character));
                writer.write(row);
            }

            for (char character = 'a'; character <= 'z'; ++character) {
                row = String.format("%c: %d\n", character, freqDict.get(character));
                writer.write(row);
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public HashMap<Character, Integer> getDict() {
        return freqDict;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (char character = 'A'; character <= 'Z'; ++character) {
            result.append(character).append(": ");
            result.append(freqDict.get(character)).append("\n");
        }

        for (char character = 'a'; character <= 'z'; ++character) {
            result.append(character).append(": ");
            result.append(freqDict.get(character)).append("\n");
        }

        return new String(result);
    }
}
