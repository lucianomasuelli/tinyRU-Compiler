package tinyru.etapa3.Exceptions;

public class ParamAlreadyDeclared extends SemanticError {
    public ParamAlreadyDeclared(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Param '" + name + "' already declared");
    }

}
