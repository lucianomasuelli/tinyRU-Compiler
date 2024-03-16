package tinyru;

public class LexerError extends RuntimeException {
    private final String errorMessage;

    public LexerError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getMessage() {
        return "ERROR: LEXICO \n" + errorMessage;
    }
}

class IllegalCharError extends LexerError {
    public IllegalCharError(char c, int line, int column) {
        super( "| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column + "| DESCRIPCION: " + "Illegal character '" + c + "'");
    }
}
