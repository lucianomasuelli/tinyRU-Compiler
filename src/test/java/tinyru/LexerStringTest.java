package tinyru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LexerStringTest {

    @Test
    void tokenize() {
        Lexer lexer = new Lexer();
        try {
            lexer.tokenize("src/test/resources/lexer_string_test.ru");
            System.out.println(lexer.tokens);
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, lexer.tokens.size());
        assertEquals(TokenType.STRING, lexer.tokens.get(0).getType());
        assertEquals("\"hola mundo\"", lexer.tokens.get(0).getLexeme());
    }
}