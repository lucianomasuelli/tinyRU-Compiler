package tinyru.Exceptions;

public class IllegalStructIdError extends LexerError {
    public IllegalStructIdError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Illegal struct identifier: " + id);
    }
}
