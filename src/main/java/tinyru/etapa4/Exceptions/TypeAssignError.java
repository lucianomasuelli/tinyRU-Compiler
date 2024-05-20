package tinyru.etapa4.Exceptions;

public class TypeAssignError extends SemanticError{

    public TypeAssignError(String type1, String type2, int line, int column){
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Cant assign type '" + type2 + "' to variable of type '" + type1 + "'.");
    }
}
