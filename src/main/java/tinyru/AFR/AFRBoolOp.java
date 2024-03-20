package tinyru.AFR;

import tinyru.Exceptions.InvalidOperatorError;
import tinyru.FileScanner;
import tinyru.Token;
import tinyru.TokenType;

import java.io.IOException;

/**
 * AFRBoolOp reconoce los operadores booleanos de la gramática
 * de TinyRU
 * @author Gabriel Mangione
 */
public class AFRBoolOp {

/**
     * Método que reconoce el operador booleano
     * El metodo asocia operadores iguales a izquierda
     * @param op operador a reconocer
     * @param scanner scanner del archivo
     * @return Token
     * @throws IOException
     */
    public Token recognize(char op, FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        scanner.advance();
        int currChar = scanner.getCurrentChar();
        StringBuilder lexeme = new StringBuilder();
        Token token = null;

        if (currChar == op){
            lexeme.append(op);
            lexeme.append(currChar);
        }
        else {
            throw new InvalidOperatorError(op, scanner.getLine(), scanner.getColumn());
        }
        switch (lexeme.toString()) {
            case "&&" -> token = new Token(TokenType.AND, "&&", scanner.getLine(), initialColumn);
            case "||" -> token = new Token(TokenType.OR, "||", scanner.getLine(), initialColumn);
        }
        scanner.advance();
        return token;
    }
}
