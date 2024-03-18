package tinyru.Exceptions;

public class LexerError extends RuntimeException {
    private final String errorMessage;

    public LexerError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return "ERROR: LEXICO \n" + errorMessage;
    }
}














