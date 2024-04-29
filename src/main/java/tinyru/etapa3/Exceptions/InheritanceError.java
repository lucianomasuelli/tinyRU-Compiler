package tinyru.etapa3.Exceptions;

public class InheritanceError extends SemanticError {
    public InheritanceError(String name, String inheritanceName, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Inheritance error in struct " + "`" + name + "`" + ", parent `" + inheritanceName + "` not found.");

    }
}
