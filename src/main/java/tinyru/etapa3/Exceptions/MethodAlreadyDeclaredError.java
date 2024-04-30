package tinyru.etapa3.Exceptions;

import tinyru.etapa3.StructInput;

public class MethodAlreadyDeclaredError extends SemanticError {
    public MethodAlreadyDeclaredError(StructInput struct, String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method '" + name + "' already declared in struct '" + struct.getName() + "'");
    }
}
