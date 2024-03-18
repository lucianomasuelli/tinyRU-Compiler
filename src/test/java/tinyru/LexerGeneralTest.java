package tinyru;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LexerGeneralTest {

    @Test
    void nextToken() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/fibonacci.ru");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            int i = 0;
            ArrayList<Token> tokens = new ArrayList<Token>();
            int currChar = lexer.getCurrentChar();
            Token token;
            while (currChar != -1) {
                token = lexer.nextToken();
                if (token != null) {
                    tokens.add(token);
                }
                currChar = lexer.getCurrentChar();
            }
            System.out.println(tokens);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}