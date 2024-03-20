package tinyru.Exceptions;

/**
 * Clase que representa la presencia de un char sin cerrar
 */
public class UnterminatedCharError extends LexerError {
    public UnterminatedCharError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Unterminated char: " + "'"+ id);
    }
}
