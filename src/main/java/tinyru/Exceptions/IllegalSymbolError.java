package tinyru.Exceptions;

public class IllegalSymbolError extends LexerError {
    public IllegalSymbolError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Invalid simbol '" + c + "'");
    }
}
