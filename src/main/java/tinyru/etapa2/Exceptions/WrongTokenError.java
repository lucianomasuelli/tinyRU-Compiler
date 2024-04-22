package tinyru.etapa2.Exceptions;

import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;

public class WrongTokenError extends  ParserError{
    public WrongTokenError(Token token, TokenType expected, int line, int column) {
        super("| NÚMERO DE LINEA: " + line + "| NÚMERO DE COLUMNA: " + column
                +"| DESCRIPCION: " + "Wrong token '" + token.getType().toString() + "' found, expected '" + expected.toString() + "'");
    }
}
