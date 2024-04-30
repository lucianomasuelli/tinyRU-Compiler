package tinyru.etapa3.Exceptions;

public class NoConstructorError extends SemanticError {
    public NoConstructorError(String structName, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Struct '" + structName + "' has no constructor");
    }
}
