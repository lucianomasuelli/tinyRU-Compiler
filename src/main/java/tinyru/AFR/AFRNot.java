package tinyru.AFR;

import tinyru.Exceptions.InvalidOperatorError;
import tinyru.FileScanner;
import tinyru.Token;
import tinyru.TokenType;

import java.io.IOException;

/**
 * AFRNot reconoce el operador de negación y el operador de desigualdad
 * de la gramática de TinyRU
 * @author Gabriel Mangione

 */
public class AFRNot {
    public Token recognize(char op, FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int nextChar = scanner.seeNextChar();
        StringBuilder lexeme = new StringBuilder();
        Token token = null;

        if (nextChar == '='){
            lexeme.append(op);
            lexeme.append(nextChar);
            scanner.advance();
        }
        else {
            lexeme.append(op);
        }
        switch (lexeme.toString()) {
            case "!" -> token = new Token(TokenType.NOT, "!", scanner.getLine(), initialColumn);
            case "!=" -> token = new Token(TokenType.DIF, "!=", scanner.getLine(), initialColumn);
        }
        scanner.advance();
        return token;
    }
}
