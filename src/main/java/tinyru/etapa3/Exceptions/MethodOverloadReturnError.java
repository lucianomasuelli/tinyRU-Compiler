package tinyru.etapa3.Exceptions;

public class MethodOverloadReturnError extends SemanticError{
    public MethodOverloadReturnError(String methodName, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method `" + methodName + "` has the same name that a parent's method but different return type.");
    }
}
