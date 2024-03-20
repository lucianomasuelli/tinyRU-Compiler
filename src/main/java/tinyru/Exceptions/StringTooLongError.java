package tinyru.Exceptions;

/**
 * Clase que representa el error de un string de mas de 1024 caracteres
 * de longitud
 */
public class StringTooLongError extends LexerError {
    public StringTooLongError(int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "String too long");
    }
}