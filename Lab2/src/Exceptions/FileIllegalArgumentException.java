package Exceptions;

import java.io.IOException;

public class FileIllegalArgumentException extends IOException {

    public FileIllegalArgumentException(String message) {
        super(message);
    }
}
