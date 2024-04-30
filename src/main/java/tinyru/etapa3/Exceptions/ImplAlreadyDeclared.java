package tinyru.etapa3.Exceptions;

public class ImplAlreadyDeclared extends SemanticError {
    public ImplAlreadyDeclared(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Implementation in '" + name + "' already declared");
    }
}
