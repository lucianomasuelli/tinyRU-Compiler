package tinyru.etapa3.Exceptions;

public class MethodOverloadError extends SemanticError{
    public MethodOverloadError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method '" + name + "' already declared with different parameters");
    }
}
