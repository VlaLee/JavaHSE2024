import Classes.FrequencyFileDictionary;
import Classes.UserFileGetter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        UserFileGetter getter = new UserFileGetter();

        String fileNameToRead, fileNameToWrite;

        try {

            fileNameToRead = getter.getFileFromUser("Enter the file for reading:");
            getter.checkReadingAccess(fileNameToRead);

            fileNameToWrite = getter.getFileFromUser("Enter the file for writing:");
            getter.checkWritingAccess(fileNameToWrite);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Unexpected exception");
            return;
        }

        FrequencyFileDictionary frequencyDictionary1 = new FrequencyFileDictionary(fileNameToRead, fileNameToWrite);

        try {

            frequencyDictionary1.fillDict();
            System.out.println("Dictionary has been successfully filled using text from " + fileNameToRead);

            frequencyDictionary1.writeToFile();
            System.out.println("Information from dictionary has been recorded to " + fileNameToWrite);

        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Unexpected exception");
            return;
        }

        try {

            fileNameToRead = getter.getFileFromUser("Enter the another file for reading:");
            getter.checkReadingAccess(fileNameToRead);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Unexpected exception");
            return;
        }

        FrequencyFileDictionary frequencyDictionary2 = new FrequencyFileDictionary(frequencyDictionary1,
                fileNameToRead, fileNameToWrite);

        try {

            frequencyDictionary2.fillDict();
            System.out.println("Dictionary has been successfully filled using text from " + fileNameToRead);

            frequencyDictionary2.writeToFile();
            System.out.println("Information from dictionary has been recorded to " + fileNameToWrite);

        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected exception");
        }
    }
}