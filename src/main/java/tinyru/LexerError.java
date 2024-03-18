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
        super( "| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Illegal character '" + c + "'");
    }
}

class IllegalStringError extends LexerError {
    public IllegalStringError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Illegal character '" + c + "' found in the string");
    }
}

class IllegalSymbolError extends LexerError {
    public IllegalSymbolError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Invalid simbol '" + c + "'");
    }
}

class InvalidOperatorError extends LexerError {
    public InvalidOperatorError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "InvalidOperator '" + c + "'");
    }
}

class UnterminatedStringError extends LexerError {
    public UnterminatedStringError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Unterminated string: " + id);
    }
}

class UnterminatedCharError extends LexerError {
    public UnterminatedCharError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Unterminated char: " + "'"+ id);
    }
}
class CharTooLongError extends LexerError {
    public CharTooLongError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Char too long");
    }
}

class IllegalStructIdError extends LexerError {
    public IllegalStructIdError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Illegal struct identifier: " + id);
    }
}

class StringTooLongError extends LexerError {
    public StringTooLongError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "String too long");
    }
}


