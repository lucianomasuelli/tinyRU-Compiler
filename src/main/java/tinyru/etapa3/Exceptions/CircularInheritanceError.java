package tinyru.etapa3.Exceptions;

public class CircularInheritanceError extends SemanticError{
public CircularInheritanceError(String structName, String inheritanceName, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Circular inheritance error in struct " + "`" + structName + "`" + ", parent `" + inheritanceName + "` is already a child.");
    }
}
