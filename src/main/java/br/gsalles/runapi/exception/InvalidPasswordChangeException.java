package br.gsalles.runapi.exception;

public class InvalidPasswordChangeException extends RuntimeException {
    public InvalidPasswordChangeException(String message) {
        super(message);
    }
}
