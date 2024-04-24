package tinyru.etapa1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.fail;

class LexerTest {

    @Test
    void tokenize() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/lexer_test.ru");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Token token = lexer.nextToken();
            System.out.println(token);
        } catch (Exception e) {
            fail(e);
        }
//        assertEquals(6, lexer.tokens.size());
//        assertEquals(TokenType.RBRACE,lexer.tokens.get(0).getType());
//        assertEquals("}",lexer.tokens.get(0).getLexeme());
//        assertEquals(TokenType.SUM, lexer.tokens.get(1).getType());
//        assertEquals("+",lexer.tokens.get(1).getLexeme());
//        assertEquals(TokenType.RESTA, lexer.tokens.get(2).getType());
//        assertEquals("-", lexer.tokens.get(2).getLexeme());
//        assertEquals(TokenType.LPAREN, lexer.tokens.get(3).getType());
//        assertEquals("(", lexer.tokens.get(3).getLexeme());
//        assertEquals(TokenType.RPAREN, lexer.tokens.get(4).getType());
//        assertEquals(")", lexer.tokens.get(4).getLexeme());
//        assertEquals(TokenType.ASSIGN, lexer.tokens.get(5).getType());
//        assertEquals("=", lexer.tokens.get(5).getLexeme());
//        assertEquals(2, lexer.tokens.get(5).getLine());
//        assertEquals(3, lexer.tokens.get(5).getColumn());
    }
}