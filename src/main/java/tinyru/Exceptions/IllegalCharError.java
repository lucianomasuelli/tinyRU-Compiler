package tinyru.Exceptions;

public class IllegalCharError extends LexerError {
    public IllegalCharError(char c, int line, int column) {
        super( "| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Illegal character '" + c + "'");
    }
}
