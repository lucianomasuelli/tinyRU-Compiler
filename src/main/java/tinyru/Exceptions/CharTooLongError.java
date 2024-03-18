package tinyru.Exceptions;

public class CharTooLongError extends LexerError {
    public CharTooLongError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Char too long");
    }
}
