package tinyru.Exceptions;

public class InvalidOperatorError extends LexerError {
    public InvalidOperatorError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "InvalidOperator '" + c + "'");
    }
}