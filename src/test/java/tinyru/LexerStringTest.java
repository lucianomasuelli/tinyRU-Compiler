package tinyru;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LexerStringTest {

    @Test
    void tokenize() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/lexer_string_test.ru");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            int i = 0;
            int currChar = lexer.getCurrentChar();
            while (currChar != -1){
                Token token = lexer.nextToken();
                System.out.println(token);
            }

        } catch (Exception e) {
            fail(e);
        }
//        assertEquals(1, lexer.tokens.size());
//        assertEquals(TokenType.STRING, lexer.tokens.get(0).getType());
//        assertEquals("\"hola mundo\"", lexer.tokens.get(0).getLexeme());
    }
}