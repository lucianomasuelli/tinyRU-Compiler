package tinyru.etapa3.Exceptions;

public class MethodTypeReturnError extends SemanticError{
    public MethodTypeReturnError(String methodName, String returnType, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method `" + methodName + "` has a return type '" + returnType +"' that doesnt exist");
    }
}
