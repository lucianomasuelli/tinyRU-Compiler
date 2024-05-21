package tinyru.etapa4.Exceptions;

public class TypeDefinitionError extends SemanticError{
    public TypeDefinitionError(String type, int line, int column){
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Array type '" + type + "' is not a valid type. Valid types are 'Int', 'Str', 'Bool' and 'Char'.");
    }
}
