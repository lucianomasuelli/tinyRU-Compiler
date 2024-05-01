package tinyru.etapa3.Exceptions;

public class PrimitiveTypeInheritanceError extends SemanticError{
    public PrimitiveTypeInheritanceError(String name, String inheritanceName, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Inheritance error in struct, parent `" + inheritanceName + "` is not an inheritable tipe.");

    }
}
