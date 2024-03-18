package tinyru.Exceptions;

public class UnterminatedCharError extends LexerError {
    public UnterminatedCharError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Unterminated char: " + "'"+ id);
    }
}
