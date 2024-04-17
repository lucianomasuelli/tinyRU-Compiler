package tinyru.etapa1.AFR;

import tinyru.etapa1.Exceptions.IllegalCharError;
import tinyru.etapa1.FileScanner;
import tinyru.etapa1.Token;
import tinyru.etapa1.TokenType;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * AFRDot reconoce los constructores de la gramática de TinyRU
 * y las llamadas a metodos
 */
public class AFRDot {
    /**
     * Reconoce si se trata de un constructor o de un punto
     *
     * @param op
     * @param scanner
     * @return
     * @throws IOException
     */
    public Token recognize(char op, FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int nextChar = scanner.seeNextChar();
        Token token = null;
        int currentState = 0;
        scanner.advance();
        int currChar = scanner.getCurrentChar();
        if (currChar == ' ') {
            scanner.advance();
            currChar = scanner.getCurrentChar();
            if (currChar == '(') {
                token = new Token(TokenType.CONSTRUCT, "constructor", scanner.getLine(), initialColumn);
            } else {
                throw new IllegalCharError((char) currChar, scanner.getLine(), scanner.getColumn());
            }
        } else {
            if (currChar == '(') {
                token = new Token(TokenType.CONSTRUCT, "(", scanner.getLine(), initialColumn);
            } else {
                token = new Token(TokenType.DOT, ".", scanner.getLine(), initialColumn);
            }
        }
        return token;
    }
}