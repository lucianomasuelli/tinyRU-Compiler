package tinyru.etapa1.Exceptions;

/**
 * Clase que representa el error de los string que contienen un simbolo que no
 * pertenece al alfabeto
 */
public class IllegalStringError extends LexerError {
    public IllegalStringError(char c, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Illegal character '" + c + "' found in the string");
    }
}
