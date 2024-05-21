package tinyru.etapa4.Exceptions;

public class ArraySizeError extends SemanticError{
    public ArraySizeError (String type, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Array size must be of type 'Int' and not '" + type + "'");
    }
}
