package tinyru.Exceptions;

public class StringTooLongError extends LexerError {
    public StringTooLongError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "String too long");
    }
}