package tinyru.etapa3.Exceptions;

public class ConstructorAlreadyDeclared extends SemanticError {
    public ConstructorAlreadyDeclared(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Constructor in '" + name + "' already declared");
    }
}
