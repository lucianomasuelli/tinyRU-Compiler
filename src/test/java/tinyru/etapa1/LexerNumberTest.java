package tinyru.etapa1;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LexerNumberTest {

    @Test
    void nextToken() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/numberTest.ru");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Token token1 = lexer.nextToken();
            assertEquals(TokenType.NUM, token1.getType());
            assertEquals("645465", token1.getLexeme());
            assertEquals(1, token1.getLine());
            assertEquals(1, token1.getColumn());

            Token token2 = lexer.nextToken();
            assertEquals(TokenType.NUM, token2.getType());
            assertEquals("9656", token2.getLexeme());
            assertEquals(1, token2.getLine());
            assertEquals(8, token2.getColumn());

        } catch (Exception e) {
            fail(e);
        }
    }
}