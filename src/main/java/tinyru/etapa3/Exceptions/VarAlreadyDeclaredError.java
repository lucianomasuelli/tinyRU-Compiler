package tinyru.etapa3.Exceptions;

import tinyru.etapa3.StructInput;

public class VarAlreadyDeclaredError extends SemanticError{
    public VarAlreadyDeclaredError(StructInput struct, String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Variable '" + name + "' already declared in struct '" + struct.getName() + "'");
    }

    public VarAlreadyDeclaredError(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Variable '" + name + "' already declared");
    }
}
