package org.text.processor.exception;

import java.io.IOException;

public class NoFileException extends IOException {
    public NoFileException(String message, Throwable cause){
        super(message,cause);
    }
    public NoFileException(String message){
        super(message);
    }
    public NoFileException(){
        super();
    }
}
