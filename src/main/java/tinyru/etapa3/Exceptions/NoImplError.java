package tinyru.etapa3.Exceptions;

public class NoImplError extends SemanticError{
    public NoImplError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Struct '" + name + "' has no implementation");
    }
}
