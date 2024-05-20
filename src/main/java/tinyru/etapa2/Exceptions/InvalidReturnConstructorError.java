package tinyru.etapa2.Exceptions;

import tinyru.etapa4.Exceptions.SemanticError;

public class InvalidReturnConstructorError extends ParserError {

    public InvalidReturnConstructorError(String struct, int line, int column){
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Invalid return on '"+ struct +"' constructor.");
    }

}
