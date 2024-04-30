package tinyru.etapa3.Exceptions;

public class ParamTypeError extends SemanticError{
    public ParamTypeError(String methodName, String paramName, String paramType, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Method `" + methodName + "` has a parameter `" + paramName + "` with a type '" + paramType +"' that doesnt exist");
    }
}
