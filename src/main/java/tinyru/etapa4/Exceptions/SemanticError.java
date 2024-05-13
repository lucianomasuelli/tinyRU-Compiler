package tinyru.etapa4.Exceptions;

public class SemanticError extends RuntimeException {
    private final String errorMessage;

    public SemanticError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return "ERROR: SEMÁNTICO - SENTENCIAS \n" + errorMessage;
    }

}
