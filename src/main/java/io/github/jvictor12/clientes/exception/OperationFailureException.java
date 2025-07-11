package io.github.jvictor12.clientes.exception;

public class OperationFailureException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "Não foi possível concluir a operação, por favor contate o suporte técnico!";

    public OperationFailureException(String message) { super(message); }
}
