package tinyru.etapa4.Exceptions;

public class ReturnTypeError extends SemanticError{
    public ReturnTypeError (String type, String expected, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Return type " + type + " does not match expected type " + expected);
    }
}
