package com.newURL.exceptions;

public class UrlShortenerException extends Exception{
    public UrlShortenerException() {
        super();
    }

    public UrlShortenerException(String message) {
        super(message);
    }

    public UrlShortenerException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlShortenerException(Throwable cause) {
        super(cause);
    }
}
