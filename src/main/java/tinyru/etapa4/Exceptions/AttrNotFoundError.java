package tinyru.etapa4.Exceptions;

public class AttrNotFoundError extends SemanticError{
    public AttrNotFoundError(String name, String struct, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Attribute '" + name + "' not found in '" + struct + "'");
    }
}
