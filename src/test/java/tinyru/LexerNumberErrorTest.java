package tinyru;

import org.junit.jupiter.api.Test;
import tinyru.IllegalCharError;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LexerNumberErrorTest {

    @Test
    void tokenize() {
        Lexer lexer = null;
        try {
            lexer = new Lexer("src/test/resources/lexer_num_error_test.ru");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Token token = lexer.nextToken();
            System.out.println(token);
            // Si se llega a este punto sin lanzar una excepción, el test fallará
            fail("Se esperaba que se lanzara una excepción");
        } catch (IllegalCharError e) {
            // Si se lanzó la excepción IllegalCharError, el test pasa
            System.out.println("Mensaje de error: " + e.getMessage());
        } catch (Exception e) {
            // Si se lanzó cualquier otra excepción, el test fallará
            fail("Se esperaba que se lanzara una excepción IllegalCharError, pero se lanzó una excepción diferente: " + e.getClass().getSimpleName());
        }
    }
}