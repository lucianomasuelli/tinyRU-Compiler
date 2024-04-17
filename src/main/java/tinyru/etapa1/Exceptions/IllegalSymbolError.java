package tinyru.etapa1.Exceptions;

/**
 * Clase que representa el error de un simbolo que no pertenece al alfabeto
 */
public class IllegalSymbolError extends LexerError {
    public IllegalSymbolError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Invalid symbol '" + c + "'");
    }
}
