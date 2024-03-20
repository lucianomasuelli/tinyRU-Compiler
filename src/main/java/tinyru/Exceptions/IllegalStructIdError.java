package tinyru.Exceptions;

/**
 * Clase que representa el error de un identificador de estructura que no cumple
 * con las condiciones definidas
 */
public class IllegalStructIdError extends LexerError {
    public IllegalStructIdError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Illegal struct identifier: " + id);
    }
}
