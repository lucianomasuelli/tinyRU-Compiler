package tinyru.etapa4.Exceptions;

public class TypesMismatchError extends SemanticError {

    public TypesMismatchError(String type1, String type2, int line, int column){
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Types '" + type1 + "' and '" + type2 + "' does not match.");
    }
}
