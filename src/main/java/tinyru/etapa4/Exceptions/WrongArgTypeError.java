package tinyru.etapa4.Exceptions;

public class WrongArgTypeError extends SemanticError{
    public WrongArgTypeError (String expected, String received, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Expected argument of type " + expected + ", but received " + received);
    }
}
