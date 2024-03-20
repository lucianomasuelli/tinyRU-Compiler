package tinyru.AFR;

import tinyru.Exceptions.InvalidOperatorError;
import tinyru.FileScanner;
import tinyru.Token;
import tinyru.TokenType;

import java.io.IOException;

public class AFRNot {
    public Token recognize(char op, FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        scanner.advance();
        int currChar = scanner.getCurrentChar();
        StringBuilder lexeme = new StringBuilder();
        Token token = null;

        if (currChar == '='){
            lexeme.append(op);
            lexeme.append(currChar);
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
