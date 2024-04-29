package tinyru.etapa3.Exceptions;

public class MethodAlreadyDeclaredError extends SemanticError {
    public MethodAlreadyDeclaredError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method '" + name + "' already declared");
    }
}
