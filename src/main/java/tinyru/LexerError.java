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

class IllegalStringError extends LexerError {
    public IllegalStringError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column + "| DESCRIPCION: " + "Illegal string");
    }
}

class IllegalSymbolError extends LexerError {
    public IllegalSymbolError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column + "| DESCRIPCION: " + "Invalid simbol '" + c + "'");
    }
}

class InvalidOperatorError extends LexerError {
    public InvalidOperatorError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column + "| DESCRIPCION: " + "InvalidOperator '" + c + "'");
    }
}

class UnterminatedStringError extends LexerError {
    public UnterminatedStringError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column + "| DESCRIPCION: " + "Unterminated string");
    }
}

class UnterminatedCharError extends LexerError {
    public UnterminatedCharError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column + "| DESCRIPCION: " + "Unterminated char");
    }
}
class CharTooLongError extends LexerError {
    public CharTooLongError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column + "| DESCRIPCION: " + "Char too long");
    }
}

