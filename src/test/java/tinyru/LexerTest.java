package tinyru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @Test
    void tokenize() {
        Lexer lexer = new Lexer();
        try {
            lexer.tokenize("src/test/resources/lexer_test.ru");
            System.out.println(lexer.tokens);
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(6, lexer.tokens.size());
        assertEquals(TokenType.RBRACE,lexer.tokens.get(0).getType());
        assertEquals("}",lexer.tokens.get(0).getLexeme());
        assertEquals(TokenType.SUM, lexer.tokens.get(1).getType());
        assertEquals("+",lexer.tokens.get(1).getLexeme());
        assertEquals(TokenType.RESTA, lexer.tokens.get(2).getType());
        assertEquals("-", lexer.tokens.get(2).getLexeme());
        assertEquals(TokenType.LPAREN, lexer.tokens.get(3).getType());
        assertEquals("(", lexer.tokens.get(3).getLexeme());
        assertEquals(TokenType.RPAREN, lexer.tokens.get(4).getType());
        assertEquals(")", lexer.tokens.get(4).getLexeme());
        assertEquals(TokenType.ASSIGN, lexer.tokens.get(5).getType());
        assertEquals("=", lexer.tokens.get(5).getLexeme());
        assertEquals(2, lexer.tokens.get(5).getLine());
        assertEquals(3, lexer.tokens.get(5).getColumn());
    }
}