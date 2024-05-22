package tinyru.etapa4.Exceptions;

public class StaticCallError extends SemanticError{
    public StaticCallError (String struct, String method, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method " + method + " in structure " + struct + " is not static");
    }
}
