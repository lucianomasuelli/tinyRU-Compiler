package tinyru.etapa4.Exceptions;

public class MissingReturnError extends SemanticError{
    public MissingReturnError (String method, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method " + method + " is missing a return statement");
    }
}
