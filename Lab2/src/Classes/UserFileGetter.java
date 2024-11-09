package Classes;

import Exceptions.FileAccessDeniedException;
import Exceptions.FileIllegalArgumentException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UserFileGetter {

    public String getFileFromUser(String message) throws IOException {
        Scanner scanner = new Scanner(System.in);

        if (message == null) {
            System.out.println("Enter a filename:");
        }
        else {
            System.out.println(message);
        }

        String fileName = scanner.nextLine();

        File file = new File(fileName);

        if (!file.exists()) {
            throw new FileNotFoundException("File " + file.getAbsolutePath() + " does not exist");
        }

        if (file.isDirectory()) {
            throw new FileIllegalArgumentException(file.getAbsolutePath() + " is a folder, not file");
        }

        return fileName;
    }

    public void checkReadingAccess(String fileName) throws FileAccessDeniedException {
        File file = new File(fileName);

        if (!file.canRead()) {
            throw new FileAccessDeniedException("File " + file.getAbsolutePath() + " is not readable");
        }
    }

    public void checkWritingAccess(String fileName) throws FileAccessDeniedException {
        File file = new File(fileName);

        if (!file.canWrite()) {
            throw new FileAccessDeniedException("File " + file.getAbsolutePath() + " is not writeable");
        }
    }
}
