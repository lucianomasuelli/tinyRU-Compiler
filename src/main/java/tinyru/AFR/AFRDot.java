package tinyru.AFR;

import tinyru.Exceptions.InvalidOperatorError;
import tinyru.FileScanner;
import tinyru.Token;
import tinyru.TokenType;

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
     *  Reconoce si se trata de un constructor o de un punto
      * @param op
     * @param scanner
     * @return
     * @throws IOException
     */
    public Token recognize(char op, FileScanner scanner) throws IOException {
        int initialColumn = scanner.getColumn();
        int nextChar = scanner.seeNextChar();
        Token token = null;

        if (nextChar == '('){
            token = new Token(TokenType.CONSTRUCT, ".", scanner.getLine(), initialColumn);
        }
        else {
            token = new Token(TokenType.DOT, ".", scanner.getLine(), initialColumn);
        }
        scanner.advance();
        return token;
    }

}
