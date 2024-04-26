package tinyru.etapa3.Exceptions;
import tinyru.etapa1.Token;

public class ParamAlreadyDecleared extends SemanticError {
    public ParamAlreadyDecleared(String name, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Param '" + name + "' already decleared");
    }

}
