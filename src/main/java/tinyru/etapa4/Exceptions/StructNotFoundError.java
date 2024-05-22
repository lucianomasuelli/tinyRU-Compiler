package tinyru.etapa4.Exceptions;

public class StructNotFoundError extends SemanticError{
    public StructNotFoundError (String struct, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Structure " + struct + " does not exist");
    }
}
