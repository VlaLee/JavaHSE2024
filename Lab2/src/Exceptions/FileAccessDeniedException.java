package Exceptions;

import java.io.IOException;

public class FileAccessDeniedException extends IOException {

    public FileAccessDeniedException(String message) {
        super(message);
    }
}
