package tinyru.etapa2.Exceptions;

import tinyru.etapa1.Token;

public class UnexpectedTokenError extends ParserError {
    public UnexpectedTokenError(String token, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Unexpected token '" + token + "' found");
    }
}
