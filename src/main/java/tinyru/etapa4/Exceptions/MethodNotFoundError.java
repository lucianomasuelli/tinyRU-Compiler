package tinyru.etapa4.Exceptions;

public class MethodNotFoundError extends SemanticError{
    public MethodNotFoundError (String struct, String method, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method \"" +  method + "\" does not exist in structure " + struct);
    }
}
