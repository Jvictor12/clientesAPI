package io.github.jvictor12.clients.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException() { super(); }

    public ObjectNotFoundException (String message) { super(message); }

    public ObjectNotFoundException (String message, Throwable cause) { super(message, cause); }
}
