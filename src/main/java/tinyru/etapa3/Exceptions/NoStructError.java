package tinyru.etapa3.Exceptions;

public class NoStructError extends SemanticError {
    public NoStructError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Struct '" + name + "' not declared");
    }
}
