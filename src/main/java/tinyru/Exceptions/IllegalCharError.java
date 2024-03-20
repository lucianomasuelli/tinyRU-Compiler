package tinyru.Exceptions;

/**
 * Clase que representa el error de un caracter que contiene un simbolo que
 * no pertenece al alfabeto
 */
public class IllegalCharError extends LexerError {
    public IllegalCharError(char c, int line, int column) {
        super( "| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Illegal character '" + c + "'");
    }
}
