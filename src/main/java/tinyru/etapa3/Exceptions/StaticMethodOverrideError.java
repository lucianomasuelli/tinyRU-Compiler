package tinyru.etapa3.Exceptions;

public class StaticMethodOverrideError extends SemanticError {
    public StaticMethodOverrideError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method '" + name + "' already declared as static by a parent struct");
    }
}
