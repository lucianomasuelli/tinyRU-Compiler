package tinyru.etapa2.Exceptions;

public class ParserError extends RuntimeException {
    private final String errorMessage;

    public ParserError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return "ERROR: SINTÁCTICO \n" + errorMessage;
    }
}
