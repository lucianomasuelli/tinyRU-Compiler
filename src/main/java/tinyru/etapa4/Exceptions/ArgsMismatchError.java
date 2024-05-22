package tinyru.etapa4.Exceptions;

public class ArgsMismatchError extends SemanticError{
    public ArgsMismatchError (int received, int expected, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Expected " + expected + " arguments, but received " + received);
    }
}
