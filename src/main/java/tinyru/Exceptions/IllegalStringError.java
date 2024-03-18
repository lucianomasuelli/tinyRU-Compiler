package tinyru.Exceptions;

public class IllegalStringError extends LexerError {
    public IllegalStringError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Illegal character '" + c + "' found in the string");
    }
}
