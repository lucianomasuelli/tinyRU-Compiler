package tinyru.etapa4.Exceptions;

public class BoolOpError extends SemanticError {
    public BoolOpError(String type1, String type2, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                + "| DESCRIPCION: " + "Boolean operation between " + type1 + " and " + type2 + " is not allowed");
    }
}

