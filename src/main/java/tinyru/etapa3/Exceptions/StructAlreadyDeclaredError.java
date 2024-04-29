package tinyru.etapa3.Exceptions;

public class StructAlreadyDeclaredError extends SemanticError{
    public StructAlreadyDeclaredError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Struct `" + name + "` already declared.");
    }
}
