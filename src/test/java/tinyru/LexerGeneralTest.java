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
            lexer = new Lexer("src/test/resources/general_test.ru");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            ArrayList<Token> tokens = new ArrayList<Token>();
            int currChar = lexer.getCurrentChar();
            Token token = lexer.nextToken();
            while (currChar != -1) {
                if (token != null) {
                    tokens.add(token);
                }
                token = lexer.nextToken();
                currChar = lexer.getCurrentChar();
            }
            System.out.println(tokens);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}