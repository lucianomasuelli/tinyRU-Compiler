package tinyru.etapa3.Exceptions;

public class ConstructorNotDeclared extends SemanticError {
    public ConstructorNotDeclared(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Constructor in '" + name + "' not declared");
    }
}
