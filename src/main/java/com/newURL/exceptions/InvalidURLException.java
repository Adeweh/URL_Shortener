package com.newURL.exceptions;

public class InvalidURLException extends UrlShortenerException{
    public InvalidURLException() {
    }

    public InvalidURLException(String message) {
        super(message);
    }

    public InvalidURLException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidURLException(Throwable cause) {
        super(cause);
    }
}
