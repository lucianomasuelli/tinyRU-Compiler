package tinyru.Exceptions;

public class UnterminatedStringError extends LexerError {
    public UnterminatedStringError(int line, int column, String id) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Unterminated string: " + id);
    }
}