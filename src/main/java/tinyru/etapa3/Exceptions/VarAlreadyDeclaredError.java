package tinyru.etapa3.Exceptions;

public class VarAlreadyDeclaredError extends SemanticError{
    public VarAlreadyDeclaredError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Variable '" + name + "' already declared");
    }
}
