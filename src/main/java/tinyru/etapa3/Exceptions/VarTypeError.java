package tinyru.etapa3.Exceptions;

public class VarTypeError extends SemanticError{
    public VarTypeError(String varName, String varType, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Variable `" + varName + "` has a type '" + varType +"' that doesnt exist");
    }
}
